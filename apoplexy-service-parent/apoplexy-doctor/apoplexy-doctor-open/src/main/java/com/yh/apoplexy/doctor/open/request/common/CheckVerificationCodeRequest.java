package com.yh.apoplexy.doctor.open.request.common;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 验证验证码(common-0002)请求
 * Created by wunder on 16/8/31 20:55.
 */
public class CheckVerificationCodeRequest implements Serializable {

    private static final long serialVersionUID = -737907809399483451L;

    /**
     * 手机号码
     */
    @StringValidator(nullable = false, pattern = RegularConstants.PHONE_REGULAR)
    private String phone;

    /**
     * 验证码
     */
    @StringValidator(nullable = false, pattern = RegularConstants.VALIDATE_CODE_REGULAR)
    private String validateCode;

    /**
     * 是否强制失效
     * 0：不强制失效
     * 1：强制失效
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String invalid;

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

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }
}
