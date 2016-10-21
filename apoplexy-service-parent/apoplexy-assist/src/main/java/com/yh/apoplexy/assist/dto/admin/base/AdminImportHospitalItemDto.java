package com.yh.apoplexy.assist.dto.admin.base;

import com.yjh.framework.core.entity.Entity;

public class AdminImportHospitalItemDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3468650524025037159L;
      /**
       * 医院名称 
       */
	private String hspitalName;
	
	/**
	 * 医院描述
	 */
	private String hospitalDesc;
	
	/**
	 *医院地址 
	 */
	private String hospitalAddr;
	
	/**
	 * 图片id
	 */
	private String imageId;

	public String getHspitalName() {
		return hspitalName;
	}

	public void setHspitalName(String hspitalName) {
		this.hspitalName = hspitalName;
	}

	public String getHospitalDesc() {
		return hospitalDesc;
	}

	public void setHospitalDesc(String hospitalDesc) {
		this.hospitalDesc = hospitalDesc;
	}

	public String getHospitalAddr() {
		return hospitalAddr;
	}

	public void setHospitalAddr(String hospitalAddr) {
		this.hospitalAddr = hospitalAddr;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	
}
