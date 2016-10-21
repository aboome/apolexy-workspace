package com.yh.apoplexy.doctor.open.response.cases.referral;

import java.io.Serializable;

/**
 * 我的-我的接诊详情-评分(doc-0032)响应
 * Created by wunder on 16/9/1 20:31.
 */
public class ScoreReferralCaseResponse implements Serializable {

    private static final long serialVersionUID = -2907977300783781355L;

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
