package com.yh.apoplexy.admin.portal.system.controller;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.system.form.LoginForm;
import com.yh.apoplexy.admin.system.dto.AdminLoginDto;
import com.yh.apoplexy.admin.system.result.AdminLoginResult;
import com.yh.apoplexy.admin.system.service.intf.AdminMemberService;
import com.yh.apoplexy.assist.dmo.admin.AdminInfoDmo;
import com.yh.apoplexy.common.dto.UserSession;
import com.yh.apoplexy.common.utils.SessionUtil;
import com.yjh.framework.web.result.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * admin登录控制器
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private AdminMemberService adminMemberService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {

        UserSession userSession = getUserSession(request);

        if (null != userSession) {
            return redirect("/main");
        }

        return commonView("login");
    }

    /**
     * 登录
     * @param loginForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult loginSubmit(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        if (StringUtils.isBlank(loginForm.getUserName()) || StringUtils.isBlank(loginForm.getPassword())) {
            jsonResult.fail("用户名或者密码为空");
            return jsonResult;
        }

        AdminLoginDto loginDto = new AdminLoginDto();

        loginDto.setPassword(loginForm.getPassword());
        loginDto.setUserName(loginForm.getUserName());

        AdminLoginResult result = adminMemberService.login(loginDto);

        if (!result.isSuccess()) {
            jsonResult.fail(result.getMessage());
            return jsonResult;
        }

        return loginSuccess(request, result.getAdminInfoDmo());

    }

    /**
     * 处理登录成功
     * @param request
     * @param adminInfo
     * @return
     */
    private JsonResult loginSuccess(HttpServletRequest request, AdminInfoDmo adminInfo) {

        JsonResult jsonResult = new JsonResult(true);
        // 设置登录session
        UserSession userSession = new UserSession();
        userSession.setUserId(String.valueOf(adminInfo.getId()));
        userSession.setName(adminInfo.getUserName());
        SessionUtil.setUserSession(request, userSession, true);

        // 设置 application信息，主要是为了保证一次只有一个用户登录
        //ApplicationUtil.addLoginUser(request, String.valueOf(adminInfo.getId()));

        return jsonResult;
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {

        SessionUtil.invalidSession(request);
        return redirect("/login");
    }
}
