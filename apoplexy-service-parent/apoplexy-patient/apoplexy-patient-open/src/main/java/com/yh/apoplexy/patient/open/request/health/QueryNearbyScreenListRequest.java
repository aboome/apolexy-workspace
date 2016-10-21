package com.yh.apoplexy.patient.open.request.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询附近的筛查点列表 (pat-0006)请求
 * Created by wunder on 16/9/1 10:20.
 */
public class QueryNearbyScreenListRequest implements Serializable {

    private static final long serialVersionUID = -2949576161207293383L;

    /**
     * 用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 用户所在位置经度
     */
    @StringValidator(nullable = false, pattern = RegularConstants.DOUBLE_REGULAR)
    private String lat;

    /**
     * 用户所在位置纬度
     */
    @StringValidator(nullable = false, pattern = RegularConstants.DOUBLE_REGULAR)
    private String lon;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
