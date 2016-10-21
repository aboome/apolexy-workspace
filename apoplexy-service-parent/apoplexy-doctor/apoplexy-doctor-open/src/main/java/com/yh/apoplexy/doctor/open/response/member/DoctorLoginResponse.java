package com.yh.apoplexy.doctor.open.response.member;

import java.io.Serializable;

/**
 * 医生登录(doc-0002)响应
 * Created by wunder on 16/9/1 14:40.
 */
public class DoctorLoginResponse implements Serializable {

    private static final long serialVersionUID = -6786843929000781637L;

    /**
     * 0000：登录成功
     * 0001：用户名或者密码错误
     * 0002：账户已被锁定
     */
    private String resultcode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 用户id
     */
    private String userId;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
