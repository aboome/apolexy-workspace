package com.yh.apoplexy.patient.open.request.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询筛查点详情 (pat-0007)请求
 * Created by wunder on 16/9/1 10:32.
 */
public class QueryNearbyScreenDetailRequest implements Serializable {

    private static final long serialVersionUID = 8412561391615107740L;

    /**
     * 医院id
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String hospitalId;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
