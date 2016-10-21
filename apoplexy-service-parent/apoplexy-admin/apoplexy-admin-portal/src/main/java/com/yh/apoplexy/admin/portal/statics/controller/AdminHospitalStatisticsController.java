package com.yh.apoplexy.admin.portal.statics.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.apoplexy.admin.statis.intf.HospitalStatisticsService;
import com.yh.apoplexy.assist.dto.statistics.HospitalStatisticsDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yjh.framework.web.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 医院数据统计管理
 *
 * @author zhangbiao
 */

@Controller
@RequestMapping(value = "/hospitalStatistics")
public class AdminHospitalStatisticsController extends BaseController {

    @Autowired
    private HospitalStatisticsService hospitalStatisticsService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String hospitalStatistics(HttpServletRequest request, Model model) {

        return staticsView("hospital_statistics");
    }

    /**
     * 查询医院统计信息
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryHospitalStatistics", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryRegisterProtocol(HttpServletRequest request, HttpServletResponse response, Model model) {

        JsonResult jsonResult = new JsonResult();

        HospitalInfoDmo con = new HospitalInfoDmo();

        con.setStatus(Constants.HospitalStatus.NORMAL);

        List<HospitalStatisticsDto> hospitalStatisticsDtoList = hospitalStatisticsService.queryHospitalCount(con);

        if (!CollectionUtils.isEmpty(hospitalStatisticsDtoList)){

            for (HospitalStatisticsDto hospitalStatisticsDto : hospitalStatisticsDtoList){

                hospitalStatisticsDto.setName(Constants.HOSPITAL_LEVEL_NAME_MAP.get(hospitalStatisticsDto.getName()));
            }

        }

        jsonResult.setSuccess(true);
        jsonResult.setData(hospitalStatisticsDtoList);

        return jsonResult;
    }


}

