package com.yh.apoplexy.patient.open.response.health;

import java.io.Serializable;

/**
 * 提交体征数据 (pat-0008)响应
 * Created by wunder on 16/9/1 10:38.
 */
public class SubmitHealthDataResponse implements Serializable {

    private static final long serialVersionUID = -6756208760121939614L;

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
