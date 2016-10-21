package com.yh.apoplexy.doctor.open.response.cases.discuss;

import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussDetailItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询病例讨论详情(doc-0007)响应
 * Created by wunder on 16/9/1 15:42.
 */
public class QueryDiscussCaseDetailResponse extends DiscussCaseItem {

    private static final long serialVersionUID = -8266143288786330229L;

    private List<DiscussDetailItem> discussList;

    public List<DiscussDetailItem> getDiscussList() {
        return discussList;
    }

    public void setDiscussList(List<DiscussDetailItem> discussList) {
        this.discussList = discussList;
    }
}
