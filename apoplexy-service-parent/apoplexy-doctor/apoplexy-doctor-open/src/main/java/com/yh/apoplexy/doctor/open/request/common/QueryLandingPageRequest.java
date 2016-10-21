package com.yh.apoplexy.doctor.open.request.common;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询开机动画(common-0007)请求
 * Created by wunder on 16/9/4 11:06.
 */
public class QueryLandingPageRequest implements Serializable {

    private static final long serialVersionUID = 7305976951742809763L;

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
