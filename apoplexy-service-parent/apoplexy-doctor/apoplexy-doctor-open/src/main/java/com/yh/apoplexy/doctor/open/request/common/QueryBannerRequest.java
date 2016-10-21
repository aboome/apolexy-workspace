package com.yh.apoplexy.doctor.open.request.common;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 首页推荐位查询(common-0008)请求
 * Created by wunder on 16/9/4 10:59.
 */
public class QueryBannerRequest implements Serializable {

    private static final long serialVersionUID = -3147243364946978942L;

    /**
     * 类型(1：医生APP；2：患者APP)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
