package com.yh.apoplexy.doctor.open.request.common;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 注册协议接口 (common-0013)请求实体
 * Created by wunder on 2016/10/9 19:33.
 */
public class QueryProtocolRequest implements Serializable {

    private static final long serialVersionUID = 7559001316070829274L;

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
