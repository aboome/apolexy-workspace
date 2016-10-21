package com.yh.apoplexy.patient.open.response.base;

import java.io.Serializable;

/**
 * 患者端App基础响应对象
 * Created by wunder on 16/9/4 14:32.
 */
public class PatientAppBaseResponse implements Serializable {

    private static final long serialVersionUID = 1181951343484859309L;

    /**
     * 业务编码
     */
    private String servicekey;

    /**
     * 唯一标识
     */
    private String uid;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 响应编码
     */
    private String resultcode;

    /**
     * 签名字段
     */
    private String sign;

    /**
     * 响应消息体
     */
    private Object parameter;

    public String getServicekey() {
        return servicekey;
    }

    public void setServicekey(String servicekey) {
        this.servicekey = servicekey;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
