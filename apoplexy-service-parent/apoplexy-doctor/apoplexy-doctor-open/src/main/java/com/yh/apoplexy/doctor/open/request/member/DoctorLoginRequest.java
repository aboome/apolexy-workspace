package com.yh.apoplexy.doctor.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 医生登录(doc-0002)请求
 * Created by wunder on 16/9/1 14:39.
 */
public class DoctorLoginRequest implements Serializable {

    private static final long serialVersionUID = -2330840521264475217L;

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
