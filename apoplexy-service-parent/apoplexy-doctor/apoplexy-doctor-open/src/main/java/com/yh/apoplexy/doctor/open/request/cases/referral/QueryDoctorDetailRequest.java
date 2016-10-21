package com.yh.apoplexy.doctor.open.request.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我的申请详情-查询医生详细信息(doc-0061)请求
 * Created by wunder on 16/9/2 16:29.
 */
public class QueryDoctorDetailRequest implements Serializable {

    private static final long serialVersionUID = -57366723123849531L;

    /**
     * 医生ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
