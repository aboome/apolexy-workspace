package com.yh.apoplexy.doctor.cases.discuss.result;

import com.yjh.framework.lang.Result;

/**
 * 检查医生的病例访问权限结果类
 * Created by wunder on 16/9/7 18:56.
 */
public class DiscussCasePermissionResult extends Result {

    private static final long serialVersionUID = 768618173334326721L;

    /**
     * 病例发布医生id
     */
    private Long postDoctorId;

    /**
     * 访问医生姓名
     */
    private String doctorName;

    /**
     * 病例讨论类型
     */
    private String type;

    /**
     * 病例讨论主述
     */
    private String mainDesc;

    public Long getPostDoctorId() {
        return postDoctorId;
    }

    public void setPostDoctorId(Long postDoctorId) {
        this.postDoctorId = postDoctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMainDesc() {
        return mainDesc;
    }

    public void setMainDesc(String mainDesc) {
        this.mainDesc = mainDesc;
    }
}
