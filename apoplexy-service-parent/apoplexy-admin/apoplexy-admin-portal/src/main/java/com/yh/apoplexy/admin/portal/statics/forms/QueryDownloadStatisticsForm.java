package com.yh.apoplexy.admin.portal.statics.forms;

/**
 * Created by wunder on 16/9/27 19:15.
 */
public class QueryDownloadStatisticsForm {

    private String startDate;

    private String endDate;

    private String type;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
