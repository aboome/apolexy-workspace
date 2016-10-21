package com.yh.apoplexy.patient.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-修改密码(pat-0019)响应
 * Created by wunder on 16/9/1 14:33.
 */
public class ModifyPasswordResponse implements Serializable {

    private static final long serialVersionUID = -3740008336050302695L;

    /**
     * 0000：修改成功
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
