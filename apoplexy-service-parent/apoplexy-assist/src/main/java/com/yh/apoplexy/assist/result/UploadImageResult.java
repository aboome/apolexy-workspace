package com.yh.apoplexy.assist.result;

import com.yjh.framework.lang.Result;

/**
 * Created by wunder on 2016/10/11 21:06.
 */
public class UploadImageResult extends Result {

    private static final long serialVersionUID = -409661301122820762L;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
