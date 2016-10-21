package com.yh.apoplexy.doctor.open.response.cases.discuss;

import java.io.Serializable;

/**
 * 病例讨论-收藏/取消收藏(doc-0008)响应
 * Created by wunder on 16/9/1 16:37.
 */
public class CollectDiscussCaseResponse implements Serializable {

    private static final long serialVersionUID = 8415331669397892123L;

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
