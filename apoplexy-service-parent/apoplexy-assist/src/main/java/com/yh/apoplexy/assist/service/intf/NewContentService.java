package com.yh.apoplexy.assist.service.intf;

import com.yh.apoplexy.assist.result.ProcessNewsContentResult;

/**
 * Created by wunder on 2016/10/11 21:08.
 */
public interface NewContentService {

    /**
     * 处理视频链接
     *
     * @param content
     * @return
     */
    public ProcessNewsContentResult processVideo(String content);

    /**
     * 处理图片链接
     *
     * @param content
     * @return
     */
    public ProcessNewsContentResult processImage(String content);

    /**
     * 恢复图片链接
     *
     * @param content
     * @param baseUrl
     * @return
     */
    public ProcessNewsContentResult recoveryImage(String content, String baseUrl);
}
