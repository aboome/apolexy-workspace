package com.yh.apoplexy.assist.dto.statistics;

import com.yjh.framework.core.entity.Entity;

public class DoctorStatisticsDto extends Entity {

	private static final long serialVersionUID = -2271381180020406815L;

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
