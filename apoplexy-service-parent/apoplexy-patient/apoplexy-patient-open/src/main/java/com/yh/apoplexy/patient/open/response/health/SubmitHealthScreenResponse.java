package com.yh.apoplexy.patient.open.response.health;

import java.io.Serializable;

/**
 * 提交健康筛查信息 (pat-0005)响应
 * Created by wunder on 16/9/1 10:17.
 */
public class SubmitHealthScreenResponse implements Serializable {

    private static final long serialVersionUID = 993699876595323749L;

    /**
     * 业务key值，用于响应后台不同业务的标识
     */
    private String resultcode;

    /**
     * 返回请求中的改字符串，用于消息配对，确保消息请求与响应一致性
     */
    private String message;

    /**
     * 发病率，百分比，采用小数表示，精确到小数点后两位
     */
    private String incidence;

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

    public String getIncidence() {
        return incidence;
    }

    public void setIncidence(String incidence) {
        this.incidence = incidence;
    }
}
