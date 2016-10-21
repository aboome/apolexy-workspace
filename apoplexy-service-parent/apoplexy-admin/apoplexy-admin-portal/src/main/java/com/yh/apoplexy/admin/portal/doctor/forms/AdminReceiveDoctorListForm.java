package com.yh.apoplexy.admin.portal.doctor.forms;


/**
 * 
 * 查询接诊医生列表
 * @author zhangbiao
 *
 */
public class AdminReceiveDoctorListForm {
   
	private String recordId;
	
	private String pageNum;
	
	private String pageSize;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
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
