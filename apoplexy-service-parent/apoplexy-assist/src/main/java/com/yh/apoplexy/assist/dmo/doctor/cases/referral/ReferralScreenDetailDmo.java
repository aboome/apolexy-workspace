package com.yh.apoplexy.assist.dmo.doctor.cases.referral;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 病例转诊高危筛查明细表
 * Created by wunder on 16/9/22 11:23.
 */
@Table(name = "t_doctor_screen_detail")
public class ReferralScreenDetailDmo extends Entity {

    private static final long serialVersionUID = -8261060920944079555L;

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
    @Column(name = "detail_index")
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
