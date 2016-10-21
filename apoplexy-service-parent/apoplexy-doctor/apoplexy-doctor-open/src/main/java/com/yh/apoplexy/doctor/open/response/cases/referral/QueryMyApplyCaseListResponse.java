package com.yh.apoplexy.doctor.open.response.cases.referral;

import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCaseItem;

import java.io.Serializable;
import java.util.List;

/**
 * 我的-我的申请列表(doc-0023)响应
 * Created by wunder on 16/9/1 20:02.
 */
public class QueryMyApplyCaseListResponse implements Serializable {

    private static final long serialVersionUID = 5638907184045831948L;

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
