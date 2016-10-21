package com.yh.apoplexy.doctor.open.response.information;

import com.yh.apoplexy.doctor.open.dto.information.InformationItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询最新资讯列表(doc-0051)响应
 * Created by wunder on 16/9/2 15:10.
 */
public class QueryInformationListResponse implements Serializable {

    private static final long serialVersionUID = 1538308983127651674L;

    /**
     * 学术信息列表
     */
    private List<InformationItem> list;

    /**
     * 总记录数
     */
    private String total;

    /**
     * 当前页
     */
    private String curPage;

    /**
     * 每页记录数
     */
    private String pageSize;

    public List<InformationItem> getList() {
        return list;
    }

    public void setList(List<InformationItem> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
