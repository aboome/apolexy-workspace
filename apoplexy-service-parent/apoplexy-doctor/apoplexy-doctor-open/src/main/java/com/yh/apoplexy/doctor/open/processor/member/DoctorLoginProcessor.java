package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorLoginDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.result.DoctorLoginResult;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.DoctorLoginRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.DoctorLoginResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 医生登录(doc-0002)处理类
 * Created by wunder on 16/9/6 11:23.
 */
public class DoctorLoginProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorLoginProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        DoctorLoginResponse doctorLoginResponse = new DoctorLoginResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        DoctorLoginRequest doctorLoginRequest = JSONObject.parseObject(requestObject.getParameter().toString(), DoctorLoginRequest.class);

        if (null == doctorLoginRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(doctorLoginRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String userName = doctorLoginRequest.getUserName();
        String password = doctorLoginRequest.getPassword();

        DoctorLoginDmo doctorLoginDmo = new DoctorLoginDmo();

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        //判断是手机号码还是邮箱登录
        if (-1 == userName.indexOf('@')) {
            doctorLoginDmo.setUserName(userName);
            doctorMemberCon.setPhone(userName);
        } else {
            doctorLoginDmo.setEmail(userName);
            doctorMemberCon.setEmail(userName);
        }
        doctorLoginDmo.setPassword(password);

        //登录
        DoctorLoginResult result = doctorMemberService.login(doctorLoginDmo);

        if (!result.isSuccess()) {

            //用户名和密码错误
            if ("PL-0001".equals(result.getErrorCode())) {
                doctorLoginResponse.setResultcode(APPResponseCodeConstants.DoctorLogin.FAILED_USER_PWD_ERROR);
                doctorLoginResponse.setMessage("用户名或者密码错误");
                //账户已被锁定
            } else if ("PL-0002".equals(result.getErrorCode())) {
                doctorLoginResponse.setResultcode(APPResponseCodeConstants.DoctorLogin.FAILED_USER_LOCK);
                doctorLoginResponse.setMessage("账户已被锁定");
                //其他错误
            } else {
                throw new AppException(result);
            }

            response.setParameter(doctorLoginResponse);
            return response;

        }

        DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(doctorMemberCon);

        if (null == doctorMemberDmo){

            doctorLoginResponse.setResultcode(APPResponseCodeConstants.DoctorLogin.FAILED_USER_PWD_ERROR);
            doctorLoginResponse.setMessage("用户名或者密码错误");
            return response;

        }

        doctorLoginResponse.setUserId(String.valueOf(doctorMemberDmo.getId()));
        doctorLoginResponse.setResultcode(APPResponseCodeConstants.DoctorLogin.SUCCESS);
        doctorLoginResponse.setMessage("登录成功");

        response.setParameter(doctorLoginResponse);
        return response;

    }

}
