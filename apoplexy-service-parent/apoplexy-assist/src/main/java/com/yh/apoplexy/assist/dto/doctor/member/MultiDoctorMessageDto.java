package com.yh.apoplexy.assist.dto.doctor.member;


import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;

import java.util.List;

/**
 * Created by wunder on 2016/10/12 17:15.
 */
public class MultiDoctorMessageDto {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private String type;

    /**
     * 用户列表
     */
    private List<DoctorMemberDmo> doctorMemberDmoList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DoctorMemberDmo> getDoctorMemberDmoList() {
        return doctorMemberDmoList;
    }

    public void setDoctorMemberDmoList(List<DoctorMemberDmo> doctorMemberDmoList) {
        this.doctorMemberDmoList = doctorMemberDmoList;
    }
}
