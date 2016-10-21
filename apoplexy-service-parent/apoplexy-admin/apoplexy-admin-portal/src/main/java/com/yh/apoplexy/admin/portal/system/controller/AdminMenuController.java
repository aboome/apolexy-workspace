package com.yh.apoplexy.admin.portal.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.system.service.intf.AdminMenuService;
import com.yh.apoplexy.assist.dmo.admin.MenuInfoDmo;
import com.yh.apoplexy.common.constants.Constants;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddMenuForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDeleteMenuForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminSubMenuListForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateMenuForm;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.system.form.AdminQueryMenuForm;
import com.yh.apoplexy.assist.dto.admin.system.AdminMenuDto;
import com.yh.apoplexy.assist.dto.admin.system.AdminMenuInfoDto;
import com.yh.apoplexy.assist.dto.admin.system.AdminSubMenuDto;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.constants.Constants.MenuStatus;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/Menu")
public class AdminMenuController extends BaseController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(value = "/menucom", method = RequestMethod.GET)
    public String menucom(HttpServletRequest request, Model model) {

        return systemView("menu_info");
    }

    /**
     * 查询菜单列表
     *
     * @param request
     * @param response
     * @param model
     * @param adminQueryMenuForm
     * @return
     */
    @RequestMapping(value = "/queryMenu", method = RequestMethod.POST)
    public String queryMenu(HttpServletRequest request, HttpServletResponse response, Model model, AdminQueryMenuForm adminQueryMenuForm) {

        MenuInfoDmo con = new MenuInfoDmo();

        con.setStatus(MenuStatus.NORMAL);

        Page page = new Page();

        page.setCurrentPage(Integer.valueOf(adminQueryMenuForm.getPageNum()));
        page.setPageSize(Integer.valueOf(adminQueryMenuForm.getPageSize()));

        List<AdminMenuDto> adminMenuDtosList = adminMenuService.selectListByPage(con, page);

        model.addAttribute("menuList", adminMenuDtosList);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return systemView("menu_table");

    }

    /**
     * 查询菜单列表
     *
     * @param menuLevel
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryMenuList", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryMenuList(String menuLevel, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        MenuInfoDmo menuInfoDmo = new MenuInfoDmo();

        if (Constants.MenuLevel.SECOND.equals(menuLevel)){
            menuInfoDmo.setMenuLevel(Constants.MenuLevel.FIRST);
        }
        if (Constants.MenuLevel.THIRD.equals(menuLevel)){
            menuInfoDmo.setMenuLevel(Constants.MenuLevel.SECOND);
        }

        menuInfoDmo.setStatus(MenuStatus.NORMAL);

        List<MenuInfoDmo> menuInfoDmoList = adminMenuService.selectList(menuInfoDmo);

        jsonResult.setSuccess(true);
        jsonResult.setData(menuInfoDmoList);

        return jsonResult;

    }

    /**
     * 新增主菜单
     *
     * @param addMenuForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult addMenu(AdminAddMenuForm addMenuForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String menuLevel = addMenuForm.getMenuLevel();
        String menuName = addMenuForm.getMenuName();
        String menuUrl = addMenuForm.getMenuUrl();
        Long parentMenu = addMenuForm.getParentMenu();
        String sort = addMenuForm.getMenuSort();

        if (StringUtils.isBlank(menuLevel) || StringUtils.isBlank(menuName) || StringUtils.isBlank(menuUrl) || StringUtils.isBlank(sort)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        MenuInfoDmo menuInfoDmo = new MenuInfoDmo();

        menuInfoDmo.setMenuLevel(menuLevel);
        menuInfoDmo.setMenuName(menuName);
        menuInfoDmo.setMenuUrl(menuUrl);
        menuInfoDmo.setStatus(MenuStatus.NORMAL);
        if (Constants.MenuLevel.FIRST.equals(menuLevel)){
            menuInfoDmo.setParentId(1L);
        }else {
            menuInfoDmo.setParentId(parentMenu);
        }
        menuInfoDmo.setSort(Long.valueOf(sort));
        menuInfoDmo.setCreateTime(DateUtil.getDate());
        menuInfoDmo.setLastUpdateTime(DateUtil.getDate());

        Result result = adminMenuService.addMenu(menuInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("新增主菜单失败");
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.ADD_MENU,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.ADD_MENU);


        return jsonResult;

    }

    /**
     * 修改菜单
     *
     * @param adminUpdateMenuForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateMenu(AdminUpdateMenuForm adminUpdateMenuForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();
        String menuName = adminUpdateMenuForm.getMenuName();
        String menuUrl = adminUpdateMenuForm.getMenuUrl();
        String menuLevel = adminUpdateMenuForm.getMenuLevel();
        Long parentMenu = adminUpdateMenuForm.getParentMenu();
        String sort = adminUpdateMenuForm.getMenuSort();
        Long id = adminUpdateMenuForm.getId();

        if (StringUtils.isBlank(menuName) || StringUtils.isBlank(menuUrl) || StringUtils.isBlank(sort) || StringUtils.isBlank(menuLevel)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        MenuInfoDmo menuInfoDmo = new MenuInfoDmo();

        menuInfoDmo.setMenuName(menuName);
        menuInfoDmo.setMenuUrl(menuUrl);
        menuInfoDmo.setMenuLevel(menuLevel);
        menuInfoDmo.setParentId(parentMenu);
        menuInfoDmo.setId(id);
        menuInfoDmo.setSort(Long.valueOf(sort));

        Result result = adminMenuService.updateMenu(menuInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改菜单失败");
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_MENU,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_MENU);

        return jsonResult;

    }

    /**
     * 删除菜单
     *
     * @param adminDeleteMenuForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteMenu(AdminDeleteMenuForm adminDeleteMenuForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = adminDeleteMenuForm.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        MenuInfoDmo menuInfoDmo = new MenuInfoDmo();

        menuInfoDmo.setId(Long.valueOf(id));

        Result result = adminMenuService.deleteMenu(menuInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail(result.getErrorCode());
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.DELETE_MENU,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.DELETE_MENU);


        return jsonResult;

    }


}
