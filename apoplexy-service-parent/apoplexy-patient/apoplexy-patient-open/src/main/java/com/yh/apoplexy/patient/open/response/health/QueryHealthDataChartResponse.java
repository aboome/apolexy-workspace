package com.yh.apoplexy.patient.open.response.health;

import com.yh.apoplexy.patient.open.dto.health.HealthDataItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询健康数据趋势图 (pat-0009)响应
 * Created by wunder on 16/9/1 10:47.
 */
public class QueryHealthDataChartResponse implements Serializable {

    private static final long serialVersionUID = -4758438271288670377L;

    private List<HealthDataItem> list;

    public List<HealthDataItem> getList() {
        return list;
    }

    public void setList(List<HealthDataItem> list) {
        this.list = list;
    }
}
