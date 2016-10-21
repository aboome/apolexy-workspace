package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * 验证验证码(common-0002)响应
 * Created by wunder on 16/8/31 20:57.
 */
public class CheckVerificationCodeResponse implements Serializable {

    private static final long serialVersionUID = 2168287275121545183L;

    /**
     * 0000：验证成功
     * 0001：验证码不正确
     * 0002：验证码已失效
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
