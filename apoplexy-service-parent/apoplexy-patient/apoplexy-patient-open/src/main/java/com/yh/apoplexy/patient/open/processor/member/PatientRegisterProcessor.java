package com.yh.apoplexy.patient.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.ValidateCodeDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientLoginDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.member.PatientRegisterRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.member.PatientRegisterResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 患者注册 (pat-0001)处理类
 * Created by wunder on 16/9/4 15:03.
 */
public class PatientRegisterProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientRegisterProcessor.class);

    @Autowired
    private PatientMemberService patientMemberService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        PatientRegisterResponse patientRegisterResponse = new PatientRegisterResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        PatientRegisterRequest patientRegisterRequest = JSONObject.parseObject(requestObject.getParameter().toString(),PatientRegisterRequest.class);

        //参数校验
        if (null == patientRegisterRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(patientRegisterRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String phone = patientRegisterRequest.getPhone();

        String validateCode = patientRegisterRequest.getValidateCode();

        //验证短信验证码
        Result result = validateCodeService.verifyValidateCode(phone,validateCode, AppConstants.VerifyValidateCodeInvalid.INVALID);

        if (!result.isSuccess()){

            patientRegisterResponse.setResultcode(APPResponseCodeConstants.PatientRegister.FAILED_VALIDATECODE_ERROR);
            patientRegisterResponse.setMessage("手机验证码不正确");

            response.setParameter(patientRegisterResponse);
            return response;
        }

        //检查手机号码是否可用
        result = patientMemberService.checkPhoneCanUse(phone);

        if (!result.isSuccess()){

            patientRegisterResponse.setResultcode(APPResponseCodeConstants.PatientRegister.FAILED_PHONE_EXIST);
            patientRegisterResponse.setMessage("手机号码已被注册");

            response.setParameter(patientRegisterResponse);
            return response;

        }

        PatientMemberDmo patientMemberDmo = new PatientMemberDmo();

        patientMemberDmo.setPhone(patientRegisterRequest.getPhone());
        patientMemberDmo.setRealName(patientRegisterRequest.getRealName());
        patientMemberDmo.setSex(patientRegisterRequest.getSex());
        if (StringUtils.isNotBlank(patientRegisterRequest.getBirthday())){
            patientMemberDmo.setBirthday(DateUtil.getZeroTimeOfDay(DateUtil.parseDate(patientRegisterRequest.getBirthday(),DateUtil.yyyyMMddHHmmss)));
        }

        PatientLoginDmo patientLoginDmo = new PatientLoginDmo();

        patientLoginDmo.setUserName(patientRegisterRequest.getPhone());
        patientLoginDmo.setPassword(patientRegisterRequest.getPassword());

        //注册
        result = patientMemberService.register(patientMemberDmo,patientLoginDmo);

        if (!result.isSuccess()){
            throw new AppException(result);
        }

        PatientMemberDmo patientMemberCon = new PatientMemberDmo();

        patientMemberCon.setPhone(patientRegisterRequest.getPhone());

        PatientMemberDmo existPatientMemberDmo = patientMemberService.findPatientMember(patientMemberCon);

        if (null == existPatientMemberDmo){

            throw new AppException(result);
        }

        patientRegisterResponse.setResultcode(APPResponseCodeConstants.PatientRegister.SUCCESS);
        patientRegisterResponse.setMessage("注册成功");
        patientRegisterResponse.setUserId(String.valueOf(existPatientMemberDmo.getId()));

        response.setParameter(patientRegisterResponse);
        return response;
    }
}
