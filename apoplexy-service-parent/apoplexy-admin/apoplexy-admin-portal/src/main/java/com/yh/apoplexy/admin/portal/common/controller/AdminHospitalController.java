package com.yh.apoplexy.admin.portal.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.assist.utils.OperateLogUtil;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yh.apoplexy.admin.base.service.intf.AdminHospitalService;
import com.yh.apoplexy.admin.base.service.result.ImportHospitalResult;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddHospitalForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDeleteHospitalForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminHospitalDetailsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminHospitalForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateHospitalForm;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dto.admin.base.AdminHospitalDto;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.ExcelUtil;
import com.yh.apoplexy.common.utils.ImportFileUtil;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/hospital")
public class AdminHospitalController extends BaseController {

    @Autowired
    private AdminHospitalService adminHospitalService;

    @RequestMapping(value = "/hospitalcom", method = RequestMethod.GET)
    public String hospitalCom(HttpServletRequest request, Model model) {

        return baseView("hospital_info_manage");
    }

    /**
     * 查询医院列表
     *
     * @param request
     * @param response
     * @param model
     * @param adminHospitalForm
     * @return
     */
    @RequestMapping(value = "/queryHospital", method = RequestMethod.POST)
    public String queryHospital(HttpServletRequest request, HttpServletResponse response, Model model, AdminHospitalForm adminHospitalForm) {

        HospitalInfoDmo hospitalInfoDmo = new HospitalInfoDmo();
        String hospitalName = adminHospitalForm.getHospitalName();

        if (StringUtils.isNotBlank(hospitalName)) {
            hospitalInfoDmo.setHospitalName(hospitalName);
        }

        hospitalInfoDmo.setStatus(Constants.HospitalStatus.NORMAL);

        Page page = new Page();
        page.setPageSize(Integer.valueOf(adminHospitalForm.getPageSize()));
        page.setCurrentPage(Integer.valueOf(adminHospitalForm.getPageNum()));

        List<AdminHospitalDto> hospitalList = adminHospitalService.selectListByPage(hospitalInfoDmo, page);

        model.addAttribute("hospitalList", hospitalList);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return baseView("hospital_info_table");

    }

    /**
     * 查询医院详情
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryHospitalDetails", method = RequestMethod.POST)
    public String queryHospitalDetails(HttpServletRequest request, HttpServletResponse response, Model model, AdminHospitalDetailsForm admDetailsForm) {
        HospitalInfoDmo hospitalInfoDmo = new HospitalInfoDmo();
        String id = admDetailsForm.getId();

        hospitalInfoDmo.setId(Long.valueOf(id));

        HospitalInfoDmo hospitalDmo = adminHospitalService.selectHospitalDetails(hospitalInfoDmo);
        model.addAttribute(hospitalDmo);

        return baseView("");
    }

    /**
     * 新增医院
     *
     * @param addHospitalForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/addHospital", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult addHospital(AdminAddHospitalForm addHospitalForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String hospitalName = addHospitalForm.getHospitalName();
        String hospitalDesc = addHospitalForm.getHospitalDesc();
        String hospitalAddr = addHospitalForm.getHospitalAddr();
        String imageId = addHospitalForm.getImageId();
        String parentHospitalId = addHospitalForm.getParentHospitalId();
        String level = addHospitalForm.getLevel();
        String lon = addHospitalForm.getLon();
        String lat = addHospitalForm.getLat();
        String areaCode = addHospitalForm.getAreaCode();
        String union = addHospitalForm.getUnion();

        AdminHospitalDto adminHospitalDto = new AdminHospitalDto();

        adminHospitalDto.setHospitalName(hospitalName);
        adminHospitalDto.setHospitalAddr(hospitalAddr);
        adminHospitalDto.setHospitalDesc(hospitalDesc);
        if (StringUtils.isNotBlank(imageId)){
            adminHospitalDto.setImageId(imageId);
        }
        adminHospitalDto.setParentHospitalId(parentHospitalId);
        adminHospitalDto.setLevel(level);
        adminHospitalDto.setLon(lon);
        adminHospitalDto.setLat(lat);
        adminHospitalDto.setAreaCode(areaCode);
        adminHospitalDto.setHospitalUnion(union);

        Result result = adminHospitalService.addHospitalDetails(adminHospitalDto);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail(result.getErrorCode());
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.ADD_HOSPITAL,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.ADD_HOSPITAL);

        return jsonResult;

    }

    /**
     * 修改医院
     *
     * @param adminUpdateHospitalForm
     * @param request
     * @param response
     * @param model
     * @return
     */

    @RequestMapping(value = "/updateHospital", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateHospital(AdminUpdateHospitalForm adminUpdateHospitalForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = adminUpdateHospitalForm.getId();
        String hospitalName = adminUpdateHospitalForm.getHospitalName();
        String hospitalDesc = adminUpdateHospitalForm.getHospitalDesc();
        String hospitalAddr = adminUpdateHospitalForm.getHospitalAddr();
        String parentHospitalId = adminUpdateHospitalForm.getParentHospitalId();
        String imageId = adminUpdateHospitalForm.getImageId();
        String level = adminUpdateHospitalForm.getLevel();
        String lon = adminUpdateHospitalForm.getLon();
        String lat = adminUpdateHospitalForm.getLat();
        String areaCode = adminUpdateHospitalForm.getAreaCode();
        String union = adminUpdateHospitalForm.getUnion();

        AdminHospitalDto adminHospitalDto = new AdminHospitalDto();

        adminHospitalDto.setHospitalName(hospitalName);
        adminHospitalDto.setHospitalDesc(hospitalDesc);
        adminHospitalDto.setHospitalAddr(hospitalAddr);
        adminHospitalDto.setParentHospitalId(parentHospitalId);
        if (StringUtils.isNotBlank(imageId)){
            adminHospitalDto.setImageId(imageId);
        }
        adminHospitalDto.setId(id);
        adminHospitalDto.setLevel(level);
        adminHospitalDto.setLon(lon);
        adminHospitalDto.setLat(lat);
        adminHospitalDto.setAreaCode(areaCode);
        adminHospitalDto.setHospitalUnion(union);

        Result result = adminHospitalService.updateHospitalDetails(adminHospitalDto);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail(result.getErrorCode());
        }
        
        UserSession userSession = getUserSession(request);
        
        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_HOSPITAL,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_HOSPITAL);
        
        return jsonResult;

    }

    /**
     * 删除医院
     *
     * @param adminDeleteHospitalForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteHospital", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteHospital(AdminDeleteHospitalForm adminDeleteHospitalForm, HttpServletRequest request,
                              HttpServletResponse response, Model model) {
        JsonResult jsonResult = new JsonResult();
        String id = adminDeleteHospitalForm.getId();
        HospitalInfoDmo hospitalInfoDmo = new HospitalInfoDmo();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }
        hospitalInfoDmo.setId(Long.valueOf(id));

        Result result = adminHospitalService.deleteHospitalDetails(hospitalInfoDmo);
        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail(result.getErrorCode());
        }
        
        UserSession userSession = getUserSession(request);
        
        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.DELETE_HOSPITAL,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.DELETE_HOSPITAL);
        
        return jsonResult;

    }

    /**
     * 导入医院信息
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importHospital", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    JsonResult importHospital(HttpServletRequest request, Model model) {

        JsonResult jsonResult = new JsonResult();
        ImportHospitalResult parseResult = new ImportHospitalResult();
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
            parseResult = adminHospitalService.parseImportExcel(data);
        }

        if (parseResult.isSuccess()) {
            parseResult.setSourceFileName(originalFileName);
            parseResult.setServerFileName(serverFileName);
            jsonResult.success(parseResult);
        }

        toJsonResult(jsonResult, parseResult);
        
        UserSession userSession = getUserSession(request);
        
        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.IMPORT_HOSPITAL,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.IMPORT_HOSPITAL);
        
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

    
    @RequestMapping(value = "/queryHospitalList", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryHospitalList(HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        HospitalInfoDmo con = new HospitalInfoDmo();

        con.setStatus(Constants.HospitalStatus.NORMAL);

        List<HospitalInfoDmo> hospitalInfoDmoList = adminHospitalService.selectList(con);

        jsonResult.setSuccess(true);
        jsonResult.setData(hospitalInfoDmoList);
        
        


        return jsonResult;
    }
}
