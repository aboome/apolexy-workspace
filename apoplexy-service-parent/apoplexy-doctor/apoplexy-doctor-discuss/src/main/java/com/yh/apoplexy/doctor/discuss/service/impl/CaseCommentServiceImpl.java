package com.yh.apoplexy.doctor.discuss.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.CaseCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.ChildCaseCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.QueryDiscussCaseDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.constants.MessageConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.discuss.result.DiscussCasePermissionResult;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yh.apoplexy.doctor.discuss.service.intf.CaseCommentService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMessageService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorScoreService;
import com.yh.apoplexy.integration.service.intf.PushMessageService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 讨论病例评论服务实现类
 * Created by wunder on 16/9/7 18:29.
 */
@Service("caseCommentService")
@ServiceTrace
public class CaseCommentServiceImpl implements CaseCommentService{

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DiscussCaseService discussCaseService;

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Autowired
    private DoctorMessageService doctorMessageService;

    @Override
    public Result insert(CaseCommentDmo caseCommentDmo) {

        int i = commonDao.insert(caseCommentDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(CaseCommentDmo caseCommentDmo) {

        int i = commonDao.update(caseCommentDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(CaseCommentDmo caseCommentDmo) {

        int i = commonDao.delete(caseCommentDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public CaseCommentDmo find(CaseCommentDmo con) {
        return (CaseCommentDmo)commonDao.selectOne(con);
    }

    @Override
    @Transactional
    public Result submitCaseComment(CaseCommentDmo con) {

        Result result = new Result();
        //检查评论医生的病例访问权限
        DiscussCasePermissionResult permissionResult = discussCaseService.checkDoctorCasePermission(con.getRecordId(),con.getFromUserId());

        if (!permissionResult.isSuccess()){
            result.fail("SCC-0000","提交评论用户没有病例访问权限");
            return result;
        }

        //新增病例评论信息
        CaseCommentDmo caseCommentDmo = new CaseCommentDmo();

        caseCommentDmo.setRecordId(con.getRecordId());
        caseCommentDmo.setFromUserId(con.getFromUserId());
        caseCommentDmo.setToUserId(permissionResult.getPostDoctorId());
        caseCommentDmo.setContent(con.getContent());
        caseCommentDmo.setType(DoctorConstants.CommentType.PARENT_COMMENT);
        caseCommentDmo.setParentId(0L);
        caseCommentDmo.setCreateTime(DateUtil.getDate());
        caseCommentDmo.setLastUpdateTime(DateUtil.getDate());
        caseCommentDmo.setStatus(DoctorConstants.CommentStatus.NORMAL);

        result = insert(caseCommentDmo);

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        //增加病例评论数量
        result = discussCaseService.increaseCommentCount(con.getRecordId());

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        //增加积分
        if(checkFirstCaseToday(con.getFromUserId())){

            DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

            doctorScoreDetailDmo.setUserId(con.getFromUserId());
            doctorScoreDetailDmo.setUserName(permissionResult.getDoctorName());
            doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.REPLAY_DISCUSS);
            doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_DISCUSS_COMMENT));
            doctorScoreDetailDmo.setScore(10L);
            doctorScoreDetailDmo.setTime(DateUtil.getDate());

            result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

        }

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(permissionResult.getPostDoctorId());
        doctorMessageDmo.setTitle(Constants.DISCUSS_TYPE_NAME_MAP.get(permissionResult.getType())+"："+permissionResult.getMainDesc());
        doctorMessageDmo.setType(MessageConstants.MESSAGE_DISCUSS_COMMENT);

        doctorMessageService.addMessage(doctorMessageDmo);

        return result;
    }

    @Override
    @Transactional
    public Result replyCaseComment(CaseCommentDmo con) {

        Result result = new Result();

        CaseCommentDmo caseCommentCon = new CaseCommentDmo();

        caseCommentCon.setId(con.getParentId());
        caseCommentCon.setStatus(DoctorConstants.CommentStatus.NORMAL);
        //查询父评论信息
        CaseCommentDmo parentComment = find(caseCommentCon);

        if (null == parentComment){

            result.fail("RCC-0000","父评论不存在，参数错误");
            return result;

        }
        //判断被回复用户和父评论的回复用户是否相同
        if (null == parentComment.getFromUserId()||!parentComment.getFromUserId().equals(con.getToUserId())){

            result.fail("RCC-0001","父评论的回复发起用户和子评论的被回复用户不相同，参数错误");
            return result;

        }

        //判断评论回复用户的病例访问权限
        DiscussCasePermissionResult permissionResult = discussCaseService.checkDoctorCasePermission(parentComment.getRecordId(),con.getFromUserId());

        if (!permissionResult.isSuccess()){
            result.fail("RCC-0002","评论回复用户没有病例访问权限");
            return result;
        }

        //新增评论回复信息
        CaseCommentDmo caseCommentDmo = new CaseCommentDmo();

        caseCommentDmo.setRecordId(parentComment.getRecordId());
        caseCommentDmo.setFromUserId(con.getFromUserId());
        caseCommentDmo.setToUserId(con.getToUserId());
        caseCommentDmo.setContent(con.getContent());
        caseCommentDmo.setType(DoctorConstants.CommentType.CHILD_COMMENT);
        caseCommentDmo.setParentId(parentComment.getId());
        caseCommentDmo.setCreateTime(DateUtil.getDate());
        caseCommentDmo.setLastUpdateTime(DateUtil.getDate());
        caseCommentDmo.setStatus(DoctorConstants.CommentStatus.NORMAL);

        result = insert(caseCommentDmo);

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        //增加病例评论数量
        result = discussCaseService.increaseCommentCount(parentComment.getRecordId());

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        //增加积分
        if(checkFirstCaseToday(con.getFromUserId())){

            DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

            doctorScoreDetailDmo.setUserId(con.getFromUserId());
            doctorScoreDetailDmo.setUserName(permissionResult.getDoctorName());
            doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.REPLAY_DISCUSS);
            doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_DISCUSS_COMMENT));
            doctorScoreDetailDmo.setScore(10L);
            doctorScoreDetailDmo.setTime(DateUtil.getDate());

            result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

        }

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(parentComment.getFromUserId());
        doctorMessageDmo.setTitle(Constants.DISCUSS_TYPE_NAME_MAP.get(permissionResult.getType())+"："+permissionResult.getMainDesc());
        doctorMessageDmo.setType(MessageConstants.MESSAGE_DISCUSS_REPLY);

        doctorMessageService.addMessage(doctorMessageDmo);

        return result;
    }

    @Override
    public List<CaseCommentDto> queryCommentList(CaseCommentDmo con, Page page) {

        con.setType(DoctorConstants.CommentType.PARENT_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        List<CaseCommentDto> caseCommentDtoList = commonDao.selectListByPage("CaseCommentMapper.countComment","CaseCommentMapper.queryComment",con,page);

        if (CollectionUtils.isEmpty(caseCommentDtoList)){

            return caseCommentDtoList;

        }

        for (CaseCommentDto caseCommentDto: caseCommentDtoList){

            CaseCommentDmo childCon = new CaseCommentDmo();

            childCon.setParentId(caseCommentDto.getDiscussId());
            childCon.setStatus(DoctorConstants.CommentStatus.NORMAL);

            List<ChildCaseCommentDto> childCaseCommentDtoList = queryChildCommentList(childCon);

            caseCommentDto.setChildDiscussList(childCaseCommentDtoList);

        }

        return caseCommentDtoList;

    }

    @Override
    public List<ChildCaseCommentDto> queryChildCommentList(CaseCommentDmo con) {

        con.setType(DoctorConstants.CommentType.CHILD_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        return commonDao.selectList("CaseCommentMapper.queryChildComment",con);
    }

    /**
     * 检查是否今日首次发布
     * @param doctorId
     * @return
     */
    private boolean checkFirstCaseToday(Long doctorId){

        CaseCommentDmo con = new CaseCommentDmo();

        con.setFromUserId(doctorId);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        Long count = commonDao.selectCount("CaseCommentMapper.countCommentToday",con);

        return count.equals(1L);

    }

}
