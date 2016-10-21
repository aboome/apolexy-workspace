package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * 注册协议接口 (common-0013)响应实体
 * Created by wunder on 2016/10/9 19:34.
 */
public class QueryProtocolResponse implements Serializable {

    private static final long serialVersionUID = -3226915205797307785L;

    /**
     * 注册协议内容
     */
    private String protocolContent;

    public String getProtocolContent() {
        return protocolContent;
    }

    public void setProtocolContent(String protocolContent) {
        this.protocolContent = protocolContent;
    }
}
