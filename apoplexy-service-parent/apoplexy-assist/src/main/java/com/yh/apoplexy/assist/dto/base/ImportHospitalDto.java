/**
 * 
 */
package com.yh.apoplexy.assist.dto.base;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yjh.framework.core.entity.Entity;

/**
 * 批量导入医院信息
 * 
 * @author CC
 *
 */
public class ImportHospitalDto extends Entity {

	private static final long serialVersionUID = -4952452498386393576L;

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
