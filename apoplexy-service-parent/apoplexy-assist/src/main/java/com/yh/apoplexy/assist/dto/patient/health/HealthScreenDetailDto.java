package com.yh.apoplexy.assist.dto.patient.health;

import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * 高危筛查详情实体类
 * Created by wunder on 16/9/5 14:53.
 */
public class HealthScreenDetailDto extends Entity {

    private static final long serialVersionUID = -5070319076585297960L;

    private Long recordId;

    private List<PatientScreenDetailDmo> list;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public List<PatientScreenDetailDmo> getList() {
        return list;
    }

    public void setList(List<PatientScreenDetailDmo> list) {
        this.list = list;
    }
}
