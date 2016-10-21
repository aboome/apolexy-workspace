package com.yh.apoplexy.patient.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.member.PatientLoginDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.member.result.PatientLoginResult;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.member.PatientLoginRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.member.PatientLoginResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 患者登录 (pat-0002)处理类
 * Created by wunder on 16/9/4 18:39.
 */
public class PatientLoginProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientRegisterProcessor.class);

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        PatientLoginResponse patientLoginResponse = new PatientLoginResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        PatientLoginRequest patientLoginRequest = JSONObject.parseObject(requestObject.getParameter().toString(),PatientLoginRequest.class);

        //参数校验
        if (null == patientLoginRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(patientLoginRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String userName = patientLoginRequest.getUserName();
        String password = patientLoginRequest.getPassword();

        PatientLoginDmo patientLoginDmo = new PatientLoginDmo();

        patientLoginDmo.setUserName(userName);
        patientLoginDmo.setPassword(password);

        //登录
        PatientLoginResult loginResult = patientMemberService.login(patientLoginDmo);

        if (!loginResult.isSuccess()){

            //用户名和密码错误
            if ("PL-0001".equals(loginResult.getErrorCode())){
                patientLoginResponse.setResultcode(APPResponseCodeConstants.PatientLogin.FAILED_USERNAME_PWD_ERROR);
                patientLoginResponse.setMessage("用户名或者密码错误");
            //账户已被锁定
            }else if ("PL-0002".equals(loginResult.getErrorCode())){
                patientLoginResponse.setResultcode(APPResponseCodeConstants.PatientLogin.FAILED_USERNAME_LOCK);
                patientLoginResponse.setMessage("账户已被锁定");
            //其他错误
            }else {
                throw new AppException(loginResult);
            }

            response.setParameter(patientLoginResponse);
            return response;

        }

        patientLoginResponse.setResultcode(APPResponseCodeConstants.PatientLogin.SUCCESS);
        patientLoginResponse.setMessage("登录成功");
        patientLoginResponse.setUserId(String.valueOf(loginResult.getUserId()));

        response.setParameter(patientLoginResponse);
        return response;
    }
}
