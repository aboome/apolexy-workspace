package com.yh.apoplexy.doctor.open.dto.common;

import java.io.Serializable;

/**
 * 开机动画项
 * Created by wunder on 16/9/1 08:40.
 */
public class LandingPageItem implements Serializable {

    private static final long serialVersionUID = 4529849107404536654L;

    /**
     * 开机图片查看id
     */
    private String imageId;

    /**
     * 开机图片查看顺序
     */
    private String sort;

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
