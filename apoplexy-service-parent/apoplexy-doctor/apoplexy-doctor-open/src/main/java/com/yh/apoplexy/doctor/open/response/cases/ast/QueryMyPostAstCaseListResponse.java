package com.yh.apoplexy.doctor.open.response.cases.ast;

import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCaseItem;

import java.io.Serializable;
import java.util.List;

/**
 * 我的-我发布的列表(doc-0042)响应
 * Created by wunder on 16/9/2 14:34.
 */
public class QueryMyPostAstCaseListResponse implements Serializable{

    private static final long serialVersionUID = 103316582513204471L;

    /**
     * AST病例列表
     */
    private List<AstCaseItem> list;

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

    public List<AstCaseItem> getList() {
        return list;
    }

    public void setList(List<AstCaseItem> list) {
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
