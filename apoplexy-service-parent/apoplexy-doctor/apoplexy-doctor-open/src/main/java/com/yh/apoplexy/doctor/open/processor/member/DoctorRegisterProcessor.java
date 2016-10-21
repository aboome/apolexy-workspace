package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorLoginDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.base.service.intf.DoctorInfoService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.DoctorRegisterRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.DoctorRegisterResponse;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 医生注册(doc-0001)处理类
 * Created by wunder on 16/9/6 10:41.
 */
public class DoctorRegisterProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorRegisterProcessor.class);

    @Autowired
    private DoctorInfoService doctorInfoService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        DoctorRegisterResponse doctorRegisterResponse = new DoctorRegisterResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        DoctorRegisterRequest doctorRegisterRequest = JSONObject.parseObject(requestObject.getParameter().toString(),DoctorRegisterRequest.class);

        if (null == doctorRegisterRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(doctorRegisterRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String phone = doctorRegisterRequest.getPhone();
        String password = doctorRegisterRequest.getPassword();
        String validateCode = doctorRegisterRequest.getValidateCode();

        //验证短信验证码
        Result result = validateCodeService.verifyValidateCode(phone,validateCode, AppConstants.VerifyValidateCodeInvalid.INVALID);

        if (!result.isSuccess()){

            doctorRegisterResponse.setResultcode(APPResponseCodeConstants.DoctorRegister.FAILED_VALIDATECODE_ERROR);
            doctorRegisterResponse.setMessage("手机验证码不正确");

            response.setParameter(doctorRegisterResponse);
            return response;
        }

        DoctorInfoDmo doctorInfoDmo = doctorInfoService.findDoctorByPhone(phone);

        if (null == doctorInfoDmo){

            doctorRegisterResponse.setResultcode(APPResponseCodeConstants.DoctorRegister.FAILED_DOCTOR_NOT_EXIST);
            doctorRegisterResponse.setMessage("医生信息未录入");

            response.setParameter(doctorRegisterResponse);
            return response;

        }

        //检查手机号码是否可用
        result = doctorMemberService.checkPhoneCanUse(phone);

        if (!result.isSuccess()){

            doctorRegisterResponse.setResultcode(APPResponseCodeConstants.DoctorRegister.FAILED_PHONE_EXIST);
            doctorRegisterResponse.setMessage("手机号码已被注册");

            response.setParameter(doctorRegisterResponse);
            return response;

        }

        DoctorMemberDmo doctorMemberDmo = new DoctorMemberDmo();

        doctorMemberDmo.setPhone(phone);
        doctorMemberDmo.setDoctorName(doctorInfoDmo.getDoctorName());
        doctorMemberDmo.setSex(doctorInfoDmo.getSex());
        doctorMemberDmo.setEmail(doctorInfoDmo.getEmail());
        doctorMemberDmo.setHospital(doctorInfoDmo.getHospital());
        doctorMemberDmo.setDepartment(doctorInfoDmo.getDepartment());
        doctorMemberDmo.setTitle(doctorInfoDmo.getTitle());
        doctorMemberDmo.setJob(doctorInfoDmo.getJob());
        doctorMemberDmo.setEffectiveTime(doctorInfoDmo.getEffectiveTime());

        DoctorLoginDmo doctorLoginDmo = new DoctorLoginDmo();

        doctorLoginDmo.setUserName(phone);
        doctorLoginDmo.setEmail(doctorInfoDmo.getEmail());
        doctorLoginDmo.setPassword(password);

        //注册
        result = doctorMemberService.register(doctorMemberDmo,doctorLoginDmo);

        if (!result.isSuccess()){
            throw new AppException(result);
        }

        doctorRegisterResponse.setResultcode(APPResponseCodeConstants.DoctorRegister.SUCCESS);
        doctorRegisterResponse.setMessage("注册成功");

        response.setParameter(doctorRegisterResponse);
        return response;

    }
}
