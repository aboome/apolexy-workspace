package com.yh.apoplexy.doctor.open.response.cases.ast;

import java.io.Serializable;

/**
 * 新增AST病例(doc-0035)响应
 * Created by wunder on 16/9/2 09:15.
 */
public class AddAstCaseResponse implements Serializable {

    private static final long serialVersionUID = 368242730350329641L;

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
