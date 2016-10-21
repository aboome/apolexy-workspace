package com.yh.apoplexy.patient.open.request.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询医院详情(pat-0014)请求
 * Created by wunder on 16/9/13 10:53.
 */
public class QueryNearbyHospitalDetailRequest implements Serializable {

    private static final long serialVersionUID = 5043491276029079369L;

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
