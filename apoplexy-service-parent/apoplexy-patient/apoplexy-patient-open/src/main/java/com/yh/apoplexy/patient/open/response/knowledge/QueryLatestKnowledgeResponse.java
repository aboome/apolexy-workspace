package com.yh.apoplexy.patient.open.response.knowledge;

import java.io.Serializable;

/**
 * 查询最新健康宣教信息(pat-0020)响应类
 * Created by wunder on 16/9/22 20:10.
 */
public class QueryLatestKnowledgeResponse implements Serializable {

    private static final long serialVersionUID = -2095267716707603855L;

    private String recordId;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
