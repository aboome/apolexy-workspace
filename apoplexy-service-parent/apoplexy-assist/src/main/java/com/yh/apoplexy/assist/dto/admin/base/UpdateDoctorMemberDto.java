package com.yh.apoplexy.assist.dto.admin.base;

import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * Created by wunder on 2016/10/15 17:23.
 */
public class UpdateDoctorMemberDto extends Entity {

    private static final long serialVersionUID = -549646498548560988L;

    private String hospitalName;

    private List<DoctorMemberDmo> list;

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public List<DoctorMemberDmo> getList() {
        return list;
    }

    public void setList(List<DoctorMemberDmo> list) {
        this.list = list;
    }
}
