package com.yh.apoplexy.doctor.open.request.information;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询最新资讯列表(doc-0051)请求
 * Created by wunder on 16/9/2 14:57.
 */
public class QueryInformationListRequest implements Serializable {

    private static final long serialVersionUID = -4467478506159139512L;

    /**
     * 当前页数
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String curPage;

    /**
     * 每页显示数据条数
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String pageSize;

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
