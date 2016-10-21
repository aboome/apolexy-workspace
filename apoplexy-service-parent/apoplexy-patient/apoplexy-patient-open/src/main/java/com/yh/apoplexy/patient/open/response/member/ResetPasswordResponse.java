package com.yh.apoplexy.patient.open.response.member;

import java.io.Serializable;

/**
 * 重置密码 (pat-0004)响应
 * Created by wunder on 16/9/1 09:49.
 */
public class ResetPasswordResponse implements Serializable {

    private static final long serialVersionUID = -3166980547583762373L;

    /**
     * 0000：重置密码成功
     * 0001：验证码错误
     * 0002：验证码已失效
     * 0003：重置密码失败
     */
    private String resultcode;

    /**
     * 返回消息
     */
    private String message;

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
}
