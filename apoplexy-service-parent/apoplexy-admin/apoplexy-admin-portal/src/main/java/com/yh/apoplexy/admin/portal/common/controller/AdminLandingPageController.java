package com.yh.apoplexy.admin.portal.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.base.service.intf.AdminLandingPageService;
import com.yh.apoplexy.admin.portal.common.controller.dto.*;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.OperateLogConstants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.assist.dmo.common.LandingPageDmo;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/landingPage")
public class AdminLandingPageController extends BaseController {

    @Autowired
    private AdminLandingPageService adminLandingPageService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {

        return baseView("landingpage_info");
    }

    /**
     * 查询开机动画列表
     *
     * @param request
     * @param response
     * @param model
     * @param queryLandingPageForm
     * @return
     */
    @RequestMapping(value = "/queryLandingPageList", method = RequestMethod.POST)
    public String queryLandingPageList(HttpServletRequest request, HttpServletResponse response, Model model, AdminQueryLandingPageForm queryLandingPageForm) {

        String owner = queryLandingPageForm.getOwner();

        LandingPageDmo con = new LandingPageDmo();

        if (StringUtils.isNotBlank(owner)) {
            con.setOwner(owner);
        }

        con.setStatus(Constants.StartPictureStatus.NORMAL);

        List<LandingPageDmo> landingDoctorList = adminLandingPageService.selectList(con);

        model.addAttribute("landingPageList", landingDoctorList);

        return baseView("landingpage_table");
    }

    /**
     * 新增开机动画
     *
     * @param addLandingPageForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/addLandingPage", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult addLandingPage(AdminAddLandingPageForm addLandingPageForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String owner = addLandingPageForm.getOwner();
        String imageId = addLandingPageForm.getImageId();
        String sort = addLandingPageForm.getSort();

        if (StringUtils.isBlank(owner) || StringUtils.isBlank(imageId) || StringUtils.isBlank(sort)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        LandingPageDmo landingPageDmo = new LandingPageDmo();

        landingPageDmo.setOwner(owner);

        if (StringUtils.isNotBlank(imageId)){

            landingPageDmo.setImageId(imageId);

        }

        landingPageDmo.setSort(Long.parseLong(sort));
        landingPageDmo.setStatus(Constants.StartPictureStatus.NORMAL);
        landingPageDmo.setCreateTime(DateUtil.getDate());
        landingPageDmo.setLastUpdateTime(DateUtil.getDate());

        Result result = adminLandingPageService.add(landingPageDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("新增开机动画失败");
        }
        
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.ADD_LANDINGPAGE,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.ADD_LANDINGPAGE);

        return jsonResult;

    }

    /**
     * 修改开机动画失败
     *
     * @param updateLandingPageForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateLandingPage", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateLandingPage(AdminUpdateLandingPageForm updateLandingPageForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = updateLandingPageForm.getId();
        String imageId = updateLandingPageForm.getImageId();
        String sort = updateLandingPageForm.getSort();
        String owner = updateLandingPageForm.getOwner();

        if (StringUtils.isBlank(id) || StringUtils.isBlank(sort)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        LandingPageDmo landingPageDmo = new LandingPageDmo();

        landingPageDmo.setId(Long.valueOf(id));

        if (StringUtils.isNotBlank(imageId)){

            landingPageDmo.setImageId(imageId);

        }

        landingPageDmo.setSort(Long.valueOf(sort));
        landingPageDmo.setOwner(owner);
        landingPageDmo.setLastUpdateTime(DateUtil.getDate());

        Result result = adminLandingPageService.update(landingPageDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改开机动画失败");
        }
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_LANDINGPAGE,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_LANDINGPAGE);
        
        return jsonResult;

    }

    /**
     * 删除开机动画
     *
     * @param deleteLandingPageForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteLandingPage", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteLandingPage(AdminDeleteLandingPageForm deleteLandingPageForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = deleteLandingPageForm.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        LandingPageDmo landingPageDmo = new LandingPageDmo();

        landingPageDmo.setId(Long.valueOf(id));

        Result result = adminLandingPageService.deleteLandingPage(landingPageDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除开机动画失败");
        }
        
        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.DELETE_LANDINGPAGE,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.DELETE_LANDINGPAGE);
        
        
        return jsonResult;

    }

}
