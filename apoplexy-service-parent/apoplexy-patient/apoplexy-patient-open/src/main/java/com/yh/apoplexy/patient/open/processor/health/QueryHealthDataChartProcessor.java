package com.yh.apoplexy.patient.open.processor.health;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.health.service.intf.HealthDataService;
import com.yh.apoplexy.patient.open.dto.health.HealthDataItem;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.health.QueryHealthDataChartRequest;
import com.yh.apoplexy.patient.open.request.health.QueryNearbyScreenDetailRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.health.QueryHealthDataChartResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 查询健康数据趋势图 (pat-0009)处理类
 * Created by wunder on 16/9/5 19:08.
 */
public class QueryHealthDataChartProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryHealthDataChartProcessor.class);

    @Autowired
    private HealthDataService healthDataService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryHealthDataChartResponse queryHealthDataChartResponse = new QueryHealthDataChartResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryHealthDataChartRequest queryHealthDataChartRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryHealthDataChartRequest.class);

        //参数校验
        if (null == queryHealthDataChartRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryHealthDataChartRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryHealthDataChartRequest.getUserId());

        Date beginDate = DateUtil.getZeroTimeOfDay(DateUtil.parseDate(queryHealthDataChartRequest.getBeginDate(),DateUtil.yyyyMMddHHmmss));

        Date endDate = DateUtil.getZeroTimeOfDay(DateUtil.parseDate(queryHealthDataChartRequest.getEndDate(),DateUtil.yyyyMMddHHmmss));

        if (beginDate.after(endDate)){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        List<HealthDataDmo> healthDataDmoList = healthDataService.selectListByDate(userId,beginDate,endDate);

        if (CollectionUtils.isEmpty(healthDataDmoList)){

            response.setParameter(queryHealthDataChartResponse);
            return response;
        }

        List<HealthDataItem> healthDataItemList = new ArrayList<>();

        HealthDataItem healthDataItem = null;

        for(HealthDataDmo healthDataDmo :  healthDataDmoList){

            healthDataItem = new HealthDataItem();

            healthDataItem.setRecordId(String.valueOf(healthDataDmo.getId()));
            healthDataItem.setRecordDate(DateUtil.format(healthDataDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
            healthDataItem.setBloodPressureHigh(String.valueOf(healthDataDmo.getHighPressure()));
            healthDataItem.setBloodPressureLow(String.valueOf(healthDataDmo.getLowPressure()));
            healthDataItem.setBloodSugar(String.valueOf(healthDataDmo.getBloodSugar()));
            healthDataItem.setBloodFat(String.valueOf(healthDataDmo.getBloodFat()));

            healthDataItemList.add(healthDataItem);

        }

        queryHealthDataChartResponse.setList(healthDataItemList);

        response.setParameter(queryHealthDataChartResponse);

        return response;
    }
}
