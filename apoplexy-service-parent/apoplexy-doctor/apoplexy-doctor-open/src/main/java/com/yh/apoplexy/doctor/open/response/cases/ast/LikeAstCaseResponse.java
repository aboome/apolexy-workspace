package com.yh.apoplexy.doctor.open.response.cases.ast;

import java.io.Serializable;

/**
 * AST病例详情-点赞/取消点赞(doc-0040)响应
 * Created by wunder on 16/9/2 14:27.
 */
public class LikeAstCaseResponse implements Serializable {

    private static final long serialVersionUID = -2010418037479008475L;

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
