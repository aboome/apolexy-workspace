package com.yh.apoplexy.patient.open.processor.health;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.health.service.intf.HealthDataService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.health.SubmitHealthDataRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.health.SubmitHealthDataResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 提交体征数据 (pat-0008)处理类
 * Created by wunder on 16/9/5 18:17.
 */
public class SubmitHealthDataProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitHealthDataProcessor.class);

    @Autowired
    private HealthDataService healthDataService;

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        SubmitHealthDataResponse submitHealthDataResponse = new SubmitHealthDataResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SubmitHealthDataRequest submitHealthDataRequest = JSONObject.parseObject(requestObject.getParameter().toString(),SubmitHealthDataRequest.class);

        //参数校验
        if (null == submitHealthDataRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(submitHealthDataRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(submitHealthDataRequest.getUserId());

        Double bloodPressureHigh = Double.parseDouble(submitHealthDataRequest.getBloodPressureHigh());

        Double bloodPressureLow = Double.parseDouble(submitHealthDataRequest.getBloodPressureLow());

        Double bloodSugar = Double.parseDouble(submitHealthDataRequest.getBloodSugar());

        Double bloodFat = Double.parseDouble(submitHealthDataRequest.getBloodFat());

        PatientMemberDmo patientMemberCon = new PatientMemberDmo();

        patientMemberCon.setId(userId);
        patientMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        PatientMemberDmo patientMemberDmo = patientMemberService.findPatientMember(patientMemberCon);

        if (null == patientMemberDmo){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        HealthDataDmo healthDataDmo = new HealthDataDmo();

        healthDataDmo.setUserId(userId);
        healthDataDmo.setUserName(patientMemberDmo.getRealName());
        healthDataDmo.setHighPressure(bloodPressureHigh);
        healthDataDmo.setLowPressure(bloodPressureLow);
        healthDataDmo.setBloodSugar(bloodSugar);
        healthDataDmo.setBloodFat(bloodFat);
        healthDataDmo.setCreateTime(DateUtil.getDate());
        healthDataDmo.setLastUpdateTime(DateUtil.getDate());

        Result result = healthDataService.submitHealthData(healthDataDmo);

        if (!result.isSuccess()){

            submitHealthDataResponse.setResultcode(APPResponseCodeConstants.HealthData.FAILED);
            submitHealthDataResponse.setMessage("提交失败");

        }else {

            submitHealthDataResponse.setResultcode(APPResponseCodeConstants.HealthData.SUCCESS);
            submitHealthDataResponse.setMessage("提交成功");

        }

        response.setParameter(submitHealthDataResponse);

        return response;
    }

}
