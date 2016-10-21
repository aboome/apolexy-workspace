package com.yh.apoplexy.doctor.open.response.cases.referral;

import java.io.Serializable;

/**
 * 转诊病例详情-接诊(doc-0022)响应
 * Created by wunder on 16/9/1 19:56.
 */
public class SubmitCaseReceiveResponse implements Serializable {

    private static final long serialVersionUID = -8660868971777539994L;

    /**
     * 0000:提交成功
     */
    private String status;

    /**
     * 返回消息
     */
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
