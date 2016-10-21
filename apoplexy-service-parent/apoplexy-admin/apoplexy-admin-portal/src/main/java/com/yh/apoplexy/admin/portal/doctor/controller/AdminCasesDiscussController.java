package com.yh.apoplexy.admin.portal.doctor.controller;


import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.doctor.cases.discuss.service.intf.AdminCaseCommentService;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminDiscussCaseDetailDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminDiscussCommentDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminQueryDiscussCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.CasesDiscussDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.dto.UserSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.doctor.cases.discuss.service.intf.AdminDoctorCasesService;
import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminCasesDeleteForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminCasesDiscussDetailForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminCasesDiscussForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminCasesDiscussReplyFrom;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminCasesReplyDeleteForm;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CasesReplyDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

/**
 * 病例/病例讨论管理控制器
 *
 * @author zhangbiao
 */
@Controller
@RequestMapping(value = "/cases")
public class AdminCasesDiscussController extends BaseController {

    @Autowired
    private AdminDoctorCasesService adminDoctorCasesService;

    @Autowired
    private AdminCaseCommentService adminCaseCommentService;

    @RequestMapping(value = "/casescom", method = RequestMethod.GET)
    public String doctorCom(HttpServletRequest request, Model model) {

        return doctorView("casediscuss");
    }


    /**
     * 查询病例讨论列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryCasesList", method = RequestMethod.POST)
    public String queryCasesList(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesDiscussForm form) {

        String doctorName = form.getDoctorName();
        String hospitalName = form.getHospitalName();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        AdminQueryDiscussCaseDto queryDiscussCaseDto = new AdminQueryDiscussCaseDto();

        if (StringUtils.isNotBlank(doctorName)) {
            queryDiscussCaseDto.setDoctorName(doctorName);
        }

        if (StringUtils.isNotBlank(hospitalName)) {
            queryDiscussCaseDto.setHospitalName(hospitalName);
        }

        if (StringUtils.isNotBlank(startTime)) {
            queryDiscussCaseDto.setStartTime(DateUtil.parseDate(startTime, DateUtil.yyyy_MM_dd));
        }

        if (StringUtils.isNotBlank(endTime)) {
            queryDiscussCaseDto.setEndTime(DateUtil.parseDate(endTime, DateUtil.yyyy_MM_dd));
        }

        Page page = new Page();

        page.setCurrentPage(Integer.valueOf(form.getPageNum()));
        page.setPageSize(Integer.valueOf(form.getPageSize()));

        List<CasesDiscussDto> list = adminDoctorCasesService.queryCasesDiscussList(queryDiscussCaseDto, page);

        model.addAttribute("casesList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return doctorView("casediscusstable");

    }

    /**
     * 查询病例讨论详情
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryCasesDetails", method = RequestMethod.POST)
    public String queryDoctorDetails(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesDiscussDetailForm form) {

        String id = form.getId();

        DiscussCaseDmo con = new DiscussCaseDmo();

        con.setId(Long.valueOf(id));

        AdminDiscussCaseDetailDto detail = adminDoctorCasesService.queryCasesDiscussDetail(con);

        DiscussCaseDmo discussCaseDmo = detail.getDiscussCaseDmo();

        discussCaseDmo.setType(Constants.DISCUSS_TYPE_NAME_MAP.get(discussCaseDmo.getType()));

        detail.setDiscussCaseDmo(discussCaseDmo);

        model.addAttribute("discussDetail", detail);

        return doctorView("casediscussdetail");
    }

    /**
     * 查询病例讨论的回复列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryCasesReply", method = RequestMethod.POST)
    public String queryCasesReply(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesDiscussReplyFrom form) {

        String id = form.getId();

        CaseCommentDmo con = new CaseCommentDmo();
        con.setRecordId(Long.valueOf(id));

        List<AdminDiscussCommentDto> list = adminCaseCommentService.queryCommentList(con);

        model.addAttribute("commentList", list);
        return doctorView("discuss_comment_table");
    }


    /**
     * 删除病例讨论的评论
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/deleteCasesComment", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteCasesComment(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesReplyDeleteForm form) {

        JsonResult jsonResult = new JsonResult();

        String id = form.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        CaseCommentDmo con = new CaseCommentDmo();

        con.setId(Long.valueOf(id));

        Result result = adminDoctorCasesService.deleteDiscussComment(con);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除病例讨论评论失败");
        }
        
        UserSession userSession = getUserSession(request);

//        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
//                OperateLogConstants.OperateEnum.DELETE_CASE_REVIEW,
//                Long.parseLong(userSession.getUserId()),
//                userSession.getName(),
//                OperateLogConstants.OperateDesc.DELETE_CASE_REVIEW);
        return jsonResult;
    }


    /**
     * 删除病例讨论的回复
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/deleteCasesReply", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteCasesReply(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesReplyDeleteForm form) {

        JsonResult jsonResult = new JsonResult();

        String id = form.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        CaseCommentDmo con = new CaseCommentDmo();

        con.setId(Long.valueOf(id));

        Result result = adminDoctorCasesService.deleteDiscussReply(con);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除病例讨论回复失败");
        }
        
        UserSession userSession = getUserSession(request);

//        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
//                OperateLogConstants.OperateEnum.DELETE_CASE_REPLY,
//                Long.parseLong(userSession.getUserId()),
//                userSession.getName(),
//                OperateLogConstants.OperateDesc.DELETE_CASE_REPLY);
        return jsonResult;
    }

    /**
     * 删除病例讨论
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/deleteCases", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteCases(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesDeleteForm form) {

        JsonResult jsonResult = new JsonResult();

        String recordId = form.getId();

        if (StringUtils.isBlank(recordId)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        DiscussCaseDmo con = new DiscussCaseDmo();

        con.setId(Long.valueOf(recordId));

        Result result = adminDoctorCasesService.deleteCases(con);
        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除病例讨论失败");
        }
        
        UserSession userSession = getUserSession(request);

//        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
//                OperateLogConstants.OperateEnum.DELETE_CASE_REPLY,
//                Long.parseLong(userSession.getUserId()),
//                userSession.getName(),
//                OperateLogConstants.OperateDesc.DELETE_CASE_REPLY);
        return jsonResult;
    }


}
