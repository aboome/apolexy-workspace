package com.yh.apoplexy.doctor.open.dto.common;

import java.io.Serializable;

/**
 * 首页推荐位实体项
 * Created by wunder on 16/9/1 08:46.
 */
public class BannerItem implements Serializable {

    private static final long serialVersionUID = -3175753687016122205L;

    /**
     * id
     */
    private String bannerId;

    /**
     * 图片id
     */
    private String imageId;

    /**
     * 标题
     */
    private String title;

    /**
     * 链接
     */
    private String url;

    /**
     * 顺序
     */
    private String sort;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
