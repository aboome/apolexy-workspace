package com.yh.apoplexy.patient.open.dto.member;

import java.io.Serializable;

/**
 *
 * Created by wunder on 16/9/1 14:03.
 */
public class ScoreDetailItem implements Serializable {

    private static final long serialVersionUID = -3551641025023235401L;

    /**
     * 积分明细
     */
    private String recordId;

    /**
     * 积分日期YYYYMMDDhi24mmss
     */
    private String recordDate;

    /**
     * 积分数值
     */
    private String recordScore;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordScore() {
        return recordScore;
    }

    public void setRecordScore(String recordScore) {
        this.recordScore = recordScore;
    }
}
