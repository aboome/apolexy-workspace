package com.yh.apoplexy.assist.dto.admin.base;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yjh.framework.core.entity.Entity;

public class AdminImportDoctorListDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3296551183011431378L;
	
	private List<DoctorInfoDmo> doctorList;

	public List<DoctorInfoDmo> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<DoctorInfoDmo> doctorList) {
		this.doctorList = doctorList;
	}

    
	

}
