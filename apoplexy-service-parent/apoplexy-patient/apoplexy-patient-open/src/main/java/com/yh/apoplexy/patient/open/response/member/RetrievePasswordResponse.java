package com.yh.apoplexy.patient.open.response.member;

import java.io.Serializable;

/**
 * 找回密码 (pat-0003)响应
 * Created by wunder on 16/9/1 09:46.
 */
public class RetrievePasswordResponse implements Serializable {

    private static final long serialVersionUID = 4124899823020581240L;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
