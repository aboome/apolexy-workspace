package com.yh.apoplexy.doctor.open.response.cases.referral;

import com.yh.apoplexy.doctor.open.dto.cases.referral.ReceiveDoctorItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCaseItem;

import java.io.Serializable;
import java.util.List;

/**
 * 我的-我的申请详情(doc-0024)响应
 * Created by wunder on 16/9/1 20:06.
 */
public class QueryMyApplyCaseDetailResponse extends ReferralCaseItem {

    private static final long serialVersionUID = -4251702304585559100L;

    private List<ReceiveDoctorItem> doctorList;

    /**
     * 是否接诊
     */
    private String hasReceived;

    /**
     * 是否评价
     */
    private String hasScored;

    public List<ReceiveDoctorItem> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<ReceiveDoctorItem> doctorList) {
        this.doctorList = doctorList;
    }

    public String getHasReceived() {
        return hasReceived;
    }

    public void setHasReceived(String hasReceived) {
        this.hasReceived = hasReceived;
    }

    public String getHasScored() {
        return hasScored;
    }

    public void setHasScored(String hasScored) {
        this.hasScored = hasScored;
    }
}
