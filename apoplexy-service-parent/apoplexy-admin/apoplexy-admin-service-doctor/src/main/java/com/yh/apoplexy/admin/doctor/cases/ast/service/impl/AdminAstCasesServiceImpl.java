package com.yh.apoplexy.admin.doctor.cases.ast.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yh.apoplexy.admin.doctor.cases.ast.service.intf.*;
import com.yjh.framework.lang.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.doctor.cases.service.intf.AdminNihssDetailService;
import com.yh.apoplexy.admin.doctor.member.service.intf.AdminDoctorMemberService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstResourcesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.NihssDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.NihssDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminAstCaseDetailDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminDoctorAstNihssDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminQueryAstCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AstCasesReplyDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AstHistoryDto;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientScreenDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.AstCasesDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.AstMedicationDto;
import com.yh.apoplexy.common.constants.AdminConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 * Ast病例管理
 *
 * @author zhangbiao
 */
@Service("adminAstCasesService")
@ServiceTrace


public class AdminAstCasesServiceImpl implements AdminAstCasesService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AdminDoctorMemberService adminDoctorMemberService;

    @Autowired
    private AdminNihssDetailService adminNihssDetailService;

    @Autowired
    private AdminAstHistoryService adminAstHistoryService;

    @Autowired
    private AdminAstMedicationService adminAstMedicationService;

    @Autowired
    private AdminAstResourceService adminAstResourceService;

    @Autowired
    private AdminAstCommentService adminAstCommentService;

    @Override
    public AstCasesDmo find(AstCasesDmo con) {
        return (AstCasesDmo) commonDao.selectOne(con);
    }

    /**
     * 查询Ast病例列表
     */
    @Override
    public List<AstCasesDto> queryAstCasesList(AdminQueryAstCaseDto con, Page page) {

        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        return commonDao.selectListByPage("DoctorMapper.queryAstCasesListCount", "DoctorMapper.queryAstCasesList", con, page);

    }

    @Override
    public AdminAstCaseDetailDto findCaseDetail(AstCasesDmo con) {

        AdminAstCaseDetailDto astCaseDetailDto = new AdminAstCaseDetailDto();

        if (null == con.getId()) {
            return null;
        }

        //查询病例详情
        AstCasesDmo astCasesDmo = find(con);

        if (null == astCasesDmo) {
            return null;
        }

        astCaseDetailDto.setAstCasesDmo(astCasesDmo);

        DoctorMemberDmo memberCon = new DoctorMemberDmo();

        memberCon.setId(astCasesDmo.getUserId());
        memberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询发布病例详情的医生信息
        DoctorMemberDmo doctorMemberDmo = adminDoctorMemberService.selectOne(memberCon);

        astCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);

        NihssDetailDmo nihssDetailCon = new NihssDetailDmo();

        nihssDetailCon.setRecordId(con.getId());
        nihssDetailCon.setType(DoctorConstants.NihssTestSrcType.AST);

        //查询NIHSS信息列表
        List<NihssDetailDmo> nihssDetailDmoList = adminNihssDetailService.selectList(nihssDetailCon);

        astCaseDetailDto.setNihssList(nihssDetailDmoList);

        AstHistoryDmo astHistoryCon = new AstHistoryDmo();

        astHistoryCon.setRecordId(con.getId());


        List<AdminDoctorAstNihssDto> adminDoctorAstNihssList = new ArrayList<>();

        AdminDoctorAstNihssDto astNihssDto = null;

        for (NihssDetailDmo detailDmo : nihssDetailDmoList) {

            astNihssDto = new AdminDoctorAstNihssDto();

            astNihssDto.setDetailIndex(detailDmo.getDetailIndex());

            astNihssDto.setQuestion(AdminConstants.AST_NIHSS_QUESTION_MAP.get(detailDmo.getDetailIndex().toString()));

            astNihssDto.setResult(String.valueOf(detailDmo.getResult()));

            adminDoctorAstNihssList.add(astNihssDto);

        }
        astCaseDetailDto.setAstNihssList(adminDoctorAstNihssList);


        //查询既往史
        List<AstHistoryDmo> astHistoryDmoList = adminAstHistoryService.selectList(astHistoryCon);

        astCaseDetailDto.setHisIllness(astHistoryDmoList);

        List<AdminDoctorAstNihssDto> astHistoryList = new ArrayList<>();

        AdminDoctorAstNihssDto astHistory = null;

        for (AstHistoryDmo detailDmo : astHistoryDmoList) {

            astHistory = new AdminDoctorAstNihssDto();

            astHistory.setDetailIndex(detailDmo.getDetailIndex());

            astHistory.setQuestion(AdminConstants.AST_HISTORY_INDEX_QUESTION_MAP.get(detailDmo.getDetailIndex().toString()));

            astHistory.setResult(detailDmo.getResult());

            astHistoryList.add(astHistory);

        }
        astCaseDetailDto.setHisQuestionList(astHistoryList);


        AstMedicationDmo astMedicationCon = new AstMedicationDmo();

        astMedicationCon.setRecordId(con.getId());
        //查询既往用药史
        List<AstMedicationDmo> astMedicationDmoList = adminAstMedicationService.selectList(astMedicationCon);

        astCaseDetailDto.setHisMedicaitionList(astMedicationDmoList);

        List<AdminDoctorAstNihssDto> astHistoryMedicationList = new ArrayList<>();

        AdminDoctorAstNihssDto astMedication = null;

        for (AstMedicationDmo detailDmo : astMedicationDmoList) {

            astMedication = new AdminDoctorAstNihssDto();

            astMedication.setDetailIndex(detailDmo.getDetailIndex());

            astMedication.setQuestion(AdminConstants.AST_HISTORY_MEDICATION_MAP.get(detailDmo.getDetailIndex().toString()));

            astMedication.setResult(detailDmo.getResult());

            astHistoryMedicationList.add(astMedication);

        }
        astCaseDetailDto.setAstHisMedicaitionList(astHistoryMedicationList);


        AstResourcesDmo ctCon = new AstResourcesDmo();

        ctCon.setRecordId(con.getId());
        ctCon.setType(DoctorConstants.AstResourcesType.CT);

        //查询病例的CT图片列表
        List<AstResourcesDmo> ctList = adminAstResourceService.selectList(ctCon);

        astCaseDetailDto.setCtList(ctList);

        AstResourcesDmo ctaCon = new AstResourcesDmo();

        ctaCon.setRecordId(con.getId());
        ctaCon.setType(DoctorConstants.AstResourcesType.CTA);

        //查询病例的CTA图片列表
        List<AstResourcesDmo> ctaList = adminAstResourceService.selectList(ctaCon);

        astCaseDetailDto.setCtaList(ctaList);

        AstResourcesDmo ctpCon = new AstResourcesDmo();

        ctpCon.setRecordId(con.getId());
        ctpCon.setType(DoctorConstants.AstResourcesType.CTP);

        //查询病例的CTP图片列表
        List<AstResourcesDmo> ctpList = adminAstResourceService.selectList(ctpCon);

        astCaseDetailDto.setCtpList(ctpList);

        return astCaseDetailDto;

    }


    //查询nihss明细表
    public List<NihssDto> queryHihss(NihssDmo con) {

        //Nihss评测明细表绑定一个recordId
        if (con.getRecordId() == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("recordId", con.getRecordId().toString());
        return commonDao.selectList("DoctorMapper.queryNihssList", map);
    }


    //查询AST既往史
    public List<AstHistoryDto> queryAstHistory(AstHistoryDmo con) {


        if (con.getRecordId() == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("recordId", con.getRecordId().toString());

        return commonDao.selectList("DoctorMapper.queryAstHistoryList", map);
    }


    //查询AST既往用药史
    public List<AstMedicationDto> queryAstMedication(AstMedicationDmo con) {

        if (con.getRecordId() == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("recordId", con.getRecordId().toString());
        return commonDao.selectList("DoctorMapper.queryAstMedicationList", map);
    }

    @Override
    @Transactional
    public Result deleteAstComment(AstCasesCommentDmo con) {

        Result result = new Result();

        Long id = con.getId();

        if (id == null) {
            result.fail("DAC-0001", "参数为空");
            return result;
        }

        AstCasesCommentDmo astCommentDmo = adminAstCommentService.find(con);

        if (null == astCommentDmo) {

            result.fail("DAC-0002", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentType.PARENT_COMMENT.equals(astCommentDmo.getType())) {

            result.fail("DAC-0003", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentStatus.NORMAL.equals(astCommentDmo.getStatus())) {

            result.fail("DAC-0004", "评论已被删除");
            return result;
        }

        astCommentDmo.setStatus(DoctorConstants.CommentStatus.DELETE);

        result = adminAstCommentService.update(astCommentDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        AstCasesCommentDmo childCommentCon = new AstCasesCommentDmo();

        childCommentCon.setParentId(astCommentDmo.getId());
        childCommentCon.setStatus(DoctorConstants.CommentStatus.NORMAL);

        Long childCommentNum = adminAstCommentService.countChildComment(childCommentCon);

        result = decreaseCommentCount(astCommentDmo.getRecordId(), childCommentNum + 1);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;
    }

    @Override
    @Transactional
    public Result deleteAstReply(AstCasesCommentDmo con) {

        Result result = new Result();

        Long id = con.getId();

        if (id == null) {
            result.fail("DAR-0001", "参数为空");
            return result;
        }

        AstCasesCommentDmo caseCommentDmo = adminAstCommentService.find(con);

        if (null == caseCommentDmo) {

            result.fail("DAR-0002", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentType.CHILD_COMMENT.equals(caseCommentDmo.getType())) {

            result.fail("DAR-0003", "参数错误");
            return result;
        }

        if (!DoctorConstants.CommentStatus.NORMAL.equals(caseCommentDmo.getStatus())) {

            result.fail("DAR-0003", "回复已被删除");
            return result;
        }

        caseCommentDmo.setStatus(DoctorConstants.CommentStatus.DELETE);

        result = adminAstCommentService.update(caseCommentDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        result = decreaseCommentCount(caseCommentDmo.getRecordId(), 1L);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;

    }

    @Override
    public Result decreaseCommentCount(Long recordId, Long num) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id", recordId.toString());
        conMap.put("num", num.toString());

        int i = commonDao.update("AdminAstCaseMapper.decreaseCommentCount", conMap);

        return SqlAssertUtils.updateAssert(i);
    }

    /**
     * 查询ASt病例的回复列表,通过recordId来查
     */
    @Override
    public List<AstCasesReplyDto> queryAstCasesReplyList(AstCasesDmo con, Page page) {
        if (con == null) {
            return null;
        }
        if (con.getId() == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("recordId", con.getId().toString());

        return commonDao.selectListByPage("DoctorMapper.queryAstCasesReplyListCount", "DoctorMapper.queryAstCasesReplyList", map, page);
    }


    /**
     * Ast病例回复的删除，根据recordId来删除
     */
    @Override
    public Result deleteAstCasesReply(AstCasesCommentDmo con) {
        Result result = new Result();

        if (con == null) {
            result.fail("", "参数为空，删除失败");
            return result;
        }
        if (con.getId() == null) {
            result.fail("", "参数为空，删除失败");
            return null;
        }
        con.setStatus(DoctorConstants.CaseStatus.DELETE);
        int i = commonDao.update(con);
        return SqlAssertUtils.updateAssert(i);
    }


    /**
     * 删除Ast病例讨论，如果Ast病例讨论都删除了，那么在这条病例下生成的所有评论也要删除
     */
    @Override
    public Result deleteAstCases(AstCasesDmo con) {
        Result result = new Result();
        if (con == null) {
            result.fail("", "参数为空,删除失败");
            return result;
        }

        if (con.getId() == null) {
            result.fail("", "参数为空,删除失败");
            return result;
        }

        int i = commonDao.delete(con);
        result = SqlAssertUtils.deleteAssert(i);

	    /*if(result.isSuccess()){
	    	    Long recordId = con.getId();
	    	    AstCasesCommentDmo accDmo = new AstCasesCommentDmo();
	    	    accDmo.setRecordId(recordId);
	    	    int j = commonDao.delete(accDmo);
	    	    result = SqlAssertUtils.deleteAssert(i);
	    	    return result;
	    }*/

        return result;


    }


}
