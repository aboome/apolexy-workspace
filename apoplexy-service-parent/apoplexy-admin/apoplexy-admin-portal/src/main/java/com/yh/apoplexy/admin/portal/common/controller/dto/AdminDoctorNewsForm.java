package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 查看医生查询学术更新/最新资讯列表
 * @author eyelake
 *
 */
public class AdminDoctorNewsForm {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 新闻类型
	 */
	private String newType;
	/**
	 * 内容类型
	 */
	private String type;
	
	private String pageNum;

	private String pageSize;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
