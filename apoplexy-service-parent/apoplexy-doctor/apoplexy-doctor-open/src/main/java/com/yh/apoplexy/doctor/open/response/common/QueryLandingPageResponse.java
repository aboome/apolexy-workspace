package com.yh.apoplexy.doctor.open.response.common;

import com.yh.apoplexy.doctor.open.dto.common.LandingPageItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询开机动画(common-0007)响应
 * Created by wunder on 16/9/1 08:39.
 */
public class QueryLandingPageResponse implements Serializable {

    private static final long serialVersionUID = -8110811121960530929L;

    private List<LandingPageItem> list;

    public List<LandingPageItem> getList() {
        return list;
    }

    public void setList(List<LandingPageItem> list) {
        this.list = list;
    }
}
