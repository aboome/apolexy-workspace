package com.yh.apoplexy.admin.portal.doctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.doctor.cases.ast.service.intf.AdminAstCasesService;
import com.yh.apoplexy.admin.doctor.cases.ast.service.intf.AdminAstCommentService;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminAstCaseDetailDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminAstCommentDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminQueryAstCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AstCasesReplyDto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminAstCommentForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminAstDeleteForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminAstDetailForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminAstListForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminAstReplyDeleteForm;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.NihssDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.NihssDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.AstCasesDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.AstMedicationDto;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

/**
 * Ast病例管理
 *
 * @author zhangbiao
 */

@Controller
@RequestMapping(value = "/ast")
public class AdminAstController extends BaseController {

    @Autowired
    private AdminAstCasesService adminAstCasesService;

    @Autowired
    private AdminAstCommentService adminAstCommentService;

    @RequestMapping(value = "/astcom", method = RequestMethod.GET)
    public String astCom(HttpServletRequest request, Model model) {

        return doctorView("casemanage");
    }

    /**
     * 查询ast列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryAstlList", method = RequestMethod.POST)
    public String queryAstlList(HttpServletRequest request, HttpServletResponse response, Model model, AdminAstListForm form) {

        AdminQueryAstCaseDto queryAstCaseDto = new AdminQueryAstCaseDto();

        String doctorName = form.getDoctorName();
        String hospitalName = form.getHospitalName();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();


        if (StringUtils.isNotBlank(doctorName)) {
            queryAstCaseDto.setDoctorName(doctorName);
        }

        if (StringUtils.isNotBlank(hospitalName)) {
            queryAstCaseDto.setHospitalName(hospitalName);
        }
        if (StringUtils.isNotBlank(startTime)) {
            queryAstCaseDto.setStartTime(DateUtil.parseDate(startTime, DateUtil.yyyy_MM_dd));
        }
        if (StringUtils.isNotBlank(endTime)) {
            queryAstCaseDto.setEndTime(DateUtil.parseDate(endTime, DateUtil.yyyy_MM_dd));
        }

        Page page = new Page();
        page.setCurrentPage(Integer.valueOf(form.getPageNum()));
        page.setPageSize(Integer.valueOf(form.getPageSize()));

        List<AstCasesDto> list = adminAstCasesService.queryAstCasesList(queryAstCaseDto, page);

        model.addAttribute("astList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));
        return doctorView("casemanagetable");

    }

    /**
     * 查询Ast病例详情
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryAstDetails", method = RequestMethod.POST)
    public String queryAstDetails(HttpServletRequest request, HttpServletResponse response, Model model, AdminAstDetailForm form) {

        String id = form.getRecordId();

        AstCasesDmo con = new AstCasesDmo();

        if (!StringUtils.isBlank(id)) {
            con.setId(Long.valueOf(id));
        }

        AdminAstCaseDetailDto astDetail = adminAstCasesService.findCaseDetail(con);

        model.addAttribute("astDetail", astDetail);

        return doctorView("casemanagedetail");

    }

    /**
     * 查询ast病例的回复列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryAstReply", method = RequestMethod.POST)
    public String queryAstReply(HttpServletRequest request, HttpServletResponse response, Model model, AdminAstCommentForm form) {

        String id = form.getId();

        AstCasesCommentDmo con = new AstCasesCommentDmo();

        con.setRecordId(Long.valueOf(id));

        List<AdminAstCommentDto> list = adminAstCommentService.queryCommentList(con);

        model.addAttribute("commentList", list);

        return doctorView("ast_comment_table");
    }

    /**
     * 删除AST病例的评论
     *
     * @param request
     * @param response
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCasesComment", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteCasesComment(HttpServletRequest request, HttpServletResponse response, Model model, Long id) {

        JsonResult jsonResult = new JsonResult();

        if (null == id) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        AstCasesCommentDmo con = new AstCasesCommentDmo();

        con.setId(id);

        Result result = adminAstCasesService.deleteAstComment(con);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除AST病例评论失败");
        }
        
        UserSession userSession = getUserSession(request);

//        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
//                OperateLogConstants.OperateEnum.DELETE_AST_REVIEW,
//                Long.parseLong(userSession.getUserId()),
//                userSession.getName(),
//                OperateLogConstants.OperateDesc.DELETE_AST_REVIEW);
        return jsonResult;
    }


    /**
     * 删除AST病例的回复
     *
     * @param request
     * @param response
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCasesReply", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteCasesReply(HttpServletRequest request, HttpServletResponse response, Model model, Long id) {

        JsonResult jsonResult = new JsonResult();

        if (null == id) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        AstCasesCommentDmo con = new AstCasesCommentDmo();

        con.setId(id);

        Result result = adminAstCasesService.deleteAstReply(con);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除AST病例回复失败");
        }
        
        UserSession userSession = getUserSession(request);

//        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
//                OperateLogConstants.OperateEnum.DELETE_AST_REPLY,
//                Long.parseLong(userSession.getUserId()),
//                userSession.getName(),
//                OperateLogConstants.OperateDesc.DELETE_AST_REPLY);
        return jsonResult;
    }

    /**
     * 删除ast病例讨论
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/deleteAstCases", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteAstCases(HttpServletRequest request,
                              HttpServletResponse response, Model model,
                              AdminAstDeleteForm form) {

        JsonResult jsonResult = new JsonResult();

        String id = form.getId();

        AstCasesDmo con = new AstCasesDmo();

        if (!StringUtils.isBlank(id)) {
            con.setId(Long.valueOf(id));
        }


        Result result = adminAstCasesService.deleteAstCases(con);
        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除病例讨论失败");
        }
        
        UserSession userSession = getUserSession(request);

//        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
//                OperateLogConstants.OperateEnum.DELETE_AST_DISCUSS,
//                Long.parseLong(userSession.getUserId()),
//                userSession.getName(),
//                OperateLogConstants.OperateDesc.DELETE_AST_DISCUSS);
        return jsonResult;
    }

}
