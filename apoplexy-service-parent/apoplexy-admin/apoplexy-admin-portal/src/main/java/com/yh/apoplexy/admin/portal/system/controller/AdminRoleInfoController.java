package com.yh.apoplexy.admin.portal.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yh.apoplexy.admin.portal.system.form.UpdateRoleMenuForm;
import com.yh.apoplexy.assist.dmo.admin.AdminRoleInfoDmo;
import com.yh.apoplexy.assist.dto.system.MenuTreeNodeDto;
import com.yh.apoplexy.assist.dto.system.QueryMenuTreeNodeDto;
import com.yh.apoplexy.assist.dto.system.RoleMenuInfoDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yjh.framework.utils.DateUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddRoleInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDeleteRoleInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminRoleInfoDetailsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminRoleInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateRoleInfoForm;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.system.service.intf.AdminRoleInfoService;
import com.yh.apoplexy.assist.dto.admin.system.AdminRoleInfoDetailsDto;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/roleInfo")
public class AdminRoleInfoController extends BaseController {

	@Autowired
	private AdminRoleInfoService adminRoleInfoService;

	@RequestMapping(value = "/roleInfocom", method = RequestMethod.GET)
	public String doctorNewscom(HttpServletRequest request, Model model) {

		return systemView("adminroleinfo");
	}

	/**
	 * 查询角色列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param adminRoleInfoForm
	 * @return
	 */
	@RequestMapping(value = "/queryRoleInfo", method = RequestMethod.POST)
	public String queryRoleInfo(HttpServletRequest request,
			HttpServletResponse response, Model model,
			AdminRoleInfoForm adminRoleInfoForm) {
		String roleName = adminRoleInfoForm.getRoleName();
		String pageNum = adminRoleInfoForm.getPageNum();
		String pageSize = adminRoleInfoForm.getPageSize();
		AdminRoleInfoDmo adminRoleInfo = new AdminRoleInfoDmo();
		if (StringUtils.isNotBlank(roleName)) {
			adminRoleInfo.setRoleName(roleName);
		}
		Page page = new Page();
		if (StringUtils.isNotBlank(pageSize)) {
			page.setPageSize(Integer.valueOf(pageSize));
		}

		if (StringUtils.isNotBlank(pageNum)) {
			page.setCurrentPage(Integer.valueOf(pageNum));
		}

		List<AdminRoleInfoDmo> AdminRoleInfoList = adminRoleInfoService
				.selectListByPage(adminRoleInfo, page);

		model.addAttribute("AdminRoleInfoList", AdminRoleInfoList);
		model.addAttribute("page", PageVo.createPageVo(request, page));
		return systemView("adminroleinfotable");

	}

	/**
	 * 查询角色详情
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param adminRoleInfoDetailsForm
	 * @return
	 */
	@RequestMapping(value = "/queryRoleInfoDetails", method = RequestMethod.POST)
	public String queryRoleInfoDetails(HttpServletRequest request,
			HttpServletResponse response, Model model,
			AdminRoleInfoDetailsForm adminRoleInfoDetailsForm) {

		String id = adminRoleInfoDetailsForm.getId();
		String pageNum = adminRoleInfoDetailsForm.getPageNum();
		String pageSize = adminRoleInfoDetailsForm.getPageSize();
        AdminRoleInfoDmo adminRoleInfo = new AdminRoleInfoDmo();
		if (!StringUtils.isBlank(id)) {
			adminRoleInfo.setId(Long.valueOf(id));
		}
		Page page = new Page();
		if (!StringUtils.isBlank(pageSize)) {
			page.setPageSize(Integer.valueOf(pageSize));
		}
		if (!StringUtils.isBlank(pageNum)) {
			page.setCurrentPage(Integer.valueOf(pageNum));
		}
		List<AdminRoleInfoDetailsDto> adminRoleInfoDetailsList = adminRoleInfoService.selectOne(adminRoleInfo, page);

		model.addAttribute("adminRoleInfoDetailsList", adminRoleInfoDetailsList);
		model.addAttribute("page", PageVo.createPageVo(request, page));
		return baseView("");

	}
	
    /**
     * 新增角色
     * @param addRoleInfoForm
     * @param request
     * @param response
     * @param model
     * @return
     */
	@RequestMapping(value = "/addRoleInfo", method = RequestMethod.POST)
	public @ResponseBody JsonResult addRoleInfo(AdminAddRoleInfoForm addRoleInfoForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String roleName = addRoleInfoForm.getRoleName();

        if(StringUtils.isBlank(roleName)){
			jsonResult.fail("参数异常");
			return jsonResult;
		}

        AdminRoleInfoDmo adminRoleInfo = new AdminRoleInfoDmo();

        adminRoleInfo.setRoleName(roleName);
        adminRoleInfo.setDescription(addRoleInfoForm.getDescription());
        adminRoleInfo.setCreateTime(DateUtil.getDate());
        adminRoleInfo.setLastUpdateTime(DateUtil.getDate());
        adminRoleInfo.setStatus(Constants.RoleStatus.NORMAL);

        Result result = adminRoleInfoService.addRoleInfo(adminRoleInfo);
        
		if (result.isSuccess()) {
			jsonResult.setSuccess(true);
		} else {
			jsonResult.fail("新增角色失败");
		}

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.ADD_ROLEINFO,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.ADD_ROLEINFO);
		
		return jsonResult;

	}
	/**
	 * 修改角色
	 * @param updateRoleInfoForm
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateRoleInfo", method = RequestMethod.POST)
    public @ResponseBody JsonResult updateRoleInfo(AdminUpdateRoleInfoForm updateRoleInfoForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        if(StringUtils.isBlank(updateRoleInfoForm.getId())){
			jsonResult.fail("参数异常");
			return jsonResult;
		}

        AdminRoleInfoDmo adminRoleInfo = new AdminRoleInfoDmo();

        adminRoleInfo.setId(Long.valueOf(updateRoleInfoForm.getId()));
        adminRoleInfo.setRoleName(updateRoleInfoForm.getRoleName());
        adminRoleInfo.setDescription(updateRoleInfoForm.getDescription());
        adminRoleInfo.setLastUpdateTime(DateUtil.getDate());

		Result result = adminRoleInfoService.updateRoleInfo(adminRoleInfo);
		
		if (result.isSuccess()) {
			jsonResult.setSuccess(true);
		} else {
			jsonResult.fail("修改角色失败");
		}
		
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_ROLEINFO,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_ROLEINFO);
		
		
		return jsonResult;
		
	}
	/**
	 * 删除角色
	 * @param deleteRoleInfoForm
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteRoleInfo", method = RequestMethod.POST)
    public @ResponseBody
	JsonResult deleteRoleInfo(AdminDeleteRoleInfoForm deleteRoleInfoForm,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JsonResult jsonResult = new JsonResult();
        AdminRoleInfoDmo adminRoleInfo = new AdminRoleInfoDmo();
		String id = deleteRoleInfoForm.getId();
		if(StringUtils.isBlank(id)){
			jsonResult.fail("参数异常");
			return jsonResult;
		}
		adminRoleInfo.setId(Long.valueOf(id));
		Result result  = adminRoleInfoService.DeleteRoleInfo(adminRoleInfo);
		
		if (result.isSuccess()) {
			jsonResult.setSuccess(true);
		} else {
			jsonResult.fail("删除角色失败");
		}
		
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.DELETE_ROLEINFO,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.DELETE_ROLEINFO);
		
		
		return jsonResult;
		
	}

    @RequestMapping(value = "/queryMenuList/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult queryMenuListOfRole(HttpServletRequest request, Model model, @PathVariable(value = "id") String id) {

        JsonResult jsonResult = new JsonResult();

        QueryMenuTreeNodeDto con = new QueryMenuTreeNodeDto();

        con.setRoleId(id);
        con.setSubMenuLevel(Constants.MenuLevel.SECOND);

        //选取出所有的菜单
        List<MenuTreeNodeDto> menuList = adminRoleInfoService.queryMenuTreeByRole(con);

        jsonResult.setData(menuList);

        jsonResult.setSuccess(true);

        return jsonResult;
    }

    @RequestMapping(value = "/updateRoleMenu", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateRoleMenu(HttpServletRequest request, Model model, UpdateRoleMenuForm roleMenuForm) {

        JsonResult jsonResult = new JsonResult();

        RoleMenuInfoDto roleMenuInfoDto = new RoleMenuInfoDto();

        roleMenuInfoDto.setRoleId(roleMenuForm.getRoleId());

        List<Long> menuIdList = JSON.parseArray(roleMenuForm.getMenuIdList(),Long.class);

        roleMenuInfoDto.setMenuIdList(menuIdList);

        Result result = adminRoleInfoService.updateRoleMenuInfo(roleMenuInfoDto);

        if (!result.isSuccess()){

            jsonResult.fail(result.getMessage());
            return jsonResult;

        }

        jsonResult.setSuccess(true);
        return jsonResult;
    }


}
