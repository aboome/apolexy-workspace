package com.yh.apoplexy.doctor.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-查询工作岗位信息(doc-0054)响应
 * Created by wunder on 16/9/2 15:40.
 */
public class QueryDoctorJobResponse implements Serializable {

    private static final long serialVersionUID = -2434115161166660193L;

    /**
     * 工作岗位
     */
    private String post;

    /**
     * 有效期
     */
    private String effectiveTime;

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
