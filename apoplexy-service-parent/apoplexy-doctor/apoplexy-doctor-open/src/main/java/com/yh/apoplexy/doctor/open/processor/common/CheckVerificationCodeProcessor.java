package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.CheckVerificationCodeRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.CheckVerificationCodeResponse;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 验证验证码(common-0002)处理类
 * Created by wunder on 16/9/6 23:07.
 */
public class CheckVerificationCodeProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckVerificationCodeProcessor.class);

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        CheckVerificationCodeResponse checkVerificationCodeResponse = new CheckVerificationCodeResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        CheckVerificationCodeRequest checkVerificationCodeRequest = JSONObject.parseObject(requestObject.getParameter().toString(),CheckVerificationCodeRequest.class);

        if (null == checkVerificationCodeRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(checkVerificationCodeRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String phone = checkVerificationCodeRequest.getPhone();

        String validateCode = checkVerificationCodeRequest.getValidateCode();

        String invalid = checkVerificationCodeRequest.getInvalid();

        Result result = validateCodeService.verifyValidateCode(phone,validateCode,invalid);

        if (!result.isSuccess()){

            if ("VERIFY-0000".equals(result.getErrorCode())){

                checkVerificationCodeResponse.setResultcode(APPResponseCodeConstants.VerifyValidateCode.FAILED_CODE_UNEFFECT);
                checkVerificationCodeResponse.setMessage("验证码已失效");

            }else {

                checkVerificationCodeResponse.setResultcode(APPResponseCodeConstants.VerifyValidateCode.FAILED_CODE_ERROR);
                checkVerificationCodeResponse.setMessage("验证码不正确");

            }

            response.setParameter(checkVerificationCodeResponse);
            return response;

        }

        checkVerificationCodeResponse.setResultcode(APPResponseCodeConstants.VerifyValidateCode.SUCCESS);
        checkVerificationCodeResponse.setMessage("验证成功");

        response.setParameter(checkVerificationCodeResponse);
        return response;
    }
}
