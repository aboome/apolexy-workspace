package com.yh.apoplexy.doctor.open.response.cases.referral;

import java.io.Serializable;

/**
 * 我的-我的申请详情-确认转诊(doc-0026)响应
 * Created by wunder on 16/9/1 20:10.
 */
public class ConfirmReferralCaseResponse implements Serializable {

    private static final long serialVersionUID = 59114427613482233L;

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
