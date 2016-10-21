package com.yh.apoplexy.doctor.open.request.cases.discuss;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我收藏的详情(doc-0018)请求
 * Created by wunder on 16/9/1 18:47.
 */
public class QueryMyCollectCaseDetailRequest implements Serializable {

    private static final long serialVersionUID = -2878822468155967362L;

    /**
     * 病例ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    /**
     * 用户id
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
