package com.yh.apoplexy.doctor.open.request.cases.discuss;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我发布的详情-删除病例讨论(doc-0014)请求
 * Created by wunder on 16/9/1 17:08.
 */
public class DeleteDiscussCaseRequest implements Serializable {

    private static final long serialVersionUID = 8405213094544016544L;

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
}
