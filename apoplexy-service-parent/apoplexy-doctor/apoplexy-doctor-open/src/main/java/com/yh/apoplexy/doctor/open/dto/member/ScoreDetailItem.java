package com.yh.apoplexy.doctor.open.dto.member;

import java.io.Serializable;

/**
 * 积分详情实体项
 * Created by wunder on 16/9/2 15:53.
 */
public class ScoreDetailItem implements Serializable {

    private static final long serialVersionUID = 6341243324477943840L;

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
