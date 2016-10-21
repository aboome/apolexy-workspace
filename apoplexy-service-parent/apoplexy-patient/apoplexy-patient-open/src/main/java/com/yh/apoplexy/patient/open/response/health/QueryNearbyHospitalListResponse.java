package com.yh.apoplexy.patient.open.response.health;

import com.yh.apoplexy.patient.open.dto.health.HospitalInfoItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询附近的医院列表 (pat-0013)响应
 * Created by wunder on 16/9/13 11:27.
 */
public class QueryNearbyHospitalListResponse implements Serializable {

    private static final long serialVersionUID = -3496233741421813621L;

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
