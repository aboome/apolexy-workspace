package com.yh.apoplexy.admin.portal.patient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.assist.dto.admin.patient.emergency.PatientEmergencyDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yh.apoplexy.admin.patient.service.intf.AdminPatientZFEmerService;
import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.patient.forms.AdminZFEmerDetailForm;
import com.yh.apoplexy.admin.portal.patient.forms.AdminZFEmerForm;
import com.yh.apoplexy.assist.dmo.patient.health.PatientEmergencyDmo;
import com.yh.apoplexy.assist.dto.admin.patient.emergency.PatientEmergencyInputDto;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientScreenDetailDto;
import com.yh.apoplexy.assist.dto.patient.health.patientZfDto;
import com.yh.apoplexy.common.constants.AdminConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;

/**
 * 中风急救管理
 *
 * @author zhangbiao
 */

@Controller
@RequestMapping(value = "/ZFEmer")
public class AdminZFEmerController extends BaseController {


    @Autowired
    private AdminPatientZFEmerService adminPatientZFEmerService;

    @RequestMapping(value = "/ZFEmercom", method = RequestMethod.GET)
    public String astCom(HttpServletRequest request, Model model) {

        UserSession userSession = getUserSession(request);

        return patientView("patientZfList");
    }


    /**
     * 查询中风急救列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryZFEmerList", method = RequestMethod.POST)
    public String queryZFEmerList(HttpServletRequest request, HttpServletResponse response, Model model, AdminZFEmerForm form) {


        String patientName = form.getPatientName();
        String area = form.getAreaName();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        PatientEmergencyInputDto inputDto = new PatientEmergencyInputDto();

        if (StringUtils.isNotBlank(patientName)) {
            inputDto.setPatientName(patientName);
        }

        if (StringUtils.isNotBlank(area)) {
            inputDto.setAreaName(area);
            ;
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

        List<PatientEmergencyDto> list = adminPatientZFEmerService.queryZFEmerList(inputDto, page);

        model.addAttribute("ZFEmerList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));
        return patientView("patientZfListtable");

    }


    /**
     * 查询中风急救详情
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryZFEmerDetail", method = RequestMethod.POST)
    public String queryZFEmerDetail(HttpServletRequest request, HttpServletResponse response, Model model, AdminZFEmerDetailForm form) {

        String id = form.getId();
        PatientEmergencyDmo con = new PatientEmergencyDmo();

        List<patientZfDto> detailDtoList = new ArrayList<>();
        patientZfDto detailDtoOne = new patientZfDto();
        patientZfDto detailDtoTwo = new patientZfDto();
        patientZfDto detailDtoThree = new patientZfDto();


        if (!StringUtils.isBlank(id)) {
            con.setId(Long.valueOf(id));
        }
        PatientEmergencyDmo zfDetail = adminPatientZFEmerService.queryZFEmerDetail(con);


        detailDtoOne.setQuestion(AdminConstants.ZF_INDEX_QUESTION_MAP.get("1"));
        detailDtoOne.setResult(zfDetail.getFace());
        detailDtoList.add(detailDtoOne);

        detailDtoTwo.setQuestion(AdminConstants.ZF_INDEX_QUESTION_MAP.get("2"));
        detailDtoTwo.setResult(zfDetail.getArm());
        detailDtoList.add(detailDtoTwo);

        detailDtoThree.setQuestion(AdminConstants.ZF_INDEX_QUESTION_MAP.get("3"));
        detailDtoThree.setResult(zfDetail.getSpeech());
        detailDtoList.add(detailDtoThree);
        model.addAttribute("zfDetailList", detailDtoList);
        return patientView("zfdetailtable");
    }


}
