package com.yh.apoplexy.admin.portal.patient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.patient.service.intf.AdminHealthScreenDetailService;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientHRInputDto;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientScreenDetailDto;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientScreenDto;
import com.yh.apoplexy.common.constants.AdminConstants;
import com.yh.apoplexy.common.constants.PatientConstants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestMethod;

import com.yh.apoplexy.admin.patient.service.intf.AdminPatientHRService;
/**
 *
 *
 * 高危筛查管理
 */
import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.patient.forms.AdminHRDetailForm;
import com.yh.apoplexy.admin.portal.patient.forms.AdminHRListForm;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;


import com.yh.apoplexy.assist.dto.admin.patient.PatientHRDto;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;


@Controller
@RequestMapping(value = "/hr")
public class AdminHRController extends BaseController {

    @Autowired
    private AdminPatientHRService adminPatientHRService;

    @Autowired
    private AdminHealthScreenDetailService adminHealthScreenDetailService;

    @RequestMapping(value = "/hrcom", method = RequestMethod.GET)
    public String astCom(HttpServletRequest request, Model model) {
        return patientView("patientHrList");
    }

    /**
     * 查询高危筛查列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryHRList", method = RequestMethod.POST)
    public String queryHRlList(HttpServletRequest request, HttpServletResponse response, Model model, AdminHRListForm form) {

        String patientName = form.getPatientName();
        String area = form.getAreaName();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        PatientHRInputDto inputDto = new PatientHRInputDto();

        if (StringUtils.isNotBlank(patientName)) {
            inputDto.setPatientName(patientName);
        }

        if (StringUtils.isNotBlank(area)) {
            inputDto.setAreaName(area);
        }
        if (StringUtils.isNotBlank(startTime)) {
            inputDto.setStartTime(DateUtil.parseDate(startTime, DateUtil.yyyy_MM_dd));
        }

        if (StringUtils.isNotBlank(endTime)) {
            inputDto.setEndTime(DateUtil.parseDate(endTime, DateUtil.yyyy_MM_dd));
        }

        Page page = new Page();

        page.setCurrentPage(Integer.valueOf(form.getPageNum()));
        page.setPageSize(Integer.valueOf(form.getPageSize()));

        List<PatientScreenDto> list = adminPatientHRService.queryHRList(inputDto, page);

        model.addAttribute("hrList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));
        return patientView("patientHrListtable");

    }

    /**
     * 查询高危筛查详情
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryHRDetails", method = RequestMethod.POST)
    public String queryHRDetails(HttpServletRequest request, HttpServletResponse response, Model model, AdminHRDetailForm form) {

        String id = form.getId();

        if (StringUtils.isBlank(id)) {
            return null;
        }

        PatientScreenDetailDmo con = new PatientScreenDetailDmo();

        con.setRecordId(Long.parseLong(id));
        con.setType(form.getType());

        List<PatientScreenDetailDmo> detailDmoList = adminHealthScreenDetailService.selectList(con);

        List<PatientScreenDetailDto> detailDtoList = new ArrayList<>();

        PatientScreenDetailDto detailDto = null;

        for (PatientScreenDetailDmo detailDmo:detailDmoList){

            detailDto = new PatientScreenDetailDto();

            detailDto.setDetailIndex(detailDmo.getDetailIndex());
            if (PatientConstants.ScreenType.HISTORY.equals(form.getType())){
                detailDto.setQuestion(AdminConstants.HISTORY_INDEX_QUESTION_MAP.get(detailDmo.getDetailIndex().toString()));
            }else{
                detailDto.setQuestion(AdminConstants.FIRST_SCREEN_INDEX_QUESTION_MAP.get(detailDmo.getDetailIndex().toString()));
            }
            detailDto.setResult(detailDmo.getResult());

            detailDtoList.add(detailDto);

        }

        model.addAttribute("healthScreenDetailList", detailDtoList);

        return patientView("patienthealthscreendetailtable");
    }

}
