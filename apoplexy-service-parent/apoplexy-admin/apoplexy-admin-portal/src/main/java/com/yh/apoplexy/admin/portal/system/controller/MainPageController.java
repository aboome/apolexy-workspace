/**
 * 
 */
package com.yh.apoplexy.admin.portal.system.controller;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.system.dto.FirstMenuDto;
import com.yh.apoplexy.admin.system.service.intf.AdminMenuService;
import com.yh.apoplexy.admin.system.service.intf.AdminRoleInfoService;
import com.yh.apoplexy.assist.dmo.admin.AdminRoleInfoDmo;
import com.yh.apoplexy.assist.dto.admin.system.QueryMenuListDto;
import com.yh.apoplexy.common.dto.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页控制类
 * 
 * @author CC
 *
 */
@Controller
public class MainPageController extends BaseController {
	
	@Autowired
	private AdminMenuService adminMenuService;

    @Autowired
    private AdminRoleInfoService adminRoleInfoService;
	
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {
    	
    	UserSession userSession = getUserSession(request);

    	String userId = userSession.getUserId();

    	//通过userId查询菜单
        QueryMenuListDto con = new QueryMenuListDto();

        con.setUserId(Long.parseLong(userId));

        List<FirstMenuDto> menuDtoList = adminMenuService.loadMenuListByUser(con);

        AdminRoleInfoDmo roleInfoDmo = adminRoleInfoService.findRoleInfoByUserId(userId);

    	model.addAttribute("menuList", menuDtoList);

    	model.addAttribute("userName", userSession.getName());
        model.addAttribute("roleName", roleInfoDmo.getRoleName());


        return commonView("main");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        return commonView("index");
    }
    
}
