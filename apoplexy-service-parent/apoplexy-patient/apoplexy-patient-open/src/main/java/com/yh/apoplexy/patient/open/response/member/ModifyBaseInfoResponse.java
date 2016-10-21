package com.yh.apoplexy.patient.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-修改基本资料(pat-0016)响应
 * Created by wunder on 16/9/1 13:54.
 */
public class ModifyBaseInfoResponse implements Serializable {

    private static final long serialVersionUID = 8648634677048422694L;

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
