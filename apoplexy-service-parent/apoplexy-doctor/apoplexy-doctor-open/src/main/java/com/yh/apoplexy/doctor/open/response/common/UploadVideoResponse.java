package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * 视频上传(common-0005)响应
 * Created by wunder on 16/9/1 08:33.
 */
public class UploadVideoResponse implements Serializable {

    private static final long serialVersionUID = 5211580900200517568L;

    /**
     * 0000：视频上传成功
     * 0001：视频上传失败
     */
    private String resultcode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 视频上传后的id
     */
    private String videoId;

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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
