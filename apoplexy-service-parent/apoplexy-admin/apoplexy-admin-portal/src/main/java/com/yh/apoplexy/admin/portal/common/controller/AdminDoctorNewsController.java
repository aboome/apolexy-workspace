package com.yh.apoplexy.admin.portal.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.doctor.member.service.intf.AdminDoctorMemberService;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.result.ProcessNewsContentResult;
import com.yh.apoplexy.assist.service.intf.NewContentService;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.base.service.intf.AdminDoctorNewService;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddDoctorNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDeleteDoctorNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDoctorNewsDetailsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminDoctorNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminUpdateDoctorNewsForm;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/doctorNews")
public class AdminDoctorNewsController extends BaseController {

    @Autowired
    private AdminDoctorNewService adminDoctorNewService;

    @Autowired
    private NewContentService newContentService;

    @RequestMapping(value = "/doctorNewsMain", method = RequestMethod.GET)
    public String doctorNewsMain(HttpServletRequest request, Model model) {

        return baseView("doctor_news_info");
    }

    /**
     * 医生查询学术更新/最新资讯列表
     *
     * @param request
     * @param response
     * @param model
     * @param adminDoctorNewsForm
     * @return
     */
    @RequestMapping(value = "/queryDoctorNews", method = RequestMethod.POST)
    public String queryDoctorNews(HttpServletRequest request, HttpServletResponse response, Model model, AdminDoctorNewsForm adminDoctorNewsForm) {

        String title = adminDoctorNewsForm.getTitle();
        String newType = adminDoctorNewsForm.getNewType();
        String type = adminDoctorNewsForm.getType();

        String pageSize = adminDoctorNewsForm.getPageSize();
        String pageNum = adminDoctorNewsForm.getPageNum();

        DoctorNewsDmo doctorNewsDmo = new DoctorNewsDmo();

        if (StringUtils.isNotBlank(title)) {
            doctorNewsDmo.setTitle(title);
        }
        if (StringUtils.isNotBlank(newType)) {
            doctorNewsDmo.setNewType(newType);
        }
        if (StringUtils.isNotBlank(type)) {
            doctorNewsDmo.setType(type);
        }

        doctorNewsDmo.setStatus(DoctorConstants.NewsStatus.NORMAL);

        Page page = new Page();

        if (StringUtils.isNotBlank(pageSize)) {
            page.setPageSize(Integer.valueOf(pageSize));
        }

        if (StringUtils.isNotBlank(pageNum)) {
            page.setCurrentPage(Integer.valueOf(pageNum));
        }

        List<DoctorNewsDmo> doctorNewsList = adminDoctorNewService.selectListByPage(doctorNewsDmo, page);

        model.addAttribute("doctorNewsList", doctorNewsList);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return baseView("doctor_news_table");

    }

    /**
     * 医生查询学术更新/最新资讯详情
     *
     * @param request
     * @param response
     * @param model
     * @param adminDoctorNewsDetailsForm
     * @return
     */
    @RequestMapping(value = "/queryDoctorNewsDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryDoctorNewsDetails(HttpServletRequest request, HttpServletResponse response, Model model, AdminDoctorNewsDetailsForm adminDoctorNewsDetailsForm) {

        JsonResult jsonResult = new JsonResult();

        String id = adminDoctorNewsDetailsForm.getId();

        DoctorNewsDmo adminDoctorNewsDmo = new DoctorNewsDmo();
        adminDoctorNewsDmo.setId(Long.valueOf(id));
        adminDoctorNewsDmo.setStatus(DoctorConstants.NewsStatus.NORMAL);

        DoctorNewsDmo doctorNewsDmo = adminDoctorNewService.selectDoctorNewsDetails(adminDoctorNewsDmo);

        jsonResult.setData(doctorNewsDmo);
        jsonResult.setSuccess(true);

        return jsonResult;

    }

    @RequestMapping(value = "/addDoctorNewsManager", method = RequestMethod.GET)
    public String doctorNewsAddManager(HttpServletRequest request, Model model) {

        return baseView("add_doctor_news");
    }

    @RequestMapping(value = "/modifyDoctorNewsManager/{id}", method = RequestMethod.GET)
    public String doctorNewsModifyManager(HttpServletRequest request, Model model, @PathVariable(value = "id") String id) {

        model.addAttribute("newsId", id);

        return baseView("modify_doctor_news");
    }

    /**
     * 新增医生学术更新/最新资讯详情
     *
     * @param addDoctorNewsForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/addDoctorNews", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult addDoctorNews(AdminAddDoctorNewsForm addDoctorNewsForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String source = addDoctorNewsForm.getSource();
        String title = addDoctorNewsForm.getTitle();
        String content = addDoctorNewsForm.getContent();
        String subTitle = addDoctorNewsForm.getSubTitle();
        String newType = addDoctorNewsForm.getNewsType();
        String smallLogo = addDoctorNewsForm.getSmallLogo();
        String type = addDoctorNewsForm.getType();
        String contentType = addDoctorNewsForm.getSourceType();

        if (StringUtils.isBlank(source)
                || StringUtils.isBlank(title) || StringUtils.isBlank(subTitle)
                || StringUtils.isBlank(newType)
                || StringUtils.isBlank(smallLogo)
                || StringUtils.isBlank(type)
                || StringUtils.isBlank(contentType)) {

            jsonResult.fail("参数异常");
            return jsonResult;

        }

        //处理视频链接
        ProcessNewsContentResult processResult = newContentService.processVideo(content);

        if (!processResult.isSuccess()){

            jsonResult.fail("保存视频失败");
            return jsonResult;
        }

        //处理图片链接
        processResult  = newContentService.processImage(processResult.getContent());

        if (!processResult.isSuccess()){

            jsonResult.fail("保存图片失败");
            return jsonResult;
        }

        DoctorNewsDmo doctorNewsDmo = new DoctorNewsDmo();

        doctorNewsDmo.setSource(source);
        doctorNewsDmo.setTitle(title);
        doctorNewsDmo.setContent(processResult.getContent());
        doctorNewsDmo.setCreateTime(DateUtil.getDate());
        doctorNewsDmo.setLastUpdateTime(DateUtil.getDate());
        doctorNewsDmo.setSubTitle(subTitle);
        doctorNewsDmo.setNewType(newType);
        doctorNewsDmo.setSmallLogo(smallLogo);
        doctorNewsDmo.setType(type);
        doctorNewsDmo.setContentType(contentType);
        doctorNewsDmo.setStatus(DoctorConstants.NewsStatus.NORMAL);

        Result result = adminDoctorNewService.addDoctorNews(doctorNewsDmo);

        adminDoctorNewService.pushMessage(doctorNewsDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("新增学术更新/最新资讯详情失败");
        }

        return jsonResult;

    }

    @RequestMapping(value = "/modifyDoctorNews", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult modifyDoctorNews(AdminAddDoctorNewsForm doctorNewsForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        Long id = doctorNewsForm.getId();
        String source = doctorNewsForm.getSource();
        String title = doctorNewsForm.getTitle();
        String content = doctorNewsForm.getContent();
        String subTitle = doctorNewsForm.getSubTitle();
        String newType = doctorNewsForm.getNewsType();
        String smallLogo = doctorNewsForm.getSmallLogo();
        String type = doctorNewsForm.getType();
        String contentType = doctorNewsForm.getSourceType();

        if (StringUtils.isBlank(source)
                || StringUtils.isBlank(title) || StringUtils.isBlank(subTitle)
                || StringUtils.isBlank(newType)
                || StringUtils.isBlank(type)
                || StringUtils.isBlank(contentType)) {

            jsonResult.fail("参数异常");
            return jsonResult;

        }

        //处理视频链接
        ProcessNewsContentResult processResult = newContentService.processVideo(content);

        if (!processResult.isSuccess()){

            jsonResult.fail("保存视频失败");
            return jsonResult;
        }

        //处理图片链接
        processResult  = newContentService.processImage(processResult.getContent());

        if (!processResult.isSuccess()){

            jsonResult.fail("保存图片失败");
            return jsonResult;
        }

        DoctorNewsDmo doctorNewsDmo = new DoctorNewsDmo();

        doctorNewsDmo.setId(id);
        doctorNewsDmo.setSource(source);
        doctorNewsDmo.setTitle(title);
        doctorNewsDmo.setContent(processResult.getContent());
        doctorNewsDmo.setLastUpdateTime(DateUtil.getDate());
        doctorNewsDmo.setSubTitle(subTitle);
        doctorNewsDmo.setNewType(newType);

        if(StringUtils.isNotBlank(smallLogo)){
            doctorNewsDmo.setSmallLogo(smallLogo);

        }

        doctorNewsDmo.setType(type);
        doctorNewsDmo.setContentType(contentType);
        doctorNewsDmo.setStatus(DoctorConstants.NewsStatus.NORMAL);

        Result result = adminDoctorNewService.updateDoctorNewsDetails(doctorNewsDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("更新资讯详情失败");
        }

        return jsonResult;

    }

    /**
     * 删除医生学术更新/最新资讯详情
     *
     * @param adminDeleteDoctorNewsForm
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteDoctorNews", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteDoctorNews(AdminDeleteDoctorNewsForm adminDeleteDoctorNewsForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = adminDeleteDoctorNewsForm.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        DoctorNewsDmo doctorNewsDmo = new DoctorNewsDmo();

        doctorNewsDmo.setId(Long.valueOf(id));
        doctorNewsDmo.setStatus(DoctorConstants.NewsStatus.DELETE);

        Result result = adminDoctorNewService.updateDoctorNewsDetails(doctorNewsDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除资讯详情失败");
        }
        return jsonResult;

    }

    @RequestMapping(value = "/doctorNewsDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult doctorNewsDetail(Long id, String url, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        if (null == id) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        DoctorNewsDmo con = new DoctorNewsDmo();

        con.setId(id);
        con.setStatus(DoctorConstants.NewsStatus.NORMAL);

        DoctorNewsDmo doctorNewsDmo = adminDoctorNewService.selectDoctorNewsDetails(con);

        if (null != doctorNewsDmo) {

            //恢复图片URL
            ProcessNewsContentResult processResult = newContentService.recoveryImage(doctorNewsDmo.getContent(), url);

            if (!processResult.isSuccess()){

                jsonResult.fail("数据异常");
                return jsonResult;
            }

            doctorNewsDmo.setContent(processResult.getContent());

            jsonResult.setSuccess(true);
            jsonResult.setData(doctorNewsDmo);
        } else {
            jsonResult.fail("数据异常");
        }

        return jsonResult;

    }

}
