package com.yh.apoplexy.assist.dto.admin.base;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yjh.framework.core.entity.Entity;

public class AdminImportHospitalListDto extends Entity {

	private static final long serialVersionUID = -2385981970427365284L;
	
	private List<HospitalInfoDmo> hospitalList;

	/**
	 * @return the hospitalList
	 */
	public List<HospitalInfoDmo> getHospitalList() {
		return hospitalList;
	}

	/**
	 * @param hospitalList the hospitalList to set
	 */
	public void setHospitalList(List<HospitalInfoDmo> hospitalList) {
		this.hospitalList = hospitalList;
	}

}
