package com.yh.apoplexy.admin.portal.statics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.statics.forms.AdminStatisDoctorForm;
import com.yh.apoplexy.admin.statis.intf.DoctorStatisService;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.statistics.DoctorStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.HospitalStatisticsDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.web.result.JsonResult;

/**
 * 
 * 医生数据统计列表
 * 
 * @author zhangbiao
 * 
 */

@Controller
@RequestMapping(value = "/doctorStatis")
public class AdminDoctorStatisController extends BaseController {

	@Autowired
	private DoctorStatisService doctorStatisService;

	@RequestMapping(value = "/doctorStatiscom", method = RequestMethod.GET)
	public String doctorStatiscom(HttpServletRequest request, Model model) {

		return staticsView("doctorstatistics");
	}

	
	@RequestMapping(value = "/queryDoctorStatistics", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryDoctorStatistics(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		JsonResult jsonResult = new JsonResult();

		DoctorMemberDmo doctorMemberDmo  =  new DoctorMemberDmo();

		doctorMemberDmo.setStatus(Constants.DoctorStatus.NORMAL);

		List<DoctorStatisticsDto> doctorStatisticsDtoList = doctorStatisService
				.queryDoctorCount(doctorMemberDmo);

		if (!CollectionUtils.isEmpty(doctorStatisticsDtoList)) {

			for (DoctorStatisticsDto doctorStatisticsDto : doctorStatisticsDtoList) {

				doctorStatisticsDto.setName(Constants.DOCTOR_LEVEL_NAME_MAP
						.get(doctorStatisticsDto.getName()));
			}

		}

		jsonResult.setSuccess(true);
		jsonResult.setData(doctorStatisticsDtoList);

		return jsonResult;

	}

}
