package com.yh.apoplexy.assist.dto.doctor.cases.referral;

import com.yjh.framework.core.entity.Entity;

/**
 * 计算医生星级实体类
 * Created by wunder on 16/9/12 08:34.
 */
public class CalStarLevelDto extends Entity {

    private static final long serialVersionUID = -1966213215274599156L;

    private Long scoreCount;

    private Long scoreSum;

    public Long getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(Long scoreCount) {
        this.scoreCount = scoreCount;
    }

    public Long getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(Long scoreSum) {
        this.scoreSum = scoreSum;
    }
}
