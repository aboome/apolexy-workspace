package com.yh.apoplexy.doctor.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 医生注册(doc-0001)请求
 * Created by wunder on 16/9/1 14:35.
 */
public class DoctorRegisterRequest implements Serializable {

    private static final long serialVersionUID = -8190835344008187221L;

    /**
     * 手机号码
     */
    @StringValidator(nullable = false, pattern = RegularConstants.PHONE_REGULAR)
    private String phone;

    /**
     * 手机验证码
     */
    @StringValidator(nullable = false, pattern = RegularConstants.VALIDATE_CODE_REGULAR)
    private String validateCode;

    /**
     * 登录密码
     */
    @StringValidator(nullable = false)
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
