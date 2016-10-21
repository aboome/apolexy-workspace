package com.yh.apoplexy.doctor.open.request.common;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * APP下载统计 (common-0011)请求
 * Created by wunder on 16/9/6 23:25.
 */
public class ReportAppDownloadRequest implements Serializable {

    private static final long serialVersionUID = 1027640539094449238L;

    /**
     * 设备ID
     */
    @StringValidator(nullable = false)
    private String iemId;

    /**
     * 类型(1：医生APP;2：患者APP)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)

    private String type;

    /**
     * 客户端类型（1：Android；2：IOS）
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String clientType;

    public String getIemId() {
        return iemId;
    }

    public void setIemId(String iemId) {
        this.iemId = iemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
