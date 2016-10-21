package com.yh.apoplexy.doctor.open.response.cases.ast;

import java.io.Serializable;

/**
 * AST病例详情-回复评论(doc-0039)响应
 * Created by wunder on 16/9/2 14:23.
 */
public class ReplyAstCaseDiscussResponse implements Serializable {

    private static final long serialVersionUID = -1860474775310951029L;

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
