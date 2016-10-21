package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 个人中心-修改基本资料(pat-0016)请求
 * Created by wunder on 16/9/1 13:52.
 */
public class ModifyBaseInfoRequest implements Serializable {

    private static final long serialVersionUID = 2704453284883338578L;

    /**
     * 用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 手机号码
     */
    @StringValidator(nullable = true, pattern = RegularConstants.PHONE_REGULAR)
    private String phone;

    /**
     * 手机验证码
     */
    @StringValidator(nullable = true, pattern = RegularConstants.VALIDATE_CODE_REGULAR)
    private String validateCode;

    /**
     * 出生日期
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String birthday;

    /**
     * 性别(0：男;1：女)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String sex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
