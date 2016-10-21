package com.yh.apoplexy.doctor.open.response.member;

import com.yh.apoplexy.doctor.open.dto.common.MessageDetailItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询通知消息列表(doc-0059)响应
 * Created by wunder on 16/9/2 16:05.
 */
public class QueryMessageListResponse implements Serializable {

    private static final long serialVersionUID = -5120791523513580125L;

    /**
     * 通知消息列表
     */
    private List<MessageDetailItem> list;

    /**
     * 总记录数
     */
    private String total;

    /**
     * 当期页数
     */
    private String pageNum;

    /**
     * 每页记录数
     */
    private String pageSize;

    public List<MessageDetailItem> getList() {
        return list;
    }

    public void setList(List<MessageDetailItem> list) {
        this.list = list;
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
