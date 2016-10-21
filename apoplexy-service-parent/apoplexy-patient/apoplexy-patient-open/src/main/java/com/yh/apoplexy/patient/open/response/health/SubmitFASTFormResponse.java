package com.yh.apoplexy.patient.open.response.health;

import java.io.Serializable;

/**
 * 提交FAST自测表单 (pat-0012)响应
 * Created by wunder on 16/9/1 13:40.
 */
public class SubmitFASTFormResponse implements Serializable {

    private static final long serialVersionUID = 4046631945231998571L;

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
