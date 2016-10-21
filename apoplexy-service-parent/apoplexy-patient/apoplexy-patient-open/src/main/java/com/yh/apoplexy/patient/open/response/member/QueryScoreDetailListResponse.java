package com.yh.apoplexy.patient.open.response.member;

import com.yh.apoplexy.patient.open.dto.member.ScoreDetailItem;

import java.io.Serializable;
import java.util.List;

/**
 * 个人中心-查询积分明细列表(pat-0018)响应
 * Created by wunder on 16/9/1 14:02.
 */
public class QueryScoreDetailListResponse implements Serializable {

    private static final long serialVersionUID = -5617431448182934107L;

    /**
     * 积分明细列表
     */
    private List<ScoreDetailItem> list;

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

    public List<ScoreDetailItem> getList() {
        return list;
    }

    public void setList(List<ScoreDetailItem> list) {
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
