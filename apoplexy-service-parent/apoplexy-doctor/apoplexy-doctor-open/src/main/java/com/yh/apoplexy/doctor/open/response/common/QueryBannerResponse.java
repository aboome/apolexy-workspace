package com.yh.apoplexy.doctor.open.response.common;

import com.yh.apoplexy.doctor.open.dto.common.BannerItem;

import java.io.Serializable;
import java.util.List;

/**
 * 首页推荐位查询(common-0008)响应
 * Created by wunder on 16/9/1 08:45.
 */
public class QueryBannerResponse implements Serializable {

    private static final long serialVersionUID = 2106804959308152183L;

    private List<BannerItem> list;

    public List<BannerItem> getList() {
        return list;
    }

    public void setList(List<BannerItem> list) {
        this.list = list;
    }
}
