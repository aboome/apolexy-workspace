package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * 发送验证码(common-0001)响应
 * Created by wunder on 16/8/31 20:53.
 */
public class SendVerificationCodeResponse implements Serializable {

    private static final long serialVersionUID = 4471530030583835618L;

    /**
     * 0000：发送成功
     * 0001：1小时内发送验证码过于频繁
     * 0002：手机号码有误
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
