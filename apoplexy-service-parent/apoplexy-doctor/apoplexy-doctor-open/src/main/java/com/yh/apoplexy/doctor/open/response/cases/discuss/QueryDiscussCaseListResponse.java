package com.yh.apoplexy.doctor.open.response.cases.discuss;

import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussCaseItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询病例讨论列表(doc-0005)响应
 * Created by wunder on 16/9/1 15:12.
 */
public class QueryDiscussCaseListResponse implements Serializable {

    private static final long serialVersionUID = 7180907513628257170L;

    /**
     * 病例讨论列表
     */
    private List<DiscussCaseItem> list;

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

    public List<DiscussCaseItem> getList() {
        return list;
    }

    public void setList(List<DiscussCaseItem> list) {
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
