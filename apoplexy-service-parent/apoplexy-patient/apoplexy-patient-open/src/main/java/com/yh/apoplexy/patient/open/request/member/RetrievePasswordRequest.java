package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 找回密码 (pat-0003)请求
 * Created by wunder on 16/9/1 09:45.
 */
public class RetrievePasswordRequest implements Serializable {

    private static final long serialVersionUID = -920774363395896600L;

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
}
