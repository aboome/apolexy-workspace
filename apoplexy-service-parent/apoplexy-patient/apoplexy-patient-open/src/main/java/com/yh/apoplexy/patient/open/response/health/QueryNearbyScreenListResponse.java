package com.yh.apoplexy.patient.open.response.health;

import com.yh.apoplexy.patient.open.dto.health.HospitalInfoItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询附近的筛查点列表 (pat-0006)响应
 * 查询附近的医院列表 (pat-0013)响应
 * Created by wunder on 16/9/1 10:24.
 */
public class QueryNearbyScreenListResponse implements Serializable {

    private static final long serialVersionUID = 7453624896475766472L;

    /**
     * 医院列表
     */
    private List<HospitalInfoItem> list;

    public List<HospitalInfoItem> getList() {
        return list;
    }

    public void setList(List<HospitalInfoItem> list) {
        this.list = list;
    }
}
