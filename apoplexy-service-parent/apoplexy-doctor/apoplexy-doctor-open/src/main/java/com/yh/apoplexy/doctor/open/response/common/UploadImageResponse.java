package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * 图片上传(common-0003)响应
 * Created by wunder on 16/8/31 21:04.
 */
public class UploadImageResponse implements Serializable {

    private static final long serialVersionUID = -5012683658953033655L;

    /**
     * 0000：图片上传成功
     * 0001：图片上传失败
     */
    private String resultcode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 图片上传后的id
     */
    private String imageId;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
