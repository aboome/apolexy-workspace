package com.yh.apoplexy.admin.portal.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.system.service.intf.AdminMenuService;
import com.yh.apoplexy.assist.dmo.admin.MenuInfoDmo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.dto.QueryParentMenuListForm;
import com.yh.apoplexy.common.constants.Constants.MenuStatus;
import com.yjh.framework.web.result.JsonResult;

/**
 * 查询父级菜单列表
 * @author zhangbiao
 *
 */
@Controller
@RequestMapping(value = "/parentMenu")
public class AdminParentMenuListController extends BaseController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(value = "/queryParentMenuList", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryParentMenuListList(HttpServletRequest request, HttpServletResponse response, Model model, QueryParentMenuListForm  queryParentMenuListForm) {

        JsonResult jsonResult = new JsonResult();

        MenuInfoDmo con = new  MenuInfoDmo();

        if (StringUtils.isNotBlank(queryParentMenuListForm.getLevel())){
            con.setMenuLevel(queryParentMenuListForm.getLevel());
        }
        con.setStatus(MenuStatus.NORMAL);

        List<MenuInfoDmo> parentMenuList = adminMenuService.selectList(con);

        jsonResult.setSuccess( true);
        jsonResult.setData(parentMenuList);

        return jsonResult;
    }
}
