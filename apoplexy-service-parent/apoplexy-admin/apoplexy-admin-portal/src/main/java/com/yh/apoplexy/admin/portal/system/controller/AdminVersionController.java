package com.yh.apoplexy.admin.portal.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.system.form.AdminVersionForm;
import com.yh.apoplexy.admin.system.service.intf.AdminVersionService;
import com.yh.apoplexy.assist.dmo.common.SystemVersionInfoDmo;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

/**
 * 版本升级管理
 *
 * @author zhangbiao
 */

@Controller
@RequestMapping(value = "/version")
public class AdminVersionController extends BaseController {
    @Autowired
    private AdminVersionService adminVersionService;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {

        return systemView("version_info");
    }

    /**
     * 查询版本号
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryVersion", method = RequestMethod.POST)
    public String findVersion(HttpServletRequest request, HttpServletResponse response, Model model) {

        SystemVersionInfoDmo con = new SystemVersionInfoDmo();
        List<SystemVersionInfoDmo> list = adminVersionService.findVersion(con);
        model.addAttribute("versionList", list);
        return systemView("version_table");
    }

    /**
     * 修改版本号
     *
     * @param form
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateVersion", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateVersion(AdminVersionForm form, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = form.getId();
        String version = form.getVersion();
        String upgradeUrl = form.getUpgradeUrl();

        if (StringUtils.isBlank(id) || StringUtils.isBlank(version) || StringUtils.isBlank(upgradeUrl)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }
        SystemVersionInfoDmo con = new SystemVersionInfoDmo();
        con.setVersion(version);
        con.setUpgradeUrl(upgradeUrl);
        con.setLastUpdateTime(DateUtil.getDate());

        con.setId(Long.valueOf(id));
        Result result = adminVersionService.modifyVersion(con);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改版本号失败");
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_VERSION,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_VERSION);

        return jsonResult;

    }
}
