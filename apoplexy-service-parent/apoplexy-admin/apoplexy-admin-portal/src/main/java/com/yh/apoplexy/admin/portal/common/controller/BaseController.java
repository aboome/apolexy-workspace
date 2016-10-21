/**
 *
 */
package com.yh.apoplexy.admin.portal.common.controller;

import com.yh.apoplexy.common.constants.CommonConst;
import com.yh.apoplexy.common.dto.UserSession;
import com.yh.apoplexy.common.utils.SessionUtil;
import com.yjh.framework.lang.Result;
import com.yjh.framework.web.result.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Admin服务的父控制类
 *
 */
public class BaseController {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected JsonResult toJsonResult(JsonResult jsonResult, Result result, String... params) {

        jsonResult.setSuccess(result.isSuccess());
        if (null == params || params.length < 1) {
            params = result.getArgs();
        }
        String message = "";
        jsonResult.setMessage(message);
        jsonResult.setCode(result.getErrorCode());
        return jsonResult;
    }

    protected boolean checkUserSession(HttpServletRequest request, HttpServletResponse response) {

        // 用户session校验
        UserSession userSession = SessionUtil.getUserSession(request);

        return null == userSession;

    }

    protected JsonResult toJsonResult(Result result, String... params) {
        return toJsonResult(new JsonResult(), result, params);
    }

    /**
     * Direct to system pages.
     *
     * @param ftl
     * @return
     */
    protected String systemView(String ftl) { return view(CommonConst.DOMAIN_SYSTEM, ftl); }

    /**
     * Direct to member pages.
     *
     * @param ftl
     * @return
     */
    protected String memberView(String ftl) {
        return view(CommonConst.DOMAIN_MEMBER, ftl);
    }

    /**
     * Direct to base pages.
     *
     * @param ftl
     * @return
     */
    protected String baseView(String ftl) {
        return view(CommonConst.DOMAIN_BASE, ftl);
    }

    /**
     * Direct to doctor pages.
     *
     * @param ftl
     * @return
     */
    protected String doctorView(String ftl) {
        return view(CommonConst.DOMAIN_DOCTOR, ftl);
    }

    /**
     * Direct to patient pages.
     *
     * @param ftl
     * @return
     */
    protected String patientView(String ftl) {
        return view(CommonConst.DOMAIN_PATIENT, ftl);
    }

    /**
     * Direct to statics pages.
     *
     * @param ftl
     * @return
     */
    protected String staticsView(String ftl) {
        return view(CommonConst.DOMAIN_STATICS, ftl);
    }

    /**
     * Direct to common pages.
     *
     * @param ftl
     * @return
     */
    protected String commonView(String ftl) { return view(CommonConst.DOMAIN_COMMON, ftl); }

    /**
     * 重定向
     */
    protected String redirect(String redirectUrl) {
        return "redirect:" + redirectUrl;
    }

    /**
     * 重定向
     */
    protected String forword(String forwordUrl) {
        return "forward:" + forwordUrl;
    }

    private String view(String domail, String ftl) {
        return CommonConst.BASE_VIEW_PATH.concat("/").concat(domail).concat("/").concat(ftl);
    }

    /**
     * 获取session中用户相关信息
     *
     * @param request
     * @return
     */
    protected UserSession getUserSession(HttpServletRequest request) {
        return SessionUtil.getUserSession(request);
    }

    /**
     * 获取session中用户相关信息
     *
     * @param request
     * @return
     */
    protected String getCurrentUserId(HttpServletRequest request) {
        return SessionUtil.getUserSession(request).getUserId();
    }

    /**
     * 获取session中用户相关信息
     *
     * @param request
     * @return
     */
    protected String getCurrentUserName(HttpServletRequest request) {
        return SessionUtil.getUserSession(request).getName();
    }
}
