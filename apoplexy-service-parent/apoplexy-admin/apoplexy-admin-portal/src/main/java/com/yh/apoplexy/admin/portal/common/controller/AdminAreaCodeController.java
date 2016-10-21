package com.yh.apoplexy.admin.portal.common.controller;

import com.yh.apoplexy.admin.base.service.intf.AdminAreaCodeService;
import com.yh.apoplexy.admin.portal.common.controller.dto.QueryAreaCodeForm;
import com.yh.apoplexy.assist.dmo.common.AreaCodeDmo;
import com.yh.apoplexy.common.constants.Constants;
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
import java.util.List;

/**
 * 区域信息服务
 * Created by wunder on 16/10/8 18:27.
 */
@Controller
@RequestMapping(value = "/areaCode")
public class AdminAreaCodeController extends BaseController {

    @Autowired
    private AdminAreaCodeService adminAreaCodeService;

    @RequestMapping(value = "/queryAreaCodeList", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryAreaCodeList(HttpServletRequest request, HttpServletResponse response, Model model, QueryAreaCodeForm queryAreaCodeForm) {

        JsonResult jsonResult = new JsonResult();

        AreaCodeDmo con = new AreaCodeDmo();

        if (StringUtils.isNotBlank(queryAreaCodeForm.getLevel())){
            con.setLevel(Long.parseLong(queryAreaCodeForm.getLevel()));
        }

        List<AreaCodeDmo> areaCodeDmoList = adminAreaCodeService.selectList(con);

        jsonResult.setSuccess(true);
        jsonResult.setData(areaCodeDmoList);

        return jsonResult;
    }
}
