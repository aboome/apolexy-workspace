package com.yh.apoplexy.doctor.open.dto.cases.discuss;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 图片详情项
 * Created by wunder on 16/9/1 15:19.
 */
public class ImageItem implements Serializable {

    private static final long serialVersionUID = -7236669674627308921L;

    @StringValidator(nullable = false, pattern = RegularConstants.UUID_REGULAR)
    private String imageId;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
