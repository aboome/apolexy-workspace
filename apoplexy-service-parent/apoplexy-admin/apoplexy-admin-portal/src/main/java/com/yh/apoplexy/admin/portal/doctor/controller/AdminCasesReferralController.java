package com.yh.apoplexy.admin.portal.doctor.controller;

/**
 * 病例转诊管理
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.doctor.cases.referral.service.intf.AdminDoctorReferralService;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminDiscussCaseDetailDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminQueryDiscussCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.referral.AdminQueryReferralCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.referral.AdminReferralCaseDetailDto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminCasesReferralDetailForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminCasesReferralForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminDeleteReferralForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminReceiveDoctorListForm;
import com.yh.apoplexy.admin.portal.doctor.forms.AdminReferralCommentForm;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.CasesReferralDto;
import com.yh.apoplexy.assist.dto.admin.doctor.CasesReferralRecvDto;
import com.yh.apoplexy.assist.dto.admin.doctor.EvalueDto;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/referral")
public class AdminCasesReferralController extends BaseController {

    @Autowired
    private AdminDoctorReferralService adminDoctorReferralService;

    @RequestMapping(value = "/casescom", method = RequestMethod.GET)
    public String referralCom(HttpServletRequest request, Model model) {


        return doctorView("casereferral");
    }

    /**
     * 查询病例转诊列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryReferralList", method = RequestMethod.POST)
    public String queryReferralList(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesReferralForm form) {

        String doctorName = form.getDoctorName();
        String hospitalName = form.getHospitalName();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        AdminQueryReferralCaseDto queryReferralCaseDto = new AdminQueryReferralCaseDto();

        if (StringUtils.isNotBlank(doctorName)) {
            queryReferralCaseDto.setDoctorName(doctorName);
        }

        if (StringUtils.isNotBlank(hospitalName)) {
            queryReferralCaseDto.setHospitalName(hospitalName);
        }
        if (StringUtils.isNotBlank(startTime)) {
            queryReferralCaseDto.setStartTime(DateUtil.parseDate(startTime, DateUtil.yyyy_MM_dd));
        }

        if (StringUtils.isNotBlank(endTime)) {
            queryReferralCaseDto.setEndTime(DateUtil.parseDate(endTime, DateUtil.yyyy_MM_dd));
        }

        Page page = new Page();

        page.setCurrentPage(Integer.valueOf(form.getPageNum()));
        page.setPageSize(Integer.valueOf(form.getPageSize()));

        List<CasesReferralDto> list = adminDoctorReferralService.queryCasesReferralList(queryReferralCaseDto, page);

        model.addAttribute("referralList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return doctorView("casereferraltable");

    }

    /**
     * 查询转诊详情
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryReferralDetails", method = RequestMethod.POST)
    public String queryReferralDetails(HttpServletRequest request, HttpServletResponse response, Model model, AdminCasesReferralDetailForm form) {

        String id = form.getId();

        ReferralCaseDmo con = new ReferralCaseDmo();

        if (!StringUtils.isBlank(id)) {
            con.setId(Long.valueOf(id));
        }

        AdminReferralCaseDetailDto referralDetail = adminDoctorReferralService.queryCasesReferralDetail(con);

        ReferralCaseDmo referralCase = referralDetail.getReferralCaseDmo();
        
        referralCase.setType(Constants.REFERRAL_TYPE_NAME_MAP.get(referralCase.getType()));
        
        referralDetail.setReferralCaseDmo(referralCase);
        
        model.addAttribute("referralDetail", referralDetail);

        return doctorView("casereferraldetail");
    }

    /**
     * 查询接诊医生列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryReveiveList", method = RequestMethod.POST)
    public String queryReveiveList(HttpServletRequest request, HttpServletResponse response, Model model, AdminReceiveDoctorListForm form) {

        ReferralCaseDmo con = new ReferralCaseDmo();

        String recordId = form.getRecordId();
        if (!StringUtils.isBlank(recordId)) {
            con.setId(Long.valueOf(recordId));
        }

        Page page = new Page();
        page.setCurrentPage(Integer.valueOf(form.getPageNum()));
        page.setPageSize(Integer.valueOf(form.getPageSize()));

        List<CasesReferralRecvDto> list = adminDoctorReferralService.queryReceiveDoctorList(con, page);
        model.addAttribute("receivedoctorList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));
        return doctorView("doctorlisttable");
    }

    /**
     * 查询病例转诊评价表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryReferralComment", method = RequestMethod.POST)
    public String queryReferralComment(HttpServletRequest request, HttpServletResponse response, Model model,
                                       AdminReferralCommentForm form) {

        DiscussCaseDmo con = new DiscussCaseDmo();

        String recordId = form.getRecordId();
        if (!StringUtils.isBlank(recordId)) {
            con.setId(Long.valueOf(recordId));
        }

        EvalueDto referralComment = adminDoctorReferralService.queryReferralComment(con);
        model.addAttribute("referralComment", referralComment);
        return doctorView("doctorevaluate");
    }
   /**
    * 删除病例转诊
    * @param form
    * @param request
    * @param response
    * @param model
    * @return
    */
    @RequestMapping(value = "/deleteReferral", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteReferral(AdminDeleteReferralForm form, HttpServletRequest request, HttpServletResponse response,
                              Model model) {

        ReferralCaseDmo con = new ReferralCaseDmo();
        JsonResult jsonResult = new JsonResult();
        String id = form.getId();
        if (!StringUtils.isBlank(id)) {
            con.setId(Long.valueOf(id));
        }

        Result result = adminDoctorReferralService.deleteCasesReferral(con);
        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除病例转诊失败");
        }
        UserSession userSession = getUserSession(request);

//        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
//                OperateLogConstants.OperateEnum.DELETE_CASE_REFERRAL,
//                Long.parseLong(userSession.getUserId()),
//                userSession.getName(),
//                OperateLogConstants.OperateDesc.DELETE_CASE_REFERRAL);
        return jsonResult;

    }

}
