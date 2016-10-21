package com.yh.apoplexy.patient.open.response.knowledge;

import com.yh.apoplexy.patient.open.dto.knowledge.HealthKnowledgeItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询健康宣教列表 (pat-0010)响应
 * Created by wunder on 16/9/1 10:53.
 */
public class QueryHealthKnowledgeListResponse implements Serializable {

    private static final long serialVersionUID = -5778798549127095731L;

    /**
     * 健康宣教列表
     */
    private List<HealthKnowledgeItem> list;

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

    public List<HealthKnowledgeItem> getList() {
        return list;
    }

    public void setList(List<HealthKnowledgeItem> list) {
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
