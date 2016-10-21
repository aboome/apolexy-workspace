package com.yh.apoplexy.patient.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-查询积分信息(pat-0017)响应
 * Created by wunder on 16/9/1 13:59.
 */
public class QueryScoreInfoResponse implements Serializable {

    private static final long serialVersionUID = -8992182822566114980L;

    /**
     * 总积分
     */
    private String totalScore;

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
}
