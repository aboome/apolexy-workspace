package com.yh.apoplexy.assist.dto.patient.health;

import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * 查询医院列表实体类
 * Created by wunder on 16/9/13 11:36.
 */
public class QueryHospitalListDto extends Entity {

    private static final long serialVersionUID = -5729062722121526068L;

    private String lat;

    private String lon;

    private String number;

    private List<String> hospitalLevelList;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<String> getHospitalLevelList() {
        return hospitalLevelList;
    }

    public void setHospitalLevelList(List<String> hospitalLevelList) {
        this.hospitalLevelList = hospitalLevelList;
    }
}
