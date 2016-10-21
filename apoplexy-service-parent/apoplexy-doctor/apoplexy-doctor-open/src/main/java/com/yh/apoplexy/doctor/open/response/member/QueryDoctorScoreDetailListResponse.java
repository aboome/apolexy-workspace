package com.yh.apoplexy.doctor.open.response.member;

import com.yh.apoplexy.doctor.open.dto.member.ScoreDetailItem;

import java.io.Serializable;
import java.util.List;

/**
 * 个人中心-积分明细列表(doc-0057)响应
 * Created by wunder on 16/9/2 15:52.
 */
public class QueryDoctorScoreDetailListResponse implements Serializable {

    private static final long serialVersionUID = 1272644404114888639L;

    /**
     * 积分详情列表
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
