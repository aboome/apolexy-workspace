package com.yh.apoplexy.doctor.open.request.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我的申请详情-查询意向接诊医生列表(doc-0025)请求
 * Created by wunder on 16/9/2 16:22.
 */
public class QueryIntentDoctorListRequest implements Serializable {

    private static final long serialVersionUID = 1280263923258966845L;

    /**
     * 病例转诊ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
