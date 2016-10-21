package com.yh.apoplexy.admin.portal.statics.controller;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.statics.forms.QueryDownloadStatisticsForm;
import com.yh.apoplexy.admin.portal.statics.forms.QueryPatientRegisterStatisticsForm;
import com.yh.apoplexy.admin.portal.statics.forms.queryRegisterStatisticsForm;
import com.yh.apoplexy.admin.statis.intf.DownloadStatisticsService;
import com.yh.apoplexy.admin.statis.result.DownloadStatisticInfoResult;
import com.yh.apoplexy.admin.statis.result.PatientRegisterStatisticsInfoResult;
import com.yh.apoplexy.admin.statis.result.ReferralStatisticsInfoResult;
import com.yh.apoplexy.assist.dto.statistics.DownloadStatisticInfoDto;
import com.yh.apoplexy.assist.dto.statistics.PatientRegisterStatisticsInfoDto;
import com.yh.apoplexy.assist.dto.statistics.QueryDoctorRegisterStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.QueryDownloadStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.QueryPatientRegisterStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.ReferralStatisticsInfoDto;
import com.yjh.framework.utils.DateUtil;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wunder on 16/9/26 19:35.
 */
@Controller
@RequestMapping(value = "/downloadStatistics")
public class AdminDownloadStaticsController extends BaseController {

    @Autowired
    private DownloadStatisticsService downloadStatisticsService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String downloadStatistics(HttpServletRequest request, Model model) {

        return staticsView("download_statistics");
    }

    /**
     * 查询下载统计信息
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryDownloadStatistics", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryDownloadSattistics(HttpServletRequest request, HttpServletResponse response, Model model,QueryDownloadStatisticsForm queryDownloadStatisticsForm) {

        JsonResult jsonResult = new JsonResult();

        QueryDownloadStatisticsDto con = new QueryDownloadStatisticsDto();

        String startDateString = queryDownloadStatisticsForm.getStartDate();

        String endDateString = queryDownloadStatisticsForm.getEndDate();

        String type = queryDownloadStatisticsForm.getType();

        Date endDate = DateUtil.getZeroTimeOfDay(DateUtil.getDate());

        Date startDate = DateUtil.addDays(endDate,-30);

        if (StringUtils.isNotBlank(startDateString)&&StringUtils.isNotBlank(endDateString)){

            startDate = DateUtil.parseDate(queryDownloadStatisticsForm.getStartDate(),"yyyy-MM-dd");

            endDate = DateUtil.parseDate(queryDownloadStatisticsForm.getEndDate(),"yyyy-MM-dd");
            
            if(startDate.after(endDate)){
            	
            	jsonResult.fail("查询起始时间不能晚于查询结束时间");
            	return jsonResult;
            }

        }

        con.setStartDate(startDate);
        con.setEndDate(endDate);
        con.setType(type);

        List<DownloadStatisticInfoDto> downloadStatisticInfoDtoList = downloadStatisticsService.queryDownloadTestCount(con);
        
        long dateNum = (endDate.getTime()-startDate.getTime())/(1000*3600*24);
        
        List<Long> countNum = new ArrayList<>();

        List<String> countDate = new ArrayList<>();

        for(int i = 0;i<dateNum;i++){
        	
        	Date currentDate = DateUtil.addDays(startDate, i);

            countDate.add(DateUtil.format(currentDate, "yyyy-MM-dd"));

            int j;

        	for(j = 0;j<downloadStatisticInfoDtoList.size();j++){
        		
        		if(currentDate.equals(downloadStatisticInfoDtoList.get(j).getCountDate())){

                    countNum.add(downloadStatisticInfoDtoList.get(j).getCountNum());
        			break;
        			
        		}
        	}

        	if (j >= downloadStatisticInfoDtoList.size()){
                countNum.add(0L);
            }

        }

        DownloadStatisticInfoResult downloadStatisticInfoResult = new DownloadStatisticInfoResult();

        downloadStatisticInfoResult.setCountDate(countDate);
        downloadStatisticInfoResult.setCountNum(countNum);
        
        jsonResult.setSuccess(true);
        jsonResult.setData(downloadStatisticInfoResult);

        return jsonResult;
    }

    
    /**
     * 医生端注册量统计
     * @param request
     * @param response
     * @param model
     * @param queryRegisterStatisticsForm
     * @return
     */
    @RequestMapping(value = "/queryDoctorRegister", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryDoctorRegister(HttpServletRequest request, HttpServletResponse response, Model model,queryRegisterStatisticsForm queryRegisterStatisticsForm ) {
    	 
    	JsonResult jsonResult = new JsonResult();
    	   
    	 QueryDoctorRegisterStatisticsDto dto = new QueryDoctorRegisterStatisticsDto();
  	   
         String startDateString = queryRegisterStatisticsForm.getStartDate();

         String endDateString = queryRegisterStatisticsForm.getEndDate();
         
         Date endDate = DateUtil.getZeroTimeOfDay(DateUtil.getDate());

         Date startDate = DateUtil.addDays(endDate,-30);
         
        
         
         if (StringUtils.isNotBlank(startDateString)&&StringUtils.isNotBlank(endDateString)){

             startDate = DateUtil.parseDate(queryRegisterStatisticsForm.getStartDate(),"yyyy-MM-dd");

             endDate = DateUtil.parseDate(queryRegisterStatisticsForm.getEndDate(),"yyyy-MM-dd");
             
             if(startDate.after(endDate)){
             	
             	jsonResult.fail("查询起始时间不能晚于查询结束时间");
             	return jsonResult;
             }
         }
             
             dto.setStartDate(startDate);
             dto.setEndDate(endDate);
             
             List<ReferralStatisticsInfoDto> referralStatisticsInfoDtosList  = downloadStatisticsService.queryDoctorRegisterCount(dto);
             
             long dateNum = (endDate.getTime()-startDate.getTime())/(1000*3600*24);
             
             List<Long> countNum = new ArrayList<>();

             List<String> countDate = new ArrayList<>();
             
             for(int i = 0;i<dateNum;i++){
          	   
             	Date currentDate = DateUtil.addDays(startDate, i);

                countDate.add(DateUtil.format(currentDate, "yyyy-MM-dd"));
                int j;
          	   
          	   	for( j = 0;j<referralStatisticsInfoDtosList.size();j++){
              		
              		if(currentDate.equals(referralStatisticsInfoDtosList.get(j).getCountDate())){
              			
                        countNum.add(referralStatisticsInfoDtosList.get(j).getCountNum());
              			
              			break;
              			
              		}
              	
              	}
              	if (j >= referralStatisticsInfoDtosList.size()){
                    countNum.add(0L);
                }
             }
             
         ReferralStatisticsInfoResult referralStatisticsInfoResults = new ReferralStatisticsInfoResult();
         referralStatisticsInfoResults.setCountDate(countDate);
         referralStatisticsInfoResults.setCountNum(countNum);
       
         jsonResult.setSuccess(true);
         jsonResult.setData(referralStatisticsInfoResults);

		return jsonResult;
    
    }
    
    /**
     * 患者端注册量统计
     * @param request
     * @param response
     * @param model
     * @param queryPatientRegisterStatisticsForm
     * @return
     */
    @RequestMapping(value = "/queryPatientRegister", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult queryPatientRegister(HttpServletRequest request, HttpServletResponse response, Model model,QueryPatientRegisterStatisticsForm queryPatientRegisterStatisticsForm) {
    	
    	JsonResult jsonResult = new JsonResult();	
    	
    	QueryPatientRegisterStatisticsDto patientRegisterStatisticsDto = new QueryPatientRegisterStatisticsDto(); 
    	
        String startDateString = queryPatientRegisterStatisticsForm.getStartDate();

        String endDateString = queryPatientRegisterStatisticsForm.getEndDate();
        
        Date endDate = DateUtil.getZeroTimeOfDay(DateUtil.getDate());

        Date startDate = DateUtil.addDays(endDate,-30);
    	
        if (StringUtils.isNotBlank(startDateString)&&StringUtils.isNotBlank(endDateString)){

            startDate = DateUtil.parseDate(queryPatientRegisterStatisticsForm.getStartDate(),"yyyy-MM-dd");

            endDate = DateUtil.parseDate(queryPatientRegisterStatisticsForm.getEndDate(),"yyyy-MM-dd");
            
            if(startDate.after(endDate)){
            	
            	jsonResult.fail("查询起始时间不能晚于查询结束时间");
            	return jsonResult;
            }
        }
        
        patientRegisterStatisticsDto.setStartDate(startDate);
        patientRegisterStatisticsDto.setEndDate(endDate);
        
        List<PatientRegisterStatisticsInfoDto> patientRegisterStatisticsInfoDtosList = downloadStatisticsService.queryPatientRegisterCount(patientRegisterStatisticsDto);
        
        long dateNum = (endDate.getTime()-startDate.getTime())/(1000*3600*24);
        
        List<Long> countNum = new ArrayList<>();

        List<String> countDate = new ArrayList<>();
        
        for(int i = 0;i<dateNum;i++){
       	   
         	Date currentDate = DateUtil.addDays(startDate, i);

            countDate.add(DateUtil.format(currentDate, "yyyy-MM-dd"));
            int j;
      	   
      	   	for( j = 0;j<patientRegisterStatisticsInfoDtosList.size();j++){
          		
          		if(currentDate.equals(patientRegisterStatisticsInfoDtosList.get(j).getCountDate())){
          			
                    countNum.add(patientRegisterStatisticsInfoDtosList.get(j).getCountNum());
          			
          			break;
          			
          		}
          	
          	}
          	if (j >= patientRegisterStatisticsInfoDtosList.size()){
                countNum.add(0L);
            }
         }
        
        PatientRegisterStatisticsInfoResult patientRegisterStatisticsInfoResult  = new PatientRegisterStatisticsInfoResult(); 
        patientRegisterStatisticsInfoResult.setCountDate(countDate);
        patientRegisterStatisticsInfoResult.setCountNum(countNum);
        
        jsonResult.setSuccess(true);
        jsonResult.setData(patientRegisterStatisticsInfoResult);

		return jsonResult;
    	
    }

}
