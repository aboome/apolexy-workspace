package com.yh.apoplexy.doctor.open.response.cases.referral;

import java.io.Serializable;

/**
 * 我的-我的申请详情-撤销转诊(doc-0027)响应
 * Created by wunder on 16/9/1 20:12.
 */
public class CancelReferralCaseResponse implements Serializable{

    private static final long serialVersionUID = 4036882618687628933L;

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
