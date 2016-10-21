package com.yh.apoplexy.admin.portal.system.form;
/**
 * 查询菜单列表表单
 * @author eyelake
 *
 */
public class AdminQueryMenuForm {
	private String pageSize;
	private String pageNum;

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

}
