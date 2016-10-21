package com.yh.apoplexy.patient.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.common.utils.EncryptUtil;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.member.RetrievePasswordRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.member.RetrievePasswordResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 找回密码 (pat-0003)处理类
 * Created by wunder on 16/9/4 19:46.
 */
public class RetrievePasswordProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetrievePasswordProcessor.class);

    @Autowired
    private PatientMemberService patientMemberService;

    @Autowired
    private ValidateCodeService validateCodeService;

    String passwordLengthString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.RETRIEVE_PASSWORD_LENGTH);

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        RetrievePasswordResponse retrievePasswordResponse = new RetrievePasswordResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        RetrievePasswordRequest retrievePasswordRequest = JSONObject.parseObject(requestObject.getParameter().toString(),RetrievePasswordRequest.class);

        if (null == retrievePasswordRequest) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(retrievePasswordRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String phone = retrievePasswordRequest.getPhone();

        String validateCode = retrievePasswordRequest.getValidateCode();

        //验证短信验证码
        Result result = validateCodeService.verifyValidateCode(phone, validateCode, AppConstants.VerifyValidateCodeInvalid.INVALID);

        if (!result.isSuccess()) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        int passwordLength = Integer.parseInt(passwordLengthString);

        String newPassword = RandomStringUtils.randomAlphanumeric(passwordLength);
        //重置密码
        result = patientMemberService.resetPassword(phone, EncryptUtil.md5Encrypt(newPassword));

        if (!result.isSuccess()) {
            throw new AppException(result);
        }

        retrievePasswordResponse.setPhone(phone);
        retrievePasswordResponse.setPassword(newPassword);

        response.setParameter(retrievePasswordResponse);
        return response;

    }
}
