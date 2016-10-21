package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 新增开机动画表单
 * @author eyelake
 *
 */
public class AdminAddLandingPageForm {

	 private String owner;
	 
	 private String imageId;
	 
	 private String sort;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	 
	 
}
