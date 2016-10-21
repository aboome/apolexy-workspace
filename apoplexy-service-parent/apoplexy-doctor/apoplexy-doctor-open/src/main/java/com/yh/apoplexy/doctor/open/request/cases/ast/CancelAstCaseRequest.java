package com.yh.apoplexy.doctor.open.request.cases.ast;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我发布的详情-撤销(doc-0044)请求
 * Created by wunder on 16/9/2 14:41.
 */
public class CancelAstCaseRequest implements Serializable {

    private static final long serialVersionUID = -722895275312798658L;

    private String userId;
    /**
     * AST病例ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

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
