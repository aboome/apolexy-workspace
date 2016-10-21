package com.yh.apoplexy.admin.portal.patient.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yh.apoplexy.admin.patient.service.intf.AdminPatientHealthDataService;
import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.patient.forms.AdminHDDetailForm;
import com.yh.apoplexy.admin.portal.patient.forms.AdminHDListForm;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataDmo;
import com.yh.apoplexy.assist.dto.admin.patient.healthdata.PatientHealthDataInputDto;
import com.yh.apoplexy.assist.dto.patient.health.HealthDataDto;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;

/**
 * 体征数据管理
 *
 * @author zhangbiao
 */

@Controller
@RequestMapping(value = "/healthData")
public class AdminHealthDataController extends BaseController {

    @Autowired
    private AdminPatientHealthDataService adminPatientHealthDataService;

    @RequestMapping(value = "/healthDatacom", method = RequestMethod.GET)
    public String astCom(HttpServletRequest request, Model model) {
        return patientView("patientHdList");
    }

    /**
     * 查询体征数据列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryHDList", method = RequestMethod.POST)
    public String queryHDlList(HttpServletRequest request, HttpServletResponse response, Model model, AdminHDListForm form) {


        String patientName = form.getPatientName();
        String areaName = form.getAreaName();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        PatientHealthDataInputDto inputDto = new PatientHealthDataInputDto();


        if (StringUtils.isNotBlank(patientName)) {
            inputDto.setPatientName(patientName);
        }

        if (StringUtils.isNotBlank(areaName)) {
            inputDto.setAreaName(areaName);
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
        List<HealthDataDto> list = adminPatientHealthDataService.queryHealDataList(inputDto, page);
        model.addAttribute("hdList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));
        return patientView("patientHdListtable");

    }


    /**
     * 查询体征数据详情
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryHDDetails", method = RequestMethod.POST)
    public String queryHDDetails(HttpServletRequest request,
                                 HttpServletResponse response, Model model, AdminHDDetailForm form
    ) {

        String id = form.getId();
        HealthDataDmo con = new HealthDataDmo();
        HealthDataDto hdDetail = adminPatientHealthDataService.queryHealDataDetail(con);
        if (!StringUtils.isBlank(id)) {
            con.setId(Long.valueOf(id));
        }

        model.addAttribute("hdDetail", hdDetail);
        return patientView("patienthealthdatadetailtable");
    }


}
