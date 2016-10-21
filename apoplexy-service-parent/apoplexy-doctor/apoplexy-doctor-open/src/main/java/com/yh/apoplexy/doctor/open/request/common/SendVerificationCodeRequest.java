package com.yh.apoplexy.doctor.open.request.common;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 发送验证码(common-0001)请求
 * Created by wunder on 16/8/31 17:39.
 */
public class SendVerificationCodeRequest implements Serializable {

    private static final long serialVersionUID = -8466240353661553548L;

    /**
     * 手机电话号码
     */
    @StringValidator(nullable = false, pattern = RegularConstants.PHONE_REGULAR)
    private String phone;

    /**
     * 00：患者注册
     * 01：患者重置密码
     * 10：医生注册
     * 11：医生重置密码
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
