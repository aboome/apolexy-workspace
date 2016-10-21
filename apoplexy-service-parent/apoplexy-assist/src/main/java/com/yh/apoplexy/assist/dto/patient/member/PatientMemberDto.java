package com.yh.apoplexy.assist.dto.patient.member;

import com.yjh.framework.core.entity.Entity;

public class PatientMemberDto extends Entity {
	
	private static final long serialVersionUID = 5165023638657125598L;

	private String name;
    
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
