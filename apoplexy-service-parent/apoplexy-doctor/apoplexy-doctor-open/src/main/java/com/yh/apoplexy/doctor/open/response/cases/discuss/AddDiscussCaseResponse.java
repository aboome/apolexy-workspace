package com.yh.apoplexy.doctor.open.response.cases.discuss;

import java.io.Serializable;

/**
 * 新增病例讨论(doc-0006)响应
 * Created by wunder on 16/9/1 15:36.
 */
public class AddDiscussCaseResponse implements Serializable {

    private static final long serialVersionUID = -8020822753834096044L;

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
