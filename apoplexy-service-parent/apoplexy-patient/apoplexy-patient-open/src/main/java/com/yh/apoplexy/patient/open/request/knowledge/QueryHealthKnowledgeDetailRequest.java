package com.yh.apoplexy.patient.open.request.knowledge;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询健康宣教详情 (pat-0011)请求
 * Created by wunder on 16/9/1 10:58.
 */
public class QueryHealthKnowledgeDetailRequest implements Serializable {

    private static final long serialVersionUID = 1382439786359227630L;

    /**
     * 健康宣教ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String newId;

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }
}
