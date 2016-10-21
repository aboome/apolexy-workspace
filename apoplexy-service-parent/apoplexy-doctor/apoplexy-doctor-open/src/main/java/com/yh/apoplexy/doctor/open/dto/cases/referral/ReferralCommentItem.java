package com.yh.apoplexy.doctor.open.dto.cases.referral;

import java.io.Serializable;

/**
 * 转诊评价实体项
 * Created by wunder on 16/9/2 16:39.
 */
public class ReferralCommentItem implements Serializable {

    private static final long serialVersionUID = -1204212671786286064L;

    /**
     * 评价星级
     */
    private String star;

    /**
     * 次数
     */
    private String count;

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
