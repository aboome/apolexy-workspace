package com.yh.apoplexy.doctor.open.request.common;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * APP版本升级接口 (common-0010)请求
 * Created by wunder on 16/9/3 13:16.
 */
public class AppUpdateRequest implements Serializable {

    private static final long serialVersionUID = 9137114720587296262L;

    /**
     * APP当前版本号
     */
    @StringValidator(nullable = false)
    private String version;

    /**
     * 类型(1：医生APP;2：患者APP)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String type;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
