package com.yh.apoplexy.doctor.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-积分详情(doc-0056)响应
 * Created by wunder on 16/9/2 15:48.
 */
public class QueryDoctorScoreInfoResponse implements Serializable {

    private static final long serialVersionUID = -6453528791307671584L;

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
