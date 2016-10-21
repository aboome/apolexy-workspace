package com.yh.apoplexy.admin.portal.common.controller;

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

import com.yh.apoplexy.admin.base.service.intf.AdminBannerInfoService;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddBannerInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminBannerInfoDetailsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminBannerInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDeleteBannerInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDoctorNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminPatientBannerInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateBannerInfoForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateDoctorNewsForm;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.assist.dmo.common.BannerInfoDmo;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/bannerInfo")
public class AdminBannerInfoController extends BaseController {

    @Autowired
    private AdminBannerInfoService adminBannerInfoService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {

        return baseView("banner_info");
    }

    /***
     * 查询主页推荐位列表
     *
     * @param request
     * @param response
     * @param model
     * @param adminBannerInfoForm
     * @return
     */
    @RequestMapping(value = "/queryBannerInfo", method = RequestMethod.POST)
    public String queryBannerInfo(HttpServletRequest request, HttpServletResponse response, Model model, AdminBannerInfoForm adminBannerInfoForm) {

        String owner = adminBannerInfoForm.getOwner();
        String status = Constants.BannerStatus.NORMAL;

        BannerInfoDmo bannerInfoDmo = new BannerInfoDmo();

        if (StringUtils.isNotBlank(owner)) {
            bannerInfoDmo.setOwner(owner);
        }
        if (StringUtils.isNotBlank(status)) {
            bannerInfoDmo.setStatus(status);
        }

        List<BannerInfoDmo> bannerInfoList = adminBannerInfoService.selectList(bannerInfoDmo);

        model.addAttribute("bannerInfoList", bannerInfoList);

        return baseView("banner_table");
    }

    /**
     * 新增主页推荐位
     *
     * @param adminAddBannerInfoForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/addBannerInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult addBannerInfo(AdminAddBannerInfoForm adminAddBannerInfoForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String owner = adminAddBannerInfoForm.getOwner();
        String title = adminAddBannerInfoForm.getTitle();
        String image = adminAddBannerInfoForm.getImage();
        String sort = adminAddBannerInfoForm.getSort();
        String url = adminAddBannerInfoForm.getUrl();

        if (StringUtils.isBlank(owner) || StringUtils.isBlank(title) || StringUtils.isBlank(sort)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        BannerInfoDmo bannerInfoDmo = new BannerInfoDmo();

        bannerInfoDmo.setOwner(owner);
        bannerInfoDmo.setTitle(title);
        bannerInfoDmo.setImageId(image);
        bannerInfoDmo.setSort(Long.valueOf(sort));
        bannerInfoDmo.setUrl(url);
        bannerInfoDmo.setStatus(Constants.BannerStatus.NORMAL);

        Result result = adminBannerInfoService.addBanner(bannerInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("新增主页推荐位失败");
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.ADD_BANNERINFO,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.ADD_BANNERINFO);

        return jsonResult;

    }

    /**
     * 修改主页推荐位
     *
     * @param adminUpdateBannerInfoForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateBannerInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateBannerInfo(AdminUpdateBannerInfoForm adminUpdateBannerInfoForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = adminUpdateBannerInfoForm.getId();
        String title = adminUpdateBannerInfoForm.getTitle();
        String image = adminUpdateBannerInfoForm.getImage();
        String url = adminUpdateBannerInfoForm.getUrl();
        String sort = adminUpdateBannerInfoForm.getSort();
        String owner = adminUpdateBannerInfoForm.getOwner();

        if ( StringUtils.isBlank(title) || StringUtils.isBlank(id) || StringUtils.isBlank(sort)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        BannerInfoDmo bannerInfoDmo = new BannerInfoDmo();

        bannerInfoDmo.setId(Long.valueOf(id));
        bannerInfoDmo.setTitle(title);

        if (StringUtils.isNotBlank(image)){

            bannerInfoDmo.setImageId(image);

        }

        bannerInfoDmo.setUrl(url);
        bannerInfoDmo.setSort(Long.valueOf(sort));
        bannerInfoDmo.setOwner(owner);

        Result result = adminBannerInfoService.updateBanner(bannerInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改主页推荐位失败");
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.UPDATE_BANNERINFO,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.UPDATE_BANNERINFO);

        return jsonResult;

    }

    /**
     * 删除医生端主页推荐位
     *
     * @param adminDeleteBannerInfoForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteBannerInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteBannerInfo(AdminDeleteBannerInfoForm adminDeleteBannerInfoForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = adminDeleteBannerInfoForm.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        BannerInfoDmo bannerInfoDmo = new BannerInfoDmo();
        bannerInfoDmo.setId(Long.valueOf(id));

        Result result = adminBannerInfoService.deleteBanner(bannerInfoDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除主页推荐位失败");
        }

        UserSession userSession = getUserSession(request);

        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
                OperateLogConstants.OperateEnum.DELETE_BANNERINFO,
                Long.parseLong(userSession.getUserId()),
                userSession.getName(),
                OperateLogConstants.OperateDesc.DELETE_BANNERINFO);


        return jsonResult;

    }

}
