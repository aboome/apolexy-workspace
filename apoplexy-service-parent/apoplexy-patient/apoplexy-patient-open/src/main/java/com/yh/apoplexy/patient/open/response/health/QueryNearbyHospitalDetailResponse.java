package com.yh.apoplexy.patient.open.response.health;

import java.io.Serializable;

/**
 * 查询医院详情(pat-0014)响应
 * Created by wunder on 16/9/13 11:26.
 */
public class QueryNearbyHospitalDetailResponse implements Serializable {

    private static final long serialVersionUID = 5626030499416503722L;

    /**
     * 医院ID
     */
    private String hospitalId;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 图片ID
     */
    private String imageId;

    /**
     * 医院地址
     */
    private String addr;

    /**
     * 医院描述
     */
    private String desc;

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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
