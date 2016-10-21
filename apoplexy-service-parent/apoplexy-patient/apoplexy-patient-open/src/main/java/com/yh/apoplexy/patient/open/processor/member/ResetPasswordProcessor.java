package com.yh.apoplexy.patient.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.member.PatientRegisterRequest;
import com.yh.apoplexy.patient.open.request.member.ResetPasswordRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.member.PatientRegisterResponse;
import com.yh.apoplexy.patient.open.response.member.ResetPasswordResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 重置密码 (pat-0004)处理类
 * Created by wunder on 16/9/4 19:47.
 */
public class ResetPasswordProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetrievePasswordProcessor.class);

    @Autowired
    private PatientMemberService patientMemberService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ResetPasswordRequest resetPasswordRequest = JSONObject.parseObject(requestObject.getParameter().toString(), ResetPasswordRequest.class);

        if (null == resetPasswordRequest) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(resetPasswordRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String phone = resetPasswordRequest.getPhone();

        String validateCode = resetPasswordRequest.getValidateCode();

        //验证短信验证码
        Result result = validateCodeService.verifyValidateCode(phone, validateCode, AppConstants.VerifyValidateCodeInvalid.INVALID);

        if (!result.isSuccess()) {

            if ("VERIFY-0001".equals(result.getErrorCode())) {
                resetPasswordResponse.setResultcode(APPResponseCodeConstants.PatientResetPwd.FAILED_VALIDATECODE_ERROR);
                resetPasswordResponse.setMessage("验证码错误");
            } else {
                resetPasswordResponse.setResultcode(APPResponseCodeConstants.PatientResetPwd.FAILED_VALIDATECODE_UNEFFECT);
                resetPasswordResponse.setMessage("验证码已失效");
            }

            response.setParameter(resetPasswordResponse);
            return response;
        }

        String newPassword = resetPasswordRequest.getNewPassword();

        result = patientMemberService.resetPassword(phone, newPassword);

        if (!result.isSuccess()) {

            resetPasswordResponse.setResultcode(APPResponseCodeConstants.PatientResetPwd.FAILED_RESET_PWD);
            resetPasswordResponse.setMessage("重置密码失败");
            response.setParameter(resetPasswordResponse);
            return response;
        }

        resetPasswordResponse.setResultcode(APPResponseCodeConstants.PatientResetPwd.SUCCESS);
        resetPasswordResponse.setMessage("重置密码成功");
        response.setParameter(resetPasswordResponse);
        return response;
    }
}
