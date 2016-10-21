package com.yh.apoplexy.admin.doctor.cases.discuss.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yh.apoplexy.admin.doctor.cases.discuss.service.intf.AdminCaseCommentService;
import com.yh.apoplexy.admin.doctor.cases.discuss.service.intf.AdminCaseResourceService;
import com.yh.apoplexy.admin.doctor.cases.discuss.service.intf.AdminDoctorCasesService;

import com.yh.apoplexy.admin.doctor.member.service.intf.AdminDoctorMemberService;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminDiscussCaseDetailDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminQueryDiscussCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.CasesDiscussDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.CasesDiscussReplyDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yjh.framework.lang.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CasesReplyDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import org.springframework.transaction.annotation.Transactional;


/**
 * 医生端病例/病例讨论管理
 *
 * @author zhangbiao
 */
@Service("adminDoctorCasesService")
@ServiceTrace
public class AdminDoctorCasesServiceImpl implements AdminDoctorCasesService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AdminDoctorMemberService adminDoctorMemberService;

    @Autowired
    private AdminCaseResourceService adminCaseResourceService;

    @Autowired
    private AdminCaseCommentService adminCaseCommentService;

    @Override
    public DiscussCaseDmo find(DiscussCaseDmo con) {

        return (DiscussCaseDmo) commonDao.selectOne(con);

    }

    @Override
    public Result update(DiscussCaseDmo con) {

        int i = commonDao.update(con);

        return SqlAssertUtils.updateAssert(i);
    }

    /**
     * 查询病例讨论列表
     */
    @Override
    public List<CasesDiscussDto> queryCasesDiscussList(AdminQueryDiscussCaseDto con, Page page) {

        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        return commonDao.selectListByPage("DoctorMapper.queryCasesDiscussListCount", "DoctorMapper.queryCasesDiscussList", con, page);

    }

    /**
     * 查询病例讨论详情
     */
    @Override
    public AdminDiscussCaseDetailDto queryCasesDiscussDetail(DiscussCaseDmo con) {

        AdminDiscussCaseDetailDto discussCaseDetailDto = new AdminDiscussCaseDetailDto();

        if (null == con.getId()) {
            return null;
        }

        //查询病例详情
        DiscussCaseDmo discussCaseDmo = find(con);

        if (null == discussCaseDmo) {
            return null;
        }

        discussCaseDetailDto.setDiscussCaseDmo(discussCaseDmo);

        DoctorMemberDmo memberCon = new DoctorMemberDmo();

        memberCon.setId(discussCaseDmo.getUserId());
        memberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询发布病例详情的医生信息
        DoctorMemberDmo doctorMemberDmo = adminDoctorMemberService.selectOne(memberCon);

        discussCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);

        CaseResourceDmo videoCon = new CaseResourceDmo();

        videoCon.setRecordId(con.getId());
        videoCon.setType(Constants.ResourcesType.VIDEO);
        //查询病例视频信息
        CaseResourceDmo video = adminCaseResourceService.find(videoCon);

        discussCaseDetailDto.setVideo(video);

        CaseResourceDmo imageCon = new CaseResourceDmo();

        imageCon.setRecordId(con.getId());
        imageCon.setType(Constants.ResourcesType.IMAGE);

        //查询病例的图片列表
        List<CaseResourceDmo> imageList = adminCaseResourceService.selectList(imageCon);

        discussCaseDetailDto.setImageList(imageList);

        return discussCaseDetailDto;

    }

    /**
     * 查询病例讨论的回复列表
     */
    @Override
    public List<CasesDiscussReplyDto> queryCasesDiscussReplyList(DiscussCaseDmo con, Page page) {

        Long recordId = con.getId();

        //根据record把所有的评论取出来
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("recordId", recordId.toString());

        return commonDao.selectListByPage("DoctorMapper.queryCasesDiscussReplyListCount", "DoctorMapper.queryCasesDiscussReplyList", map, page);
    }

    @Override
    @Transactional
    public Result deleteDiscussComment(CaseCommentDmo con) {

        Result result = new Result();

        Long id = con.getId();

        if (id == null) {
            result.fail("DDC-0001", "参数为空");
            return result;
        }

        CaseCommentDmo caseCommentDmo = adminCaseCommentService.find(con);

        if (null == caseCommentDmo) {

            result.fail("DDC-0002", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentType.PARENT_COMMENT.equals(caseCommentDmo.getType())) {

            result.fail("DDC-0003", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentStatus.NORMAL.equals(caseCommentDmo.getStatus())) {

            result.fail("DDC-0003", "评论已被删除");
            return result;
        }

        caseCommentDmo.setStatus(DoctorConstants.CommentStatus.DELETE);

        result = adminCaseCommentService.update(caseCommentDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        CaseCommentDmo childCommentCon = new CaseCommentDmo();

        childCommentCon.setParentId(caseCommentDmo.getId());
        childCommentCon.setStatus(DoctorConstants.CommentStatus.NORMAL);

        Long childCommentNum = adminCaseCommentService.countChildComment(childCommentCon);

        result = decreaseCommentCount(caseCommentDmo.getRecordId(),childCommentNum+1);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;

    }

    @Override
    @Transactional
    public Result deleteDiscussReply(CaseCommentDmo con) {

        Result result = new Result();

        Long id = con.getId();

        if (id == null) {
            result.fail("DDR-0001", "参数为空");
            return result;
        }

        CaseCommentDmo caseCommentDmo = adminCaseCommentService.find(con);

        if (null == caseCommentDmo) {

            result.fail("DDR-0002", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentType.CHILD_COMMENT.equals(caseCommentDmo.getType())) {

            result.fail("DDR-0003", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentStatus.NORMAL.equals(caseCommentDmo.getStatus())) {

            result.fail("DDR-0003", "回复已删除");
            return result;
        }

        caseCommentDmo.setStatus(DoctorConstants.CommentStatus.DELETE);

        result = adminCaseCommentService.update(caseCommentDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        result = decreaseCommentCount(caseCommentDmo.getRecordId(),1L);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;

    }


    /**
     * 删除病例讨论，删除的是病例
     */
    @Override
    public Result deleteCases(DiscussCaseDmo con) {
        Result result = new Result();

        if (con == null) {
            result.fail("", "参数为空,删除失败");
            return result;
        }

        if (con.getId() == null) {
            result.fail("", "参数为空，删除失败");
        }

        con.setStatus(DoctorConstants.CaseStatus.DELETE);
        int i = commonDao.update(con);

	   /* //病例删除了，针对这条病例的所有评论也应该删除
        if(result.isSuccess()){
	    	   Long recordId = con.getId();
	    	   CasesReplyDmo crCon = new CasesReplyDmo();
	    	   crCon.setRecordId(recordId);
	    	   int j = commonDao.delete(crCon);
	    	   result = SqlAssertUtils.deleteAssert(i);
	    	   return result;
	    }
		*/
        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result decreaseCommentCount(Long recordId, Long num) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id", recordId.toString());
        conMap.put("num", num.toString());

        int i = commonDao.update("AdminDiscussCaseMapper.decreaseCommentCount", conMap);

        return SqlAssertUtils.updateAssert(i);
    }

}
