package com.yh.apoplexy.doctor.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-修改工作岗位(doc-0055)响应
 * Created by wunder on 16/9/2 15:44.
 */
public class ModifyDoctorJobResponse implements Serializable {

    private static final long serialVersionUID = 8994967794957856071L;

    /**
     * 0000:提交成功
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