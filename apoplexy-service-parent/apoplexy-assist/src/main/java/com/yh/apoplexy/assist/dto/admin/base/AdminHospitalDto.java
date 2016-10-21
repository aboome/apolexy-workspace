package com.yh.apoplexy.assist.dto.admin.base;

public class AdminHospitalDto {

    private String id;

    private String hospitalName;

    private String hospitalAddr;

    private String hospitalDesc;

    private String parentHospitalId;

    private String parentHospitalName;

    private String imageId;

    private String level;

    private String lon;

    private String lat;

    private String areaCode;

    private String hospitalUnion;

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddr() {
        return hospitalAddr;
    }

    public void setHospitalAddr(String hospitalAddr) {
        this.hospitalAddr = hospitalAddr;
    }

    public String getHospitalDesc() {
        return hospitalDesc;
    }

    public void setHospitalDesc(String hospitalDesc) {
        this.hospitalDesc = hospitalDesc;
    }

    public String getParentHospitalId() {
        return parentHospitalId;
    }

    public void setParentHospitalId(String parentHospitalId) {
        this.parentHospitalId = parentHospitalId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getHospitalUnion() {
        return hospitalUnion;
    }

    public void setHospitalUnion(String hospitalUnion) {
        this.hospitalUnion = hospitalUnion;
    }

    public String getParentHospitalName() {
        return parentHospitalName;
    }

    public void setParentHospitalName(String parentHospitalName) {
        this.parentHospitalName = parentHospitalName;
    }
}
