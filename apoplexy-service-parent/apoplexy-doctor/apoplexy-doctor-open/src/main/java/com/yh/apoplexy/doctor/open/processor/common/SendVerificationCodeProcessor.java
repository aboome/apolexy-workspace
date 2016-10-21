package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.ValidateCodeDmo;
import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.SendVerificationCodeRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.SendVerificationCodeResponse;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发送验证码(common-0001)处理类
 * Created by wunder on 16/9/6 22:27.
 */
public class SendVerificationCodeProcessor extends DoctorAppBaseServiceProcessor {

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private PatientMemberService patientMemberService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SendVerificationCodeProcessor.class);

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        SendVerificationCodeResponse sendVerificationCodeResponse = new SendVerificationCodeResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SendVerificationCodeRequest sendVerificationCodeRequest = JSONObject.parseObject(requestObject.getParameter().toString(),SendVerificationCodeRequest.class);

        if (null == sendVerificationCodeRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(sendVerificationCodeRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String phone = sendVerificationCodeRequest.getPhone();

        String type = sendVerificationCodeRequest.getType();

        if (AppConstants.SendValidateCodeType.DOCTOR_RESET_PWD.equals(type)){

            Result phoneResult = doctorMemberService.checkPhoneCanUse(phone);

            if (phoneResult.isSuccess()){

                sendVerificationCodeResponse.setResultcode(APPResponseCodeConstants.SendValidate.NOT_REGISTER);
                sendVerificationCodeResponse.setMessage("手机号码未注册");
                response.setParameter(sendVerificationCodeResponse);
                return response;
            }

        }

        if (AppConstants.SendValidateCodeType.DOCTOR_REGISTER.equals(type)){

            Result phoneResult = doctorMemberService.checkPhoneCanUse(phone);

            if (!phoneResult.isSuccess()){

                sendVerificationCodeResponse.setResultcode(APPResponseCodeConstants.SendValidate.ALREADY_REGISTER);
                sendVerificationCodeResponse.setMessage("手机号码已注册");
                response.setParameter(sendVerificationCodeResponse);
                return response;
            }
        }

        if (AppConstants.SendValidateCodeType.PATIENT_RESET_PWD.equals(type)){

            Result phoneResult = patientMemberService.checkPhoneCanUse(phone);

            if (phoneResult.isSuccess()){

                sendVerificationCodeResponse.setResultcode(APPResponseCodeConstants.SendValidate.NOT_REGISTER);
                sendVerificationCodeResponse.setMessage("手机号码未注册");
                response.setParameter(sendVerificationCodeResponse);
                return response;
            }

        }

        if (AppConstants.SendValidateCodeType.PATIENT_REGISTER.equals(type)||AppConstants.SendValidateCodeType.PATIENT_MODIFY_BASE_INFO.equals(type)){

            Result phoneResult = patientMemberService.checkPhoneCanUse(phone);

            if (!phoneResult.isSuccess()){

                sendVerificationCodeResponse.setResultcode(APPResponseCodeConstants.SendValidate.ALREADY_REGISTER);
                sendVerificationCodeResponse.setMessage("手机号码已注册");
                response.setParameter(sendVerificationCodeResponse);
                return response;
            }
        }

        ValidateCodeDmo validateCodeDmo = new ValidateCodeDmo();

        validateCodeDmo.setPhone(phone);

        //发送短信验证码
        Result result = validateCodeService.sendValidateCode(validateCodeDmo,type);

        if (!result.isSuccess()){

            if ("SMS-0001".equals(result.getErrorCode())){

                sendVerificationCodeResponse.setResultcode(APPResponseCodeConstants.SendValidate.FAILED_SEND_COUNT);
                sendVerificationCodeResponse.setMessage("1小时内发送验证码过于频繁");

            }else if ("SMS-0004".equals(result.getErrorCode())){

                sendVerificationCodeResponse.setResultcode(APPResponseCodeConstants.SendValidate.FAILED_SEND_COUNT);
                sendVerificationCodeResponse.setMessage("手机号码有误");

            }else {

                response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
                return response;
            }

            response.setParameter(sendVerificationCodeResponse);
            return response;
        }

        sendVerificationCodeResponse.setResultcode(APPResponseCodeConstants.SendValidate.SUCCESS);
        sendVerificationCodeResponse.setMessage("发送成功");

        response.setParameter(sendVerificationCodeResponse);
        return response;

    }
}
