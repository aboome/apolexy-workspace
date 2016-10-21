package com.yh.apoplexy.assist.dto.statistics;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * Created by wunder on 16/9/27 19:04.
 */
public class QueryDownloadStatisticsDto extends Entity {

    private static final long serialVersionUID = 1284535294378766796L;

    private Date startDate;

    private Date endDate;

    private String type;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
