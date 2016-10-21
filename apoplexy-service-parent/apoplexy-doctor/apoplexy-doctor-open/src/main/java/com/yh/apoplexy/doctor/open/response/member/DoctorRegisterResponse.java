package com.yh.apoplexy.doctor.open.response.member;

import java.io.Serializable;

/**
 * 医生注册(doc-0001)响应
 * Created by wunder on 16/9/1 14:37.
 */
public class DoctorRegisterResponse implements Serializable {

    private static final long serialVersionUID = 6509260977989183303L;

    /**
     * 0000：注册成功
     * 0001：手机验证码不正确
     * 0002：手机号码已被注册
     */
    private String resultcode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 注册用户id
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
