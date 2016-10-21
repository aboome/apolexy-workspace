package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 修改开机动画表单
 * @author eyelake
 *
 */
public class AdminUpdateLandingPageForm {

    private String id;

	private String imageId;

	private String sort;

    private String owner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
