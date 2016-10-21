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
import com.yh.apoplexy.admin.portal.statics.forms.AdminStatisReferralForm;
import com.yh.apoplexy.admin.statis.intf.DoctorStatisService;
import com.yh.apoplexy.admin.statis.intf.ReferralStatisService;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.common.StatisReferralInputDto;
import com.yh.apoplexy.assist.dto.statistics.DoctorStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.HospitalStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.ReferralStatisticsDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.web.result.JsonResult;

/**
 * 
 * 
 * 病例转诊统计量表
 * 
 * @author zhangbiao
 * 
 */

@Controller
@RequestMapping(value = "/referralStatis")
public class AdminReferralStatisController extends BaseController {
	@Autowired
	private ReferralStatisService referralStatisService;

	@RequestMapping(value = "/referralStatiscom", method = RequestMethod.GET)
	public String referralStatisCom(HttpServletRequest request, Model model) {

		return staticsView("referralstatis");
	}

	/**
	 * 根据医院等级统计转诊病例发单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/queryReferralStatiscom", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryReferralStatiscom(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		JsonResult jsonResult = new JsonResult();

		ReferralCaseDmo caseDmo = new ReferralCaseDmo();

		List<ReferralStatisticsDto> referralStatisticsDtosList = referralStatisService
				.querySendReferralList(caseDmo);
		if (!CollectionUtils.isEmpty(referralStatisticsDtosList)) {

			for (ReferralStatisticsDto referralStatisticsDto : referralStatisticsDtosList) {

				referralStatisticsDto.setName(Constants.HOSPITAL_LEVEL_NAME_MAP
						.get(referralStatisticsDto.getName()));
			}

		}

		jsonResult.setSuccess(true);
		jsonResult.setData(referralStatisticsDtosList);

		return jsonResult;

	}

	/**
	 * 根据转诊患者类型统计转诊病例发单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryReceiveReferralStatuscom", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryReceiveReferralList(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		JsonResult jsonResult = new JsonResult();

		ReferralCaseDmo caseDmo = new ReferralCaseDmo();

		List<ReferralStatisticsDto> statisticsDtosList = referralStatisService
				.queryReceiveReferralListCount(caseDmo);
		if (!CollectionUtils.isEmpty(statisticsDtosList)) {

			for (ReferralStatisticsDto referralStatisticsDto : statisticsDtosList) {

				if (referralStatisticsDto.getName().equals(
						Constants.ReferralCaseType.ACUTE)) {
					referralStatisticsDto.setName("急性");
				} else if (referralStatisticsDto.getName().equals(
						Constants.ReferralCaseType.HIGH_RISK)) {
					referralStatisticsDto.setName("高危");
				}

			}

		}

		jsonResult.setSuccess(true);
		jsonResult.setData(statisticsDtosList);

		return jsonResult;

	}

	/**
	 * 根据医院等级统计转诊病例接单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryReceiveReferralRevcCom", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryReceiveReferralListcom(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		JsonResult jsonResult = new JsonResult();

		ReferralCaseDmo caseDmo = new ReferralCaseDmo();

		List<ReferralStatisticsDto> dtosList = referralStatisService
				.queryReceiveReferralList(caseDmo);

		if (!CollectionUtils.isEmpty(dtosList)) {

			for (ReferralStatisticsDto referralStatisticsDto : dtosList) {

				referralStatisticsDto.setName(Constants.HOSPITAL_LEVEL_NAME_MAP
						.get(referralStatisticsDto.getName()));

			}

		}

		jsonResult.setSuccess(true);
		jsonResult.setData(dtosList);

		return jsonResult;

	}
    /**
     * 根据患者类型统计转诊病例接单
     * @param request
     * @param response
     * @param model
     * @return
     */
	@RequestMapping(value = "/queryReceivePatientCom", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryReceivePatientCom(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		JsonResult jsonResult = new JsonResult();

		ReferralCaseDmo caseDmo = new ReferralCaseDmo();

		List<ReferralStatisticsDto> dtosList = referralStatisService
				.queryReceivePatientList(caseDmo);

		if (!CollectionUtils.isEmpty(dtosList)) {

			for (ReferralStatisticsDto referralStatisticsDto : dtosList) {

				if (referralStatisticsDto.getName().equals(
						Constants.ReferralCaseType.ACUTE)) {
					referralStatisticsDto.setName("急性");
				} else if (referralStatisticsDto.getName().equals(
						Constants.ReferralCaseType.HIGH_RISK)) {
					referralStatisticsDto.setName("高危");
				}

			}

		}

		jsonResult.setSuccess(true);
		jsonResult.setData(dtosList);

		return jsonResult;

	}
    /**
     * 根据月份统计发单转诊病例
     * @param request
     * @param response
     * @param model
     * @return
     */
	@RequestMapping(value = "/queryReceiveTiemCom", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryReceiveTiemCom(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		JsonResult jsonResult = new JsonResult();

		ReferralCaseDmo caseDmo = new ReferralCaseDmo();

		List<ReferralStatisticsDto> dtosList = referralStatisService
				.queryReceiveTiemList(caseDmo);

		if (!CollectionUtils.isEmpty(dtosList)) {

			for (ReferralStatisticsDto referralStatisticsDto : dtosList) {

				referralStatisticsDto.setName(referralStatisticsDto.getName()
						+ "月");

			}

		}

		jsonResult.setSuccess(true);
		jsonResult.setData(dtosList);

		return jsonResult;

	}
	
	/**
	 * 根据月份统计接单转诊病例
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/queryReferralTiemCom", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult queryReferralTiemCom(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		JsonResult jsonResult = new JsonResult();

		ReferralCaseDmo caseDmo = new ReferralCaseDmo();

		List<ReferralStatisticsDto> dtosList = referralStatisService.queryReferralTiemList(caseDmo);

		if (!CollectionUtils.isEmpty(dtosList)) {

			for (ReferralStatisticsDto referralStatisticsDto : dtosList) {

				referralStatisticsDto.setName(referralStatisticsDto.getName()
						+ "月");

			}

		}

		jsonResult.setSuccess(true);
		jsonResult.setData(dtosList);

		return jsonResult;

	}

}
