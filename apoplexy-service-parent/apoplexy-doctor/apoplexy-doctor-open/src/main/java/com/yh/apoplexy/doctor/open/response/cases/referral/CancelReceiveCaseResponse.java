package com.yh.apoplexy.doctor.open.response.cases.referral;

import java.io.Serializable;

/**
 * 我的-我的接诊详情-取消接诊(doc-0033)响应
 * Created by wunder on 16/9/1 20:35.
 */
public class CancelReceiveCaseResponse implements Serializable {

    private static final long serialVersionUID = -6040068185221513746L;

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
