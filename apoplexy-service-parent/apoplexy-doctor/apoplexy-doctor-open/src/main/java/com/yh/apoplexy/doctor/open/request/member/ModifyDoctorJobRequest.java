package com.yh.apoplexy.doctor.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 个人中心-修改工作岗位(doc-0055)请求
 * Created by wunder on 16/9/2 15:42.
 */
public class ModifyDoctorJobRequest implements Serializable {

    private static final long serialVersionUID = 1043790401296964250L;

    /**
     * 用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 工作岗位
     */
    @StringValidator(nullable = false, pattern = RegularConstants.SMALL_TEXT_REGULAR)
    private String post;

    /**
     * 有效期
     */
    @StringValidator(nullable = false, pattern = RegularConstants.DATE_REGULAR)
    private String effectiveTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}
