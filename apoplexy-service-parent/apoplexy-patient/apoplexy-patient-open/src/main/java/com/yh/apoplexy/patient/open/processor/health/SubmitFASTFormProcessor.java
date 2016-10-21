package com.yh.apoplexy.patient.open.processor.health;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.health.PatientEmergencyDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.health.service.intf.PatientEmergencyService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.health.SubmitFASTFormRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.health.SubmitFASTFormResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 提交FAST自测表单 (pat-0012)处理类
 * Created by wunder on 16/9/5 22:03.
 */
public class SubmitFASTFormProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitHealthDataProcessor.class);

    @Autowired
    private PatientEmergencyService patientEmergencyService;

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        SubmitFASTFormResponse submitFASTFormResponse = new SubmitFASTFormResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SubmitFASTFormRequest submitFASTFormRequest = JSONObject.parseObject(requestObject.getParameter().toString(),SubmitFASTFormRequest.class);

        //参数校验
        if (null == submitFASTFormRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(submitFASTFormRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(submitFASTFormRequest.getUserId());

        Long age = Long.parseLong(submitFASTFormRequest.getAge());

        String sex = submitFASTFormRequest.getSex();

        Date onSetTime = DateUtil.parseDate(submitFASTFormRequest.getOnsetTime(),DateUtil.yyyyMMddHHmmss);

        String face = submitFASTFormRequest.getFace();

        String arm = submitFASTFormRequest.getArm();

        String speech = submitFASTFormRequest.getSpeech();

        PatientMemberDmo patientMemberCon = new PatientMemberDmo();

        patientMemberCon.setId(userId);
        patientMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        PatientMemberDmo patientMemberDmo = patientMemberService.findPatientMember(patientMemberCon);

        if (null == patientMemberDmo){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        PatientEmergencyDmo patientEmergencyDmo = new PatientEmergencyDmo();

        patientEmergencyDmo.setUserId(userId);
        patientEmergencyDmo.setUserName(patientMemberDmo.getRealName());
        patientEmergencyDmo.setAge(age);
        patientEmergencyDmo.setSex(sex);
        patientEmergencyDmo.setOnsetTime(onSetTime);
        patientEmergencyDmo.setFace(face);
        patientEmergencyDmo.setArm(arm);
        patientEmergencyDmo.setSpeech(speech);
        patientEmergencyDmo.setCreateTime(DateUtil.getDate());

        Result result = patientEmergencyService.submitEmergency(patientEmergencyDmo);

        if (!result.isSuccess()){

            submitFASTFormResponse.setResultcode(APPResponseCodeConstants.FastTest.FAILED);
            submitFASTFormResponse.setMessage("提交失败");

        }

        submitFASTFormResponse.setResultcode(APPResponseCodeConstants.FastTest.SUCCESS);
        submitFASTFormResponse.setMessage("提交成功");

        response.setParameter(submitFASTFormResponse);

        return response;
    }
}
