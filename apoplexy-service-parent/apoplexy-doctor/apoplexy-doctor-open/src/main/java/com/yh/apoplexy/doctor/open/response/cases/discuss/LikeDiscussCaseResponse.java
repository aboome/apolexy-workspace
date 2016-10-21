package com.yh.apoplexy.doctor.open.response.cases.discuss;

import java.io.Serializable;

/**
 * 病例讨论-点赞/取消点赞(doc-0011)响应
 * Created by wunder on 16/9/1 16:51.
 */
public class LikeDiscussCaseResponse implements Serializable{

    private static final long serialVersionUID = 2874906647533645458L;

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
