package com.yh.apoplexy.assist.dto.statistics;

import com.yjh.framework.core.entity.Entity;

/**
 * Created by wunder on 16/9/24 14:22.
 */
public class HospitalStatisticsDto extends Entity {

    private static final long serialVersionUID = -8761078387190309251L;

    private Long value;

    private String name;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
