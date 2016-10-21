package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * 绑定消息推送客户端ID(common-0009)响应
 * Created by wunder on 16/9/2 16:53.
 */
public class BindMessageClientResponse implements Serializable {

    private static final long serialVersionUID = 3580188022626668474L;

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
