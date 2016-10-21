package com.yh.apoplexy.admin.portal.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.base.service.intf.AdminHospitalService;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yh.apoplexy.admin.base.service.intf.AdminDoctorService;
import com.yh.apoplexy.admin.base.service.result.ImportDoctorResult;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddDoctorForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDeleteDoctorForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDoctorDetailsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDoctorForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateDoctorForm;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dto.admin.common.AdminDoctorInfoDto;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yh.apoplexy.common.utils.ExcelUtil;
import com.yh.apoplexy.common.utils.ImportFileUtil;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/doctor")
public class AdminDoctorController extends BaseController {

	@Autowired
	private AdminDoctorService adminDoctorService;

    @Autowired
    private AdminHospitalService adminHospitalService;
    
	@RequestMapping(value = "/doctorcom", method = RequestMethod.GET)
	public String doctorCom(HttpServletRequest request, Model model) {

		return baseView("doctor_info");
	}

	/**
	 * 查询医生信息列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param adminDoctorFrom
	 * @return
	 */

	@RequestMapping(value = "/queryDoctor", method = RequestMethod.POST)
	public String queryHospital(HttpServletRequest request,
			HttpServletResponse response, Model model,
			AdminDoctorForm adminDoctorFrom) {
	    String DoctorName = adminDoctorFrom.getDoctorName();
	    String Hospital = adminDoctorFrom.getHospital();
	    String status = Constants.DoctorStatus.NORMAL;
		DoctorInfoDmo doctorInfoDmo = new DoctorInfoDmo();
		
           if(StringUtils.isNotBlank(Hospital)){
        	
        	   doctorInfoDmo.setHospital(Hospital);
           }
           if(StringUtils.isNotBlank(DoctorName)){
        	   doctorInfoDmo.setDoctorName(DoctorName);
           }
           
           if(StringUtils.isNotBlank(status)){
        	   doctorInfoDmo.setStatus(status);
           }
	    
		Page page = new Page();
		page.setCurrentPage(Integer.valueOf(adminDoctorFrom.getPageNum()));
		page.setPageSize(Integer.valueOf(adminDoctorFrom.getPageSize()));
		List<AdminDoctorInfoDto> doctorList = adminDoctorService.selectListByPage(doctorInfoDmo, page);

		model.addAttribute("doctorList", doctorList);
		model.addAttribute("page", PageVo.createPageVo(request, page));

        return baseView("doctor_table");

	}

	/**
	 * 查询医生详情
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param adminDoctorDetailsForm
	 * @return
	 */
	@RequestMapping(value = "/queryDoctorDetails", method = RequestMethod.POST)
	public String queryDoctorDetails(HttpServletRequest request,
			HttpServletResponse response, Model model,
			AdminDoctorDetailsForm adminDoctorDetailsForm) {
		
		String id = adminDoctorDetailsForm.getId();

		DoctorInfoDmo doctorInfoDmo = new DoctorInfoDmo();

		 doctorInfoDmo.setId(Long.valueOf(id));

		DoctorInfoDmo infoDmo = adminDoctorService
				.selectDoctorDetails(doctorInfoDmo);
		model.addAttribute(infoDmo);
		return baseView("");

	}
   /**
   * 新增医生
   * @param addDoctorForm
   * @param request
   * @param response
   * @param model
   * @return
   */
	@RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult addHospital(AdminAddDoctorForm addDoctorForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String doctorName = addDoctorForm.getDoctorName();
		String hospital = addDoctorForm.getHospital();
		String department = addDoctorForm.getDepartment();
		String title = addDoctorForm.getTitle();
		String job = addDoctorForm.getJob();
		String sex = addDoctorForm.getSex();
		String phone = addDoctorForm.getPhone();
        String avatar = addDoctorForm.getAvatar();

		Date date = DateUtil.getDate();
		Date comingDate = DateUtil.addDays(date, 31);
		Date beforeDate = DateUtil.beforeDate(date, 1);

		String email = addDoctorForm.getEmail();
		if (StringUtils.isBlank(doctorName) || StringUtils.isBlank(hospital) ||StringUtils.isBlank(sex)||StringUtils.isBlank(phone)) {
			jsonResult.fail("参数异常");
            return jsonResult;
		}

        HospitalInfoDmo hospitalInfoCon = new HospitalInfoDmo();

        hospitalInfoCon.setId(Long.parseLong(hospital));
        hospitalInfoCon.setStatus(Constants.HospitalStatus.NORMAL);

        HospitalInfoDmo hospitalInfoDmo = adminHospitalService.selectHospitalDetails(hospitalInfoCon);

        if (null == hospitalInfoDmo){

            jsonResult.fail("医院不存在，参数异常");
            return jsonResult;
        }

		DoctorInfoDmo doctorInfoDmo = new DoctorInfoDmo();
		doctorInfoDmo.setDoctorName(doctorName);
		doctorInfoDmo.setHospital(hospitalInfoDmo.getHospitalName());
		doctorInfoDmo.setDepartment(department);
		doctorInfoDmo.setTitle(title);
		doctorInfoDmo.setJob(job);

        if (StringUtils.isNotBlank(addDoctorForm.getEffectiveTime())){
            Date effectiveTime = DateUtil.parseDate(addDoctorForm.getEffectiveTime(),DateUtil.yyyy_MM_dd);

            if(effectiveTime.after(comingDate) || effectiveTime.before(beforeDate)){

                jsonResult.fail("有效时间不能小于当前时间，也不能大于当前时间推后一个月时间");
                return jsonResult;
            }
            doctorInfoDmo.setEffectiveTime(effectiveTime);

        }

		doctorInfoDmo.setCreateTime(DateUtil.getDate());
		doctorInfoDmo.setSex(sex);
		doctorInfoDmo.setPhone(phone);
		doctorInfoDmo.setEmail(email);
		doctorInfoDmo.setLastUpdateTime(DateUtil.getDate());
		doctorInfoDmo.setStatus(Constants.DoctorStatus.NORMAL);
        doctorInfoDmo.setAvatar(avatar);

        DoctorInfoDmo doctorInfoCon = new DoctorInfoDmo();

        doctorInfoCon.setPhone(phone);
        doctorInfoCon.setStatus(Constants.DoctorStatus.NORMAL);

        DoctorInfoDmo existDoctorInfo = adminDoctorService.selectDoctorDetails(doctorInfoCon);

        if (null != existDoctorInfo){
            jsonResult.fail("手机号码已经绑定了医生，新增医生信息失败");
            return jsonResult;
        }

		Result result = adminDoctorService.addDoctorDetails(doctorInfoDmo);

        if(result.isSuccess()){
			jsonResult.setSuccess(true);
		}else{
			jsonResult.fail("新增医生信息失败");
		}
		
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.ADD_DOCTOR,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.ADD_DOCTOR);
		
		return jsonResult;

	}
	
	/**
	 * 修改医生信息
	 * @param updateDoctorForm
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateDoctor", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult updateHospital(AdminUpdateDoctorForm updateDoctorForm, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		JsonResult jsonResult = new JsonResult();

        String doctorName = updateDoctorForm.getDoctorName();
		String hospital = updateDoctorForm.getHospital();
		String department = updateDoctorForm.getDepartment();
		String title = updateDoctorForm.getTitle();
		String job = updateDoctorForm.getJob();
		String id =updateDoctorForm.getId();
		String phone = updateDoctorForm.getPhone();
		String email = updateDoctorForm.getEmail();
		String sex  = updateDoctorForm.getSex();
        String avatar = updateDoctorForm.getAvatar();

		if(StringUtils.isBlank(doctorName) || StringUtils.isBlank(hospital)
                ||StringUtils.isBlank(id)||StringUtils.isBlank(sex)||StringUtils.isBlank(phone)){
			jsonResult.fail("参数异常");
			return jsonResult;
		}

		DoctorInfoDmo doctorInfoDmo = new DoctorInfoDmo();
		doctorInfoDmo.setDoctorName(doctorName);
		doctorInfoDmo.setHospital(hospital);
		doctorInfoDmo.setDepartment(department);
		doctorInfoDmo.setTitle(title);
		doctorInfoDmo.setJob(job);

        if (StringUtils.isNotBlank(updateDoctorForm.getEffectiveTime())){
            Date effectiveTime =DateUtil.parseDate(updateDoctorForm.getEffectiveTime(),DateUtil.yyyy_MM_dd);
            doctorInfoDmo.setEffectiveTime(effectiveTime);

        }

		doctorInfoDmo.setLastUpdateTime(DateUtil.getDate());
		doctorInfoDmo.setPhone(phone);
		doctorInfoDmo.setEmail(email);
		doctorInfoDmo.setSex(sex);
		doctorInfoDmo.setId(Long.valueOf(id));

        if (StringUtils.isNotBlank(avatar)){
            doctorInfoDmo.setAvatar(avatar);
        }

        DoctorInfoDmo doctorInfoCon = new DoctorInfoDmo();

        doctorInfoCon.setPhone(phone);
        doctorInfoCon.setStatus(Constants.DoctorStatus.NORMAL);

        List<DoctorInfoDmo> existDoctorInfoList = adminDoctorService.selectList(doctorInfoCon);

        if (!CollectionUtils.isEmpty(existDoctorInfoList)&&existDoctorInfoList.get(0).getId()!= Long.parseLong(id)){
            jsonResult.fail("手机号码已经绑定了医生，更新医生信息失败");
            return jsonResult;
        }

		Result result = adminDoctorService.updateDoctorDetails(doctorInfoDmo);

        if(result.isSuccess()){
			jsonResult.setSuccess(true);
		}else{
			jsonResult.fail("修改医生信息失败");
		}
		
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_DOCTOR,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_DOCTOR);
		
				return jsonResult;
		
	}
	/**
	 * 删除医生信息
	 * @param deleteDoctorForm
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteDoctor", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult deleteHospital(AdminDeleteDoctorForm deleteDoctorForm,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JsonResult jsonResult = new JsonResult();
		String id= deleteDoctorForm.getId();
		if(StringUtils.isBlank(id)){
			jsonResult.fail("参数异常");
			return jsonResult;
		}
		DoctorInfoDmo doctorInfoDmo = new DoctorInfoDmo();
		doctorInfoDmo.setId(Long.valueOf(id));
		Result result = adminDoctorService.deleteDoctorDetails(doctorInfoDmo);
		if(result.isSuccess()){
			jsonResult.setSuccess(true);
		}else{
			jsonResult.fail("删除医生失败");
		}
		
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.DELETE_DOCTOR,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.DELETE_DOCTOR);
        
				return jsonResult;
	}
	
	/***
	 * 导入医生
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/importDoctor", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	JsonResult importDoctor(HttpServletRequest request, Model model) {
		JsonResult jsonResult = new JsonResult();
		ImportDoctorResult parseResult = new ImportDoctorResult();
		String filePath = request.getParameter("filePath");
		String serverFileName = null;
		MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartHttpservletRequest.getFile(filePath);
		if (multipartFile.isEmpty()) {
			jsonResult.fail("读取文件错误");
			return jsonResult;
		}

		InputStream inputStream = null;
		try {
			inputStream = multipartFile.getInputStream();
		} catch (IOException e) {
			jsonResult.fail("读取文件流错误");
			return jsonResult;
		}
		
		if (StringUtils.isBlank(filePath) || filePath.length() > 300) {
			jsonResult.fail("文件路径不能为空或超过300个字符");
			return jsonResult;
		}

		String originalFileName = multipartFile.getOriginalFilename();
		serverFileName = getFilePathName(originalFileName);
		String servrPath = getServePath(serverFileName);
		if (parseResult.isSuccess()) {
			boolean saveFile = ImportFileUtil.saveFile(inputStream, servrPath);
			if (!saveFile) {
				jsonResult.fail("保存上传文件发生异常，请重新上传或联系运维人员");
				return jsonResult;
			}
		}

		// 校验文件内容有效性
		if (parseResult.isSuccess()) {
			List<LinkedHashMap<String, Object>> data = ExcelUtil.readExcel(servrPath);
			if (null == data) {
				jsonResult.fail("解析文件异常，请重新上传或联系运维人员");
				return jsonResult;
			}
			parseResult = adminDoctorService.parseImportExcel(data);
		}

		if (parseResult.isSuccess()) {
			parseResult.setSourceFileName(originalFileName);
			parseResult.setServerFileName(serverFileName);
			jsonResult.success(parseResult);
		}

		toJsonResult(jsonResult, parseResult);
		
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.IMPORT_DOCTOR,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.IMPORT_DOCTOR);
		
		return jsonResult;
	}
	
	private String getFilePathName(String originalFileName) {
		originalFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".xlsx"));
		String date = DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddhhmmss);
		return originalFileName + date + ".xlsx";
	}

	private String getServePath(String serverFileName) {
		return PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG,
				PropertiesConstants.YH_HOSPITAL_IMPORT_PATH) + serverFileName;
	}
}
