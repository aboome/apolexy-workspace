package com.yh.apoplexy.admin.portal.common.controller.dto;

/**
 * 查询患者宣教列表
 * @author eyelake
 *
 */
public class QueryPatientNewsForm {
      
	private String title;
	
	private String type;
	
	private String pageNum;
	
	private String  pageSize;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
