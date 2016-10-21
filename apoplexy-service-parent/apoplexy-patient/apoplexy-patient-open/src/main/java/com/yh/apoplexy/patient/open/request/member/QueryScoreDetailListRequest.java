package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 个人中心-查询积分明细列表(pat-0018)请求
 * Created by wunder on 16/9/1 14:01.
 */
public class QueryScoreDetailListRequest implements Serializable {

    private static final long serialVersionUID = 4040131701892719221L;

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
