package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 查询医生列表信息表单
 * @author eyelake
 *
 */
public class AdminDoctorForm {
	
	private String id;
	
	private String doctorName;
	
	private String hospital;
    
	private String pageNum;
	
	private String pageSize;
	
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

   
	

}
