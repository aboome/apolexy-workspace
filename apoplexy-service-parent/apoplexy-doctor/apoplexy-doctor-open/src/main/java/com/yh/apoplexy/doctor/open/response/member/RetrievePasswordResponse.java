package com.yh.apoplexy.doctor.open.response.member;

import java.io.Serializable;

/**
 * 找回密码(doc-0003)响应
 * Created by wunder on 16/9/1 14:56.
 */
public class RetrievePasswordResponse implements Serializable {

    private static final long serialVersionUID = 2542927609335173445L;

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
