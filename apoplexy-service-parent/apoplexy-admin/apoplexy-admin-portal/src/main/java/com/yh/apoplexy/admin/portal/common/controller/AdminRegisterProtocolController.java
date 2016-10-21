package com.yh.apoplexy.admin.portal.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.assist.dto.admin.common.RegisterProtocolDto;
import com.yh.apoplexy.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yh.apoplexy.admin.base.service.intf.AdminRegisterProtocolService;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddDoctorNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDoctorForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminRegisterProtocolForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateRegisterProtocolFrom;
import com.yh.apoplexy.assist.dmo.common.RegisterProtocolDmo;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/registerProtocol")
public class AdminRegisterProtocolController extends BaseController {

    @Autowired
    private AdminRegisterProtocolService adminRegisterProtocolService;

    @RequestMapping(value = "/registerProtocolcom", method = RequestMethod.GET)
    public String registerProtocolManager(HttpServletRequest request, Model model) {

        return baseView("register_protocol");

    }

    /**
     * 查询注册协议
     *
     * @param request
     * @param response
     * @param model
     * @param adminRegisterProtocolForm
     * @return
     */
    @RequestMapping(value = "/queryRegisterProtocol", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryRegisterProtocol(HttpServletRequest request, HttpServletResponse response, Model model, AdminRegisterProtocolForm adminRegisterProtocolForm) {

        JsonResult jsonResult = new JsonResult();

        String owner = adminRegisterProtocolForm.getOwner();

        RegisterProtocolDmo protocolDmo = new RegisterProtocolDmo();

        if (StringUtils.isNotBlank(owner)) {
            protocolDmo.setOwner(owner);
        }

        RegisterProtocolDmo registerProtocolDmo = adminRegisterProtocolService.selectOne(protocolDmo);

        if (null == registerProtocolDmo){

            jsonResult.fail("注册协议不存在");
            return jsonResult;

        }

        RegisterProtocolDto registerProtocolDto = new RegisterProtocolDto();

        registerProtocolDto.setId(registerProtocolDmo.getId());
        registerProtocolDto.setOwner(registerProtocolDmo.getOwner());
        registerProtocolDto.setProtocolContent(registerProtocolDmo.getProtocolContent());
        registerProtocolDto.setLastUpdateTime(DateUtil.format(registerProtocolDmo.getLastUpdateTime(),"yyyy-MM-dd HH:mm:ss"));

        jsonResult.setSuccess(true);
        jsonResult.setData(registerProtocolDto);

        return jsonResult;
    }

    /***
     * 修改注册协议
     * @param adminUpdateRegisterProtocolFrom
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateRegisterProtocol", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult ownerRegisterProtocol(AdminUpdateRegisterProtocolFrom adminUpdateRegisterProtocolFrom, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String owner = adminUpdateRegisterProtocolFrom.getOwner();
        String protocolContent = adminUpdateRegisterProtocolFrom.getProtocolContent();

        if (StringUtils.isBlank(owner) || StringUtils.isBlank(protocolContent)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        RegisterProtocolDmo con = new RegisterProtocolDmo();

        con.setOwner(owner);

        RegisterProtocolDmo registerProtocolDmo = adminRegisterProtocolService.selectOne(con);

        if (null == registerProtocolDmo){
            jsonResult.fail("修改协议失败");
            return jsonResult;
        }

        registerProtocolDmo.setProtocolContent(protocolContent);
        registerProtocolDmo.setLastUpdateTime(DateUtil.getDate());

        Result result = adminRegisterProtocolService.update(registerProtocolDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改协议失败");
        }
        return jsonResult;

    }

}
