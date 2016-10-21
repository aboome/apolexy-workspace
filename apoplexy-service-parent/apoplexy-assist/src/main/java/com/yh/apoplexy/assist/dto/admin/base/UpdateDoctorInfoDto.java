package com.yh.apoplexy.assist.dto.admin.base;

import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * Created by wunder on 2016/10/15 17:19.
 */
public class UpdateDoctorInfoDto extends Entity {

    private static final long serialVersionUID = -7397437669089566888L;

    private List<DoctorInfoDmo> list;

    private String hospitalName;

    public List<DoctorInfoDmo> getList() {
        return list;
    }

    public void setList(List<DoctorInfoDmo> list) {
        this.list = list;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
