package com.yh.apoplexy.doctor.open.response.cases.discuss;

import java.io.Serializable;

/**
 * 我的-我发布的详情-删除病例讨论(doc-0014)响应
 * Created by wunder on 16/9/1 17:09.
 */
public class DeleteDiscussCaseResponse implements Serializable{

    private static final long serialVersionUID = 8253815300036002034L;

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
