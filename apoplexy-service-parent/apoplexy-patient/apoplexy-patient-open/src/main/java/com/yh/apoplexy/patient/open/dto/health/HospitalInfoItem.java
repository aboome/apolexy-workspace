package com.yh.apoplexy.patient.open.dto.health;

import java.io.Serializable;

/**
 * 医院信息实体项
 * Created by wunder on 16/9/1 10:25.
 */
public class HospitalInfoItem implements Serializable {

    private static final long serialVersionUID = 2130467544349749188L;

    /**
     * 医院ID
     */
    private String hospitalId;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 距离
     */
    private String distance;

    /**
     * 医院经度
     */
    private String lat;

    /**
     * 医院纬度
     */
    private String lon;

    /**
     * 医院级别
     */
    private String hospitalLevel;

    /**
     * 是否医院联盟(1：是;0：否)
     */
    private String hospitalUnion;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

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

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getHospitalUnion() {
        return hospitalUnion;
    }

    public void setHospitalUnion(String hospitalUnion) {
        this.hospitalUnion = hospitalUnion;
    }
}
