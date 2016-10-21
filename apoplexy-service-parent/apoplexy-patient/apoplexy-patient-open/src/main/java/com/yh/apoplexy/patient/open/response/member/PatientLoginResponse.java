package com.yh.apoplexy.patient.open.response.member;

import java.io.Serializable;

/**
 * Created by wunder on 16/9/1 09:41.
 */
public class PatientLoginResponse implements Serializable {

    private static final long serialVersionUID = 7344600895977123285L;

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
