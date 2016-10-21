package com.yh.apoplexy.doctor.open.response.cases.ast;

import java.io.Serializable;

/**
 * 我的-我发布的详情-撤销(doc-0044)响应
 * Created by wunder on 16/9/2 14:42.
 */
public class CancelAstCaseResponse implements Serializable {

    private static final long serialVersionUID = -390351835895041847L;

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
