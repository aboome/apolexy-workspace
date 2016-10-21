package com.yh.apoplexy.doctor.discuss.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.ChildAstCommentDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.*;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yh.apoplexy.doctor.discuss.service.intf.AstCommentService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMessageService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorScoreService;
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
 * AST病例评论服务实现类
 * Created by wunder on 16/9/10 17:14.
 */
@Service("astCommentService")
@ServiceTrace
public class AstCommentServiceImpl implements AstCommentService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AstCaseService astCaseService;

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private DoctorMessageService doctorMessageService;

    @Override
    public Result insert(AstCasesCommentDmo astCasesCommentDmo) {

        int i = commonDao.insert(astCasesCommentDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(AstCasesCommentDmo astCasesCommentDmo) {

        int i = commonDao.update(astCasesCommentDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(AstCasesCommentDmo astCasesCommentDmo) {

        int i = commonDao.delete(astCasesCommentDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public AstCasesCommentDmo find(AstCasesCommentDmo con) {
        return (AstCasesCommentDmo)commonDao.selectOne(con);
    }

    @Override
    @Transactional
    public Result submitCaseComment(AstCasesCommentDmo con) {

        Result result = new Result();

        AstCasesDmo astCasesCon = new AstCasesDmo();

        astCasesCon.setId(con.getRecordId());
        astCasesCon.setStatus(DoctorConstants.CaseStatus.NORMAL);

        AstCasesDmo astCasesDmo = astCaseService.find(astCasesCon);
        //AST病例不存在
        if (null == astCasesDmo||!DoctorConstants.CaseStatus.NORMAL.equals(astCasesDmo.getStatus())){

            result.fail("SCC-0000","提交评论失败");
            return result;

        }

        //新增病例评论信息
        AstCasesCommentDmo astCasesCommentDmo = new AstCasesCommentDmo();

        astCasesCommentDmo.setRecordId(con.getRecordId());
        astCasesCommentDmo.setFromUserId(con.getFromUserId());
        astCasesCommentDmo.setToUserId(astCasesDmo.getUserId());
        astCasesCommentDmo.setContent(con.getContent());
        astCasesCommentDmo.setType(DoctorConstants.CommentType.PARENT_COMMENT);
        astCasesCommentDmo.setParentId(0L);
        astCasesCommentDmo.setCreateTime(DateUtil.getDate());
        astCasesCommentDmo.setLastUpdateTime(DateUtil.getDate());
        astCasesCommentDmo.setStatus(DoctorConstants.CommentStatus.NORMAL);

        result = insert(astCasesCommentDmo);

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        //增加病例评论数量
        result = astCaseService.increaseCommentCount(con.getRecordId());

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(con.getFromUserId());
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);

        if (null == doctorMemberDto){

            throw new AppException(result);
        }

        //增加积分
        if(checkFirstCaseToday(con.getFromUserId())){

            DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

            doctorScoreDetailDmo.setUserId(con.getFromUserId());
            doctorScoreDetailDmo.setUserName(doctorMemberDto.getDoctorName());
            doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.REPLY_AST);
            doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_AST_COMMENT));
            doctorScoreDetailDmo.setScore(10L);
            doctorScoreDetailDmo.setTime(DateUtil.getDate());

            result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

        }

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(astCasesDmo.getUserId());

        if(null != astCasesDmo.getOnsetTime()){

            doctorMessageDmo.setTitle("患者"+astCasesDmo.getPatientSex()+"，"+astCasesDmo.getPatientAge()+"岁。发病时间："+DateUtil.format(astCasesDmo.getOnsetTime(),"yyyy/MM/dd HH:mm"));

        }else {

            doctorMessageDmo.setTitle("患者"+astCasesDmo.getPatientSex()+"，"+astCasesDmo.getPatientAge()+"岁。发病时间：未知");

        }

        doctorMessageDmo.setType(MessageConstants.MESSAGE_AST_COMMENT);

        doctorMessageService.addMessage(doctorMessageDmo);

        return result;
    }

    @Override
    @Transactional
    public Result replyCaseComment(AstCasesCommentDmo con) {

        Result result = new Result();

        AstCasesCommentDmo astCasesCommentCon = new AstCasesCommentDmo();

        astCasesCommentCon.setId(con.getParentId());
        astCasesCommentCon.setStatus(DoctorConstants.CommentStatus.NORMAL);
        //查询父评论信息
        AstCasesCommentDmo parentComment = find(astCasesCommentCon);

        if (null == parentComment){

            result.fail("RCC-0000","父评论不存在，参数错误");
            return result;

        }
        //判断被回复用户和父评论的回复用户是否相同
        if (null == parentComment.getFromUserId()||!parentComment.getFromUserId().equals(con.getToUserId())){

            result.fail("RCC-0001","父评论的回复发起用户和子评论的被回复用户不相同，参数错误");
            return result;

        }
        //判断AST病例是否存在
        AstCasesDmo astCasesCon = new AstCasesDmo();

        astCasesCon.setId(parentComment.getRecordId());
        astCasesCon.setStatus(DoctorConstants.CaseStatus.NORMAL);

        AstCasesDmo astCasesDmo = astCaseService.find(astCasesCon);
        //AST病例不存在
        if (null == astCasesDmo||!DoctorConstants.CaseStatus.NORMAL.equals(astCasesDmo.getStatus())){

            result.fail("SCC-0000","提交评论失败");
            return result;

        }

        //新增评论回复信息
        AstCasesCommentDmo astCasesCommentDmo = new AstCasesCommentDmo();

        astCasesCommentDmo.setRecordId(parentComment.getRecordId());
        astCasesCommentDmo.setFromUserId(con.getFromUserId());
        astCasesCommentDmo.setToUserId(con.getToUserId());
        astCasesCommentDmo.setContent(con.getContent());
        astCasesCommentDmo.setType(DoctorConstants.CommentType.CHILD_COMMENT);
        astCasesCommentDmo.setParentId(parentComment.getId());
        astCasesCommentDmo.setCreateTime(DateUtil.getDate());
        astCasesCommentDmo.setLastUpdateTime(DateUtil.getDate());
        astCasesCommentDmo.setStatus(DoctorConstants.CommentStatus.NORMAL);

        result = insert(astCasesCommentDmo);

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        //增加病例评论数量
        result = astCaseService.increaseCommentCount(parentComment.getRecordId());

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(con.getFromUserId());
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);

        if (null == doctorMemberDto){

            throw new AppException(result);
        }

        //增加积分
        if(checkFirstCaseToday(con.getFromUserId())){

            DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

            doctorScoreDetailDmo.setUserId(con.getFromUserId());
            doctorScoreDetailDmo.setUserName(doctorMemberDto.getDoctorName());
            doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.REPLY_AST);
            doctorScoreDetailDmo.setEventDesc("回复AST病例");
            doctorScoreDetailDmo.setScore(10L);
            doctorScoreDetailDmo.setTime(DateUtil.getDate());

            result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

        }

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(parentComment.getFromUserId());
        doctorMessageDmo.setTitle("患者"+astCasesDmo.getPatientSex()+"，"+astCasesDmo.getPatientAge()+"岁。发病时间："+DateUtil.format(astCasesDmo.getOnsetTime(),"yyyy/MM/dd HH:mm"));
        doctorMessageDmo.setType(MessageConstants.MESSAGE_AST_REPLY);

        doctorMessageService.addMessage(doctorMessageDmo);

        return result;

    }

    @Override
    public List<AstCommentDto> queryCommentList(AstCasesCommentDmo con, Page page) {

        con.setType(DoctorConstants.CommentType.PARENT_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        List<AstCommentDto> astCommentDtoList = commonDao.selectListByPage("AstCommentMapper.countComment","AstCommentMapper.queryComment",con,page);

        if (CollectionUtils.isEmpty(astCommentDtoList)){

            return astCommentDtoList;

        }

        for (AstCommentDto astCommentDto: astCommentDtoList){

            AstCasesCommentDmo childCon = new AstCasesCommentDmo();

            childCon.setParentId(astCommentDto.getDiscussId());
            childCon.setStatus(DoctorConstants.CommentStatus.NORMAL);

            List<ChildAstCommentDto> childAstCommentDtoList = queryChildCommentList(childCon);

            astCommentDto.setChildDiscussList(childAstCommentDtoList);

        }

        return astCommentDtoList;
    }

    @Override
    public List<ChildAstCommentDto> queryChildCommentList(AstCasesCommentDmo con) {

        con.setType(DoctorConstants.CommentType.CHILD_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        return commonDao.selectList("AstCommentMapper.queryChildComment",con);

    }

    /**
     * 检查是否今日首次发布
     * @param doctorId
     * @return
     */
    private boolean checkFirstCaseToday(Long doctorId){

        AstCasesCommentDmo con = new AstCasesCommentDmo();

        con.setFromUserId(doctorId);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        Long count = commonDao.selectCount("AstCommentMapper.countCommentToday",con);

        return count.equals(1L);

    }
}
