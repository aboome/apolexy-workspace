package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 查询医院列表信息表单
 * @author eyelake
 *
 */
public class AdminHospitalForm {

	private String id;

	private String hospitalName;

	private String pageNum;

	private String pageSize;

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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}
