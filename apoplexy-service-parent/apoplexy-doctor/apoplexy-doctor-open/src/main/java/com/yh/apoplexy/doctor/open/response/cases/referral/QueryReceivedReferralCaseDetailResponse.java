package com.yh.apoplexy.doctor.open.response.cases.referral;

import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCaseItem;

import java.io.Serializable;

/**
 * 我的-已被接诊详情(doc-0029)响应
 * Created by wunder on 16/9/1 20:21.
 */
public class QueryReceivedReferralCaseDetailResponse extends ReferralCaseItem {

    private static final long serialVersionUID = -3442629927370337335L;

    /**
     * 是否接诊
     */
    private String hasReceived;

    /**
     * 是否评价
     */
    private String hasScored;

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
