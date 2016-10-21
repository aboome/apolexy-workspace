package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.common.utils.EncryptUtil;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.RetrievePasswordRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.RetrievePasswordResponse;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 找回密码(doc-0003)处理类
 * Created by wunder on 16/9/6 15:11.
 */
public class RetrievePasswordProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetrievePasswordProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private ValidateCodeService validateCodeService;

    String passwordLengthString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.RETRIEVE_PASSWORD_LENGTH);

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        RetrievePasswordResponse retrievePasswordResponse = new RetrievePasswordResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        RetrievePasswordRequest retrievePasswordRequest = JSONObject.parseObject(requestObject.getParameter().toString(),RetrievePasswordRequest.class);

        if (null == retrievePasswordRequest){

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
        result = doctorMemberService.resetPassword(phone, EncryptUtil.md5Encrypt(newPassword));

        if (!result.isSuccess()) {
            throw new AppException(result);
        }

        retrievePasswordResponse.setPhone(phone);
        retrievePasswordResponse.setPassword(newPassword);

        response.setParameter(retrievePasswordResponse);
        return response;

    }
}
