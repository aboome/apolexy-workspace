package com.yh.apoplexy.doctor.open.request.base;

import java.io.Serializable;

/**
 * 医生端App基础请求对象
 * Created by wunder on 16/9/3 13:30.
 */
public class DoctorAppBaseRequest implements Serializable {

    private static final long serialVersionUID = -6958460339237368744L;

    /**
     * 业务码
     */
    private String servicekey;

    /**
     * 唯一编码
     */
    private String uid;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 签名字段
     */
    private String sign;

    /**
     * 请求消息体
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
