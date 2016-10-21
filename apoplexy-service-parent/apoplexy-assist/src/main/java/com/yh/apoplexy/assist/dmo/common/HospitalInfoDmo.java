package com.yh.apoplexy.assist.dmo.common;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 医院信息表
 *
 * @author cc
 */
@Table(name = "t_hospital_info")
public class HospitalInfoDmo extends Entity {

    private static final long serialVersionUID = -5710773809010418707L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 医院名称
     */
    @Column(name = "name")
    private String hospitalName;

    /**
     * 医院地址
     */
    @Column(name = "addr")
    private String hospitalAddr;

    /**
     * 医院描述
     */
    @Column(name = "hospital_desc")
    private String hospitalDesc;

    /**
     * 图片id
     */
    @Column(name = "image_id")
    private String imageId;

    /**
     * 医院等级
     */
    @Column(name = "level")
    private String level;

    /**
     * 上级医院id
     */
    @Column(name = "parent_hospital_id")
    private Long parentHospitalId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 经度
     */
    @Column(name = "lon")
    private Double lon;

    /**
     * 纬度
     */
    @Column(name = "lat")
    private Double lat;

    /**
     * 地区编码
     */
    @Column(name = "area_code")
    private String areaCode;
    /**
     * 最上级的医院id
     */
    @Column(name = "root_hospital_id")
    private Long rootHospitalId;

    /**
     * 医院联盟
     */
    @Column(name = "hospital_union")
    private String hospitalUnion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getParentHospitalId() {
        return parentHospitalId;
    }

    public void setParentHospitalId(Long parentHospitalId) {
        this.parentHospitalId = parentHospitalId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getRootHospitalId() {
        return rootHospitalId;
    }

    public void setRootHospitalId(Long rootHospitalId) {
        this.rootHospitalId = rootHospitalId;
    }

    public String getHospitalUnion() {
        return hospitalUnion;
    }

    public void setHospitalUnion(String hospitalUnion) {
        this.hospitalUnion = hospitalUnion;
    }
}
