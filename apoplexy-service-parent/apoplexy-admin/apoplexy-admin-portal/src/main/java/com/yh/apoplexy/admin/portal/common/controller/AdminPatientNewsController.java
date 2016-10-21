package com.yh.apoplexy.admin.portal.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.assist.result.ProcessNewsContentResult;
import com.yh.apoplexy.assist.service.intf.NewContentService;
import com.yh.apoplexy.common.constants.PatientConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.base.service.intf.AdminPatientNewsService;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminAddPatientNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.DeletePatientNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.AdminPatientNewsDetailsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.QueryPatientNewsForm;
import com.yh.apoplexy.admin.portal.common.controller.dto.UpdatePatientNewsForm;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.result.JsonResult;

@Controller
@RequestMapping(value = "/patientNews")
public class AdminPatientNewsController extends BaseController {

    @Autowired
    private AdminPatientNewsService adminPatientNewsService;

    @Autowired
    private NewContentService newContentService;

    @RequestMapping(value = "/patientNewsMain", method = RequestMethod.GET)
    public String patientNewsMain(HttpServletRequest request, Model model) {

        return baseView("patient_news_info");
    }

    @RequestMapping(value = "/addPatientNewsManager", method = RequestMethod.GET)
    public String addPatientNewsManager(HttpServletRequest request, Model model) {

        return baseView("add_patient_news");
    }

    @RequestMapping(value = "/modifyPatientNewsManager/{id}", method = RequestMethod.GET)
    public String modifyPatientNewsManager(HttpServletRequest request, Model model, @PathVariable(value = "id") String id) {

        model.addAttribute("newsId", id);

        return baseView("modify_patient_news");
    }

    @RequestMapping(value = "/queryPatientNewsList", method = RequestMethod.POST)
    public String queryPatientNewsList(HttpServletRequest request, HttpServletResponse response, Model model, QueryPatientNewsForm patientNewsForm) {

        String title = patientNewsForm.getTitle();
        String type = patientNewsForm.getType();

        String pageNum = patientNewsForm.getPageNum();
        String pageSize = patientNewsForm.getPageSize();

        PatientNewsDmo adminPatientNewsDmo = new PatientNewsDmo();

        if (StringUtils.isNotBlank(title)) {
            adminPatientNewsDmo.setTitle(title);
        }

        if (StringUtils.isNotBlank(type)) {
            adminPatientNewsDmo.setType(type);
        }

        adminPatientNewsDmo.setStatus(PatientConstants.NewsStatus.NORMAL);

        Page page = new Page();

        page.setCurrentPage(Integer.valueOf(pageNum));
        page.setPageSize(Integer.valueOf(pageSize));

        List<PatientNewsDmo> patientNewsList = adminPatientNewsService.selectListByPage(adminPatientNewsDmo, page);

        model.addAttribute("patientNewsList", patientNewsList);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return baseView("patient_news_table");

    }

    @RequestMapping(value = "/queryPatientNewsDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryPatientNewsDetails(HttpServletRequest request, HttpServletResponse response, Model model, AdminPatientNewsDetailsForm adminPatientNewsDetailsForm) {

        JsonResult jsonResult = new JsonResult();

        String id = adminPatientNewsDetailsForm.getId();

        PatientNewsDmo adminPatientNewsDmo = new PatientNewsDmo();

        adminPatientNewsDmo.setId(Long.valueOf(id));
        adminPatientNewsDmo.setStatus(PatientConstants.NewsStatus.NORMAL);

        PatientNewsDmo patientNewsDmo = adminPatientNewsService.selectDoctorNewsDetails(adminPatientNewsDmo);

        jsonResult.setData(patientNewsDmo);
        jsonResult.setSuccess(true);

        return jsonResult;

    }

    @RequestMapping(value = "/addPatientNews", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult addPatientNews(AdminAddPatientNewsForm addPatientNewsForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String source = addPatientNewsForm.getSource();
        String content = addPatientNewsForm.getContent();
        String title = addPatientNewsForm.getTitle();
        String subTitle = addPatientNewsForm.getSubTitle();
        String smallLogo = addPatientNewsForm.getSmallLogo();
        String type = addPatientNewsForm.getType();
        String contentType = addPatientNewsForm.getSourceType();

        if (StringUtils.isBlank(source) || StringUtils.isBlank(title)
                || StringUtils.isBlank(subTitle)
                || StringUtils.isBlank(smallLogo)
                || StringUtils.isBlank(type)
                || StringUtils.isBlank(contentType)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        //处理视频链接
        ProcessNewsContentResult processResult = newContentService.processVideo(content);

        if (!processResult.isSuccess()) {

            jsonResult.fail("保存视频失败");
            return jsonResult;
        }

        //处理图片链接
        processResult = newContentService.processImage(processResult.getContent());

        if (!processResult.isSuccess()) {

            jsonResult.fail("保存图片失败");
            return jsonResult;
        }

        PatientNewsDmo adminPatientNewsDmo = new PatientNewsDmo();

        adminPatientNewsDmo.setSource(source);
        adminPatientNewsDmo.setContent(processResult.getContent());
        adminPatientNewsDmo.setTitle(title);
        adminPatientNewsDmo.setCreateTime(DateUtil.getDate());
        adminPatientNewsDmo.setLastUpdateTime(DateUtil.getDate());
        adminPatientNewsDmo.setSubTitle(subTitle);
        adminPatientNewsDmo.setSmallLogo(smallLogo);
        adminPatientNewsDmo.setType(type);
        adminPatientNewsDmo.setContentType(contentType);
        adminPatientNewsDmo.setStatus(PatientConstants.NewsStatus.NORMAL);

        Result result = adminPatientNewsService.addPatientNews(adminPatientNewsDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("新增资讯失败");
        }

        return jsonResult;

    }

    @RequestMapping(value = "/modifyPatientNews", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult modifyPatientNews(UpdatePatientNewsForm patientNewsForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String source = patientNewsForm.getSource();
        String content = patientNewsForm.getContent();
        String title = patientNewsForm.getTitle();
        String id = patientNewsForm.getId();
        String subTitle = patientNewsForm.getSubTitle();
        String smallLogo = patientNewsForm.getSmallLogo();
        String type = patientNewsForm.getType();
        String contentType = patientNewsForm.getSourceType();

        if (StringUtils.isBlank(source)
                || StringUtils.isBlank(id) || StringUtils.isBlank(title)
                || StringUtils.isBlank(subTitle)
                || StringUtils.isBlank(type)
                || StringUtils.isBlank(contentType)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        //处理视频链接
        ProcessNewsContentResult processResult = newContentService.processVideo(content);

        if (!processResult.isSuccess()) {

            jsonResult.fail("保存视频失败");
            return jsonResult;
        }

        //处理图片链接
        processResult = newContentService.processImage(processResult.getContent());

        if (!processResult.isSuccess()) {

            jsonResult.fail("保存图片失败");
            return jsonResult;
        }

        PatientNewsDmo adminPatientNewsDmo = new PatientNewsDmo();

        adminPatientNewsDmo.setSource(source);
        adminPatientNewsDmo.setContent(processResult.getContent());
        adminPatientNewsDmo.setTitle(title);
        adminPatientNewsDmo.setId(Long.valueOf(id));
        adminPatientNewsDmo.setSubTitle(subTitle);
        if (StringUtils.isNotBlank(smallLogo)) {
            adminPatientNewsDmo.setSmallLogo(smallLogo);
        }
        adminPatientNewsDmo.setType(type);
        adminPatientNewsDmo.setLastUpdateTime(DateUtil.getDate());
        adminPatientNewsDmo.setContentType(contentType);

        Result result = adminPatientNewsService.updatePatientNewsDetails(adminPatientNewsDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("修改资讯失败");
        }
        return jsonResult;

    }

    @RequestMapping(value = "/deletePatientNews", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deletePatientNews(DeletePatientNewsForm patientNewsForm, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        String id = patientNewsForm.getId();

        if (StringUtils.isBlank(id)) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        PatientNewsDmo adminPatientNewsDmo = new PatientNewsDmo();

        adminPatientNewsDmo.setId(Long.valueOf(id));
        adminPatientNewsDmo.setStatus(PatientConstants.NewsStatus.DELETE);

        Result result = adminPatientNewsService.updatePatientNewsDetails(adminPatientNewsDmo);

        if (result.isSuccess()) {
            jsonResult.setSuccess(true);
        } else {
            jsonResult.fail("删除资讯失败");
        }
        return jsonResult;

    }

    @RequestMapping(value = "/patientNewsDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult patientNewsDetail(Long id, String url, HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        if (null == id) {
            jsonResult.fail("参数异常");
            return jsonResult;
        }

        PatientNewsDmo con = new PatientNewsDmo();

        con.setId(id);
        con.setStatus(PatientConstants.NewsStatus.NORMAL);

        PatientNewsDmo patientNewsDmo = adminPatientNewsService.selectDoctorNewsDetails(con);

        if (null != patientNewsDmo) {

            ProcessNewsContentResult processResult = newContentService.recoveryImage(patientNewsDmo.getContent(), url);

            if (!processResult.isSuccess()) {

                jsonResult.fail("数据异常");
                return jsonResult;
            }

            //恢复图片URL
            patientNewsDmo.setContent(processResult.getContent());

            jsonResult.setSuccess(true);
            jsonResult.setData(patientNewsDmo);

        } else {
            jsonResult.fail("数据异常");
        }

        return jsonResult;

    }
}
