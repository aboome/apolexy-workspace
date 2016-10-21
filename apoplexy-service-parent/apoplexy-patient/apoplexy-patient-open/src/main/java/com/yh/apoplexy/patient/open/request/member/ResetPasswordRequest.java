package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 重置密码 (pat-0004)请求
 * Created by wunder on 16/9/1 09:48.
 */
public class ResetPasswordRequest implements Serializable {

    private static final long serialVersionUID = 4904302991283246620L;

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
     * 新密码
     */
    @StringValidator(nullable = false)
    private String newPassword;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
