package com.yh.apoplexy.doctor.open.request.cases.discuss;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 病例讨论-点赞/取消点赞(doc-0011)请求
 * Created by wunder on 16/9/1 16:50.
 */
public class LikeDiscussCaseRequest implements Serializable {

    private static final long serialVersionUID = -8175467992724361250L;

    /**
     * 用户id
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 病例讨论ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    /**
     * 操作(0：取消点赞;1：点赞)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String operator;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
