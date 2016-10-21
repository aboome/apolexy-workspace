package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * APP下载统计 (common-0011)响应
 * Created by wunder on 16/9/6 23:27.
 */
public class ReportAppDownloadResponse implements Serializable {

    private static final long serialVersionUID = -659408404815636605L;

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
