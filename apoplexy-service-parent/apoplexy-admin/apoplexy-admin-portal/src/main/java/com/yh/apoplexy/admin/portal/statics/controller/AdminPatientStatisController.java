package com.yh.apoplexy.admin.portal.statics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.assist.dto.patient.member.PatientMemberDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.statis.intf.PatientStatisService;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.common.constants.Constants;
import com.yjh.framework.web.result.JsonResult;
/**
 * 
 * 
 * 平台患者注册统计
 * @author zhangbiao
 *
 */

@Controller
@RequestMapping(value = "/patientStatis")
public class AdminPatientStatisController extends BaseController {
	@Autowired
	private PatientStatisService patientStatisService ; 
	
	@RequestMapping(value = "/patientStatiscom", method = RequestMethod.GET)
	public String patientStatiscom(HttpServletRequest request, Model model) {


    
		return staticsView("patientstatistics");
	}
	
	/***
	 * 根据地区进行患者统计
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryPatientStatistics", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryPatientStatistics(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		JsonResult jsonResult = new JsonResult();
		
		PatientMemberDmo memberDmo = new PatientMemberDmo();
		
		memberDmo.setStatus(Constants.MenuStatus.NORMAL);
		
		List<PatientMemberDto> memberDtosList = patientStatisService.queryPatientCount(memberDmo);
		
		if (!CollectionUtils.isEmpty(memberDtosList)) {
			
			for (PatientMemberDto patientMemberDto : memberDtosList) {
                if(StringUtils.isBlank(patientMemberDto.getName())){
				patientMemberDto.setName("未知地区");
                }
			}
			
			 
		}
		
		jsonResult.setSuccess(true);
		jsonResult.setData(memberDtosList);
		
		return jsonResult;
	}

	/**
	 * 根据FAST量表统计患者
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryPatientFastStatistics", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryPatientFastStatistics(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonResult jsonResult = new JsonResult();
		
		PatientMemberDmo memberDmo = new PatientMemberDmo();
		
		List<PatientMemberDto> patientMemberDtosList= patientStatisService.countPatientByFast(memberDmo);
		
		jsonResult.setSuccess(true);
		jsonResult.setData(patientMemberDtosList);
		
		return jsonResult;
		
	}
	
	/***
	 * 根据高危标示统计患者
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryPatientByIncidence", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryPatientByIncidence(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonResult jsonResult = new JsonResult();
		
		PatientMemberDmo memberDmo = new PatientMemberDmo();
		
		memberDmo.setStatus(Constants.MenuStatus.NORMAL);
		
		List<PatientMemberDto> dtosList= patientStatisService.countPatientByIncidence(memberDmo);
		if (!CollectionUtils.isEmpty(dtosList)) {
			
			for (PatientMemberDto patientMemberDto : dtosList) {
                if(patientMemberDto.getName().equals("1")){
				patientMemberDto.setName("高危患者");
                }else if(patientMemberDto.getName().equals("0")){
            		patientMemberDto.setName("非高危患者");
                }
			}
			
		}
		
		jsonResult.setSuccess(true);
		jsonResult.setData(dtosList);
		
		return jsonResult;
		
	}
} 
