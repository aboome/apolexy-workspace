package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 患者注册 (pat-0001)请求
 * Created by wunder on 16/9/1 08:59.
 */
public class PatientRegisterRequest implements Serializable {

    private static final long serialVersionUID = -2218782726486009105L;

    /**
     * 真实姓名
     */
    @StringValidator(nullable = false, pattern = RegularConstants.REAL_NAME_REGULAR)
    private String realName;

    /**
     * 性别：0：男   1：女
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String sex;

    /**
     * 出生日期
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String birthday;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

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
