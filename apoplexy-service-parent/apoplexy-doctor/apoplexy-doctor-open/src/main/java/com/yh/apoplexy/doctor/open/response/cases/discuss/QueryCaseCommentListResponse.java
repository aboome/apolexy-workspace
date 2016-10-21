package com.yh.apoplexy.doctor.open.response.cases.discuss;

import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussDetailItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询病例讨论评论列表(doc-0062)响应
 * Created by wunder on 16/9/10 19:13.
 */
public class QueryCaseCommentListResponse implements Serializable {

    private static final long serialVersionUID = -7265116573910505598L;

    /**
     * 评论列表
     */
    private List<DiscussDetailItem> discussList;

    /**
     * 总记录数
     */
    private String total;

    /**
     * 当前页数
     */
    private String pageNum;

    /**
     * 每页记录数
     */
    private String pageSize;

    public List<DiscussDetailItem> getDiscussList() {
        return discussList;
    }

    public void setDiscussList(List<DiscussDetailItem> discussList) {
        this.discussList = discussList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
