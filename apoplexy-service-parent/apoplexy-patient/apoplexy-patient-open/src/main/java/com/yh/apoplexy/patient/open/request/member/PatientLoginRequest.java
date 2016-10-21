package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 患者登录 (pat-0002)请求
 * Created by wunder on 16/9/1 09:40.
 */
public class PatientLoginRequest implements Serializable {

    private static final long serialVersionUID = -3543002498499386048L;

    /**
     * 用户名
     */
    @StringValidator(nullable = false, pattern = RegularConstants.PHONE_REGULAR)
    private String userName;

    /**
     * 密码
     */
    @StringValidator(nullable = false)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
