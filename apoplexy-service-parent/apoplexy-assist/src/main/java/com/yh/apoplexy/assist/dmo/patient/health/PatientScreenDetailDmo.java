package com.yh.apoplexy.assist.dmo.patient.health;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 高危筛查明细表
 * Created by wunder on 16/9/5 11:06.
 */
@Table(name = "t_patient_screen_detail")
public class PatientScreenDetailDmo extends Entity {

    private static final long serialVersionUID = -5359980675527531083L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 记录id
     */
    @Column(name = "record_id")
    private Long recordId;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 序号
     */
    @Column(name = "index")
    private Long detailIndex;

    /**
     * 结果
     */
    @Column(name = "result")
    private String result;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDetailIndex() {
        return detailIndex;
    }

    public void setDetailIndex(Long detailIndex) {
        this.detailIndex = detailIndex;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}