package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * 意见反馈接口 (common-0012)响应
 * Created by wunder on 16/9/7 00:45.
 */
public class SubmitIdeaResponse implements Serializable {

    private static final long serialVersionUID = -5888948617378003675L;

    /**
     * 0000：提交成功
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
