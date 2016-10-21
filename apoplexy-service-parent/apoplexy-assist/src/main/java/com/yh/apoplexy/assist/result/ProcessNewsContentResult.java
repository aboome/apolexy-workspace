package com.yh.apoplexy.assist.result;

import com.yjh.framework.lang.Result;

/**
 * Created by wunder on 2016/10/11 21:09.
 */
public class ProcessNewsContentResult extends Result {

    private static final long serialVersionUID = 1798393746626388960L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
