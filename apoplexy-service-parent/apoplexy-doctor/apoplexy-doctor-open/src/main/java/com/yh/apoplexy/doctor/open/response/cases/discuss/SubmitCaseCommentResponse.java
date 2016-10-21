package com.yh.apoplexy.doctor.open.response.cases.discuss;

import java.io.Serializable;

/**
 * 病例讨论-评论(doc-0009)响应
 * Created by wunder on 16/9/1 16:42.
 */
public class SubmitCaseCommentResponse implements Serializable {

    private static final long serialVersionUID = -8052828263140322067L;

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
