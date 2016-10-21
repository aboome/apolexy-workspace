package com.yh.apoplexy.doctor.open.request.cases.ast;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询AST病例评论列表(doc-0063)请求
 * Created by wunder on 16/9/11 10:13.
 */
public class QueryAstCommentListRequest implements Serializable {

    private static final long serialVersionUID = 4830672272781820657L;

    /**
     * 病例讨论ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    /**
     * 当前页数
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String pageNum;

    /**
     * 每页记录数
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String pageSize;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
