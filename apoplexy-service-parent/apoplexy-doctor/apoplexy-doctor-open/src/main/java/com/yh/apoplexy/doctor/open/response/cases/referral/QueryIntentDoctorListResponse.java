package com.yh.apoplexy.doctor.open.response.cases.referral;

import com.yh.apoplexy.doctor.open.dto.cases.referral.ReceiveDoctorItem;

import java.io.Serializable;
import java.util.List;

/**
 * 我的-我的申请详情-查询意向接诊医生列表(doc-0025)响应
 * Created by wunder on 16/9/2 16:23.
 */
public class QueryIntentDoctorListResponse implements Serializable {

    private static final long serialVersionUID = 5158713251946657448L;

    private List<ReceiveDoctorItem> list;

    public List<ReceiveDoctorItem> getList() {
        return list;
    }

    public void setList(List<ReceiveDoctorItem> list) {
        this.list = list;
    }
}
