package com.yh.apoplexy.doctor.open.request.cases.discuss;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我参与的列表(doc-0015)请求
 * Created by wunder on 16/9/1 18:36.
 */
public class QueryMyPartakeCaseListRequest implements Serializable {

    private static final long serialVersionUID = 8436431474854345582L;

    /**
     * 用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 当前页
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String curPage;

    /**
     * 每页记录数
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
