package com.yh.apoplexy.assist.dto.patient.health;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 查询健康数据实体类
 * Created by wunder on 16/9/9 17:10.
 */
public class QueryHealthDataDto extends Entity {

    private static final long serialVersionUID = -8483727428901871461L;

    private Long userId;

    private Date beginDate;

    private Date endDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
