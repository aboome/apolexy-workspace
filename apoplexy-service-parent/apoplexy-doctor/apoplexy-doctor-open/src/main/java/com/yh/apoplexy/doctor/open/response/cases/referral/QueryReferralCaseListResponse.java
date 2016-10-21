package com.yh.apoplexy.doctor.open.response.cases.referral;

import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCaseItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询病例转诊列表(doc-0019)响应
 * Created by wunder on 16/9/1 18:57.
 */
public class QueryReferralCaseListResponse implements Serializable {

    private static final long serialVersionUID = 4045537213486138389L;

    /**
     * 转诊病例列表
     */
    private List<ReferralCaseItem> list;

    /**
     * 总记录数
     */
    private String totalCount;

    /**
     * 当前页
     */
    private String curPage;

    /**
     * 每页记录数
     */
    private String pageSize;

    public List<ReferralCaseItem> getList() {
        return list;
    }

    public void setList(List<ReferralCaseItem> list) {
        this.list = list;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
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
