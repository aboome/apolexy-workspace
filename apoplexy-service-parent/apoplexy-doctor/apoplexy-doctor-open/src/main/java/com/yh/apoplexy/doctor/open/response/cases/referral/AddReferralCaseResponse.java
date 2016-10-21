package com.yh.apoplexy.doctor.open.response.cases.referral;

import java.io.Serializable;

/**
 * 新增转诊病例(doc-0020)响应
 * Created by wunder on 16/9/1 19:58.
 */
public class AddReferralCaseResponse implements Serializable {

    private static final long serialVersionUID = 4549376473375167323L;

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
