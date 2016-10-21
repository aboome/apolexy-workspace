package com.yh.apoplexy.admin.portal.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.portal.common.controller.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.system.service.intf.AdminRoleInfoService;
import com.yh.apoplexy.admin.system.service.intf.AdminUserRoleService;
import com.yh.apoplexy.admin.system.service.intf.AdminUserService;
import com.yh.apoplexy.assist.dmo.admin.AdminInfoDmo;
import com.yh.apoplexy.assist.dmo.admin.AdminRoleInfoDmo;
import com.yh.apoplexy.assist.dmo.admin.AdminUserRoleDmo;
import com.yh.apoplexy.assist.dto.admin.system.AdminInfoDto;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/userManagement")
public class AdminUserManagementController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminRoleInfoService adminRoleInfoService;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @RequestMapping(value = "/userManagementcom", method = RequestMethod.GET)
    public String userManger(HttpServletRequest request, Model model) {

        AdminRoleInfoDmo con = new AdminRoleInfoDmo();

        con.setStatus(Constants.RoleStatus.NORMAL);

        List<AdminRoleInfoDmo> adminRoleInfoDmoList = adminRoleInfoService.selectList(con);

        model.addAttribute("roleInfoList", adminRoleInfoDmoList);

        return systemView("admin");
    }

    /**
     * 查询用户列表
     *
     * @param request
     * @param response
     * @param model
     * @param adminUserManagementForm
     * @return
     */
    @RequestMapping(value = "/queryUserManagement", method = RequestMethod.POST)
    public String queryUserManagement(HttpServletRequest request, HttpServletResponse response, Model model, AdminUserManagementForm adminUserManagementForm) {

        String userName = adminUserManagementForm.getUserName();

        String pageNum = adminUserManagementForm.getPageNum();

        String pageSize = adminUserManagementForm.getPageSize();

        AdminInfoDmo adminInfoDmo = new AdminInfoDmo();

        if (!StringUtils.isBlank(userName)) {
            adminInfoDmo.setUserName(userName);
        }

        Page page = new Page();

        if (!StringUtils.isBlank(pageNum)) {
            page.setCurrentPage(Integer.valueOf(pageNum));
        }

        if (!StringUtils.isBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }

        List<AdminInfoDto> AdminInfoList = adminUserService.selectListByPage(adminInfoDmo, page);

        model.addAttribute("adminList", AdminInfoList);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return systemView("admintable");
    }

    /**
     * 新增用户信息
     *
     * @param addUserManagementForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/addUserManagement", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult addUserManagement(AdminAddUserManagementForm addUserManagementForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String userName = addUserManagementForm.getUserName();
        String realName = addUserManagementForm.getRealName();
        String phone = addUserManagementForm.getPhone();
        String job = addUserManagementForm.getJob();
        String userDesc = addUserManagementForm.getUserDesc();
        String email = addUserManagementForm.getEmail();
        String passWord = addUserManagementForm.getPassWord();

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        AdminInfoDmo adminInfoDmo = new AdminInfoDmo();
        adminInfoDmo.setUserName(userName);
        adminInfoDmo.setRealName(realName);
        adminInfoDmo.setPassWord(passWord);
        adminInfoDmo.setPhone(phone);
        adminInfoDmo.setEmail(email);
        adminInfoDmo.setJob(job);
        adminInfoDmo.setUserDesc(userDesc);
        adminInfoDmo.setCreateTime(DateUtil.getDate());
        adminInfoDmo.setLastUpdateTime(DateUtil.getDate());
        adminInfoDmo.setLoginFailCount(0L);
        adminInfoDmo.setStatus(Constants.MemberStatus.NORMAL);

        Result result = adminUserService.addUser(adminInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail(result.getErrorCode());
        }
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.ADD_USERMANAGEMENT,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.ADD_USERMANAGEMENT);
        
        
        return jsonResult;

    }

    /**
     * 修改用户信息
     *
     * @param adminUpdateUserManagement
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateUserManagement", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateUserManagement(AdminUpdateUserManagement adminUpdateUserManagement, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String userName = adminUpdateUserManagement.getUserName();
        String realName = adminUpdateUserManagement.getRealName();
        String phone = adminUpdateUserManagement.getPhone();
        String job = adminUpdateUserManagement.getJob();
        String id = adminUpdateUserManagement.getId();
        String userDesc = adminUpdateUserManagement.getUserDesc();
        String email = adminUpdateUserManagement.getEmail();
        AdminInfoDmo adminInfoDmo = new AdminInfoDmo();

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        adminInfoDmo.setUserName(userName);
        adminInfoDmo.setRealName(realName);
        adminInfoDmo.setPhone(phone);
        adminInfoDmo.setJob(job);
        adminInfoDmo.setId(Long.valueOf(id));
        adminInfoDmo.setLastUpdateTime(DateUtil.getDate());
        adminInfoDmo.setUserDesc(userDesc);
        adminInfoDmo.setEmail(email);

        Result result = adminUserService.updateUser(adminInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改用户信息失败");
        }
        
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_USERMANAGEMENT,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_USERMANAGEMENT);
        
        return jsonResult;

    }

    /**
     * 删除用户信息
     *
     * @param adminDeleteUserManagementForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteUserManagement", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult DeleteUserManagement(AdminDeleteUserManagementForm adminDeleteUserManagementForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = adminDeleteUserManagementForm.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        AdminInfoDmo adminInfoDmo = new AdminInfoDmo();

        adminInfoDmo.setId(Long.valueOf(id));

        Result result = adminUserService.deleteUser(adminInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除用户信息失败");
        }
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.DELETE_USERMANAGEMENT,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.DELETE_USERMANAGEMENT);

        return jsonResult;

    }

    /**
     * 解锁用户
     *
     * @param adminUnlockUserForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/unlockUser", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult unlockUser(AdminUnlockUserForm adminUnlockUserForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String userId = adminUnlockUserForm.getId();

        if (StringUtils.isBlank(userId)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        AdminInfoDmo adminInfoDmo = new AdminInfoDmo();

        adminInfoDmo.setId(Long.valueOf(userId));

        Result result = adminUserService.unlockUser(adminInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("解锁用户失败");
        }
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UNLOCK_USER,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UNLOCK_USER);
        
        
        return jsonResult;

    }

    /**
     * 绑定角色
     *
     * @param adminBindingRoleForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bindingRole", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult bindingRole(AdminBindingRoleForm adminBindingRoleForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String userId = adminBindingRoleForm.getUserId();
        String roleId = adminBindingRoleForm.getRoleId();

        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(userId)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        AdminUserRoleDmo adminUserRoleDmo = new AdminUserRoleDmo();

        adminUserRoleDmo.setRoleId(Long.valueOf(roleId));
        adminUserRoleDmo.setUserId(Long.valueOf(userId));

        Result result = adminUserRoleService.bindRole(adminUserRoleDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("绑定角色失败");
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.BINDING_USER,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.BINDING_USER);
        
        return jsonResult;

    }

    /**
     * 重置管理员密码
     *
     * @param adminUpdateUserManagement
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult resetPassword(AdminUpdateUserManagement adminUpdateUserManagement, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = adminUpdateUserManagement.getId();
        String password = adminUpdateUserManagement.getPassWord();

        AdminInfoDmo adminInfoDmo = new AdminInfoDmo();

        if (StringUtils.isBlank(password) || StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        adminInfoDmo.setId(Long.parseLong(id));
        adminInfoDmo.setPassWord(password);
        adminInfoDmo.setLastUpdateTime(DateUtil.getDate());

        Result result = adminUserService.updateUser(adminInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("重置管理员密码失败");
        }
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.RESET_PASSWORD,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.RESET_PASSWORD);
        
        
        return jsonResult;

    }

    /**
     * 修改管理员密码
     *
     * @param modifyAdminPasswordForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult modifyPassword(ModifyAdminPasswordForm modifyAdminPasswordForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        UserSession userSession = getUserSession(request);

        Long id = Long.parseLong(userSession.getUserId());

        String oldPassword = modifyAdminPasswordForm.getOldPassword();
        String newPassword = modifyAdminPasswordForm.getNewPassword();

        AdminInfoDmo con = new AdminInfoDmo();

        con.setId(id);
        con.setStatus(Constants.MemberStatus.NORMAL);

        AdminInfoDmo adminInfoDmo = adminUserService.selectOne(con);

        if (null  == adminInfoDmo){

            jsonResult.fail("管理员不存在，修改管理员密码失败");
            return jsonResult;
        }

        if (StringUtils.isBlank(adminInfoDmo.getPassWord())||!adminInfoDmo.getPassWord().equalsIgnoreCase(oldPassword)){

            jsonResult.fail("旧密码错误，修改管理员密码失败");
            return jsonResult;

        }

        adminInfoDmo.setPassWord(newPassword);

        Result result = adminUserService.updateUser(adminInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改管理员密码失败");
        }

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.MODIFY_PASSWORD,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.MODIFY_PASSWORD);


        return jsonResult;

    }
}
