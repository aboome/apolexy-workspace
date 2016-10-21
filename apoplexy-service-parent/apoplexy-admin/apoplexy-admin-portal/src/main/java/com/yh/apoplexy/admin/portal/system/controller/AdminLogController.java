package com.yh.apoplexy.admin.portal.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.portal.system.form.QueryLogListForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.assist.dmo.common.OperateLogDmo;

import com.yh.apoplexy.assist.dto.admin.log.SystemLogInputDto;
import com.yh.apoplexy.assist.service.intf.OperateLogService;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;

/**
 * 系统日志管理
 *
 * @author zhangbiao
 */

@Controller
@RequestMapping(value = "/log")
public class AdminLogController extends BaseController {

    @Autowired
    private OperateLogService operateLogService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String logMange(HttpServletRequest request, Model model) {

        return systemView("log_info");
    }

    /**
     * 查询系统日志列表
     *
     * @param request
     * @param response
     * @param model
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryLogList", method = RequestMethod.POST)
    public String queryLogList(HttpServletRequest request, HttpServletResponse response, Model model, QueryLogListForm form) {

        SystemLogInputDto inputDto = new SystemLogInputDto();

        String userName = form.getUserName();
        String logModel = form.getModel();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        if (StringUtils.isNotBlank(userName)) {
            inputDto.setOperateUserName(userName);
        }

        if (StringUtils.isNotBlank(logModel)) {
            inputDto.setModel(logModel);
        }

        if (StringUtils.isNotBlank(startTime)) {
            inputDto.setStartTime(DateUtil.parseDate(startTime,"yyy-MM-dd"));
        }

        if (StringUtils.isNotBlank(endTime)) {
            inputDto.setEndTime(DateUtil.parseDate(endTime,"yyy-MM-dd"));
        }

        Page page = new Page();

        page.setCurrentPage(Integer.valueOf(form.getPageNum()));
        page.setPageSize(Integer.valueOf(form.getPageSize()));

        List<OperateLogDmo> list = operateLogService.queryOperateLogList(inputDto, page);

        model.addAttribute("logList", list);
        model.addAttribute("page", PageVo.createPageVo(request, page));

        return systemView("log_table");

    }

}
