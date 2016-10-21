package com.yh.apoplexy.assist.service.intf;

import com.yh.apoplexy.assist.result.UploadImageResult;

/**
 * 图片上传服务
 * Created by wunder on 2016/10/11 21:01.
 */
public interface ImageUploadService {

    /**
     * 上传图片
     * @param href
     * @param fileName
     * @return
     */
    public UploadImageResult imageUpload(String href, String fileName);



}
