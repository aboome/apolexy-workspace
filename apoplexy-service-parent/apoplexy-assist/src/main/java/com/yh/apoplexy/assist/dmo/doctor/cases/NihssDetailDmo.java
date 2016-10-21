package com.yh.apoplexy.assist.dmo.doctor.cases;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * NIHSS评测明细表
 * Created by wunder on 16/9/9 10:13.
 */
@Table(name = "t_doctor_nihss_detail")
public class NihssDetailDmo extends Entity {

    private static final long serialVersionUID = -809204696797431020L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 记录id
     */
    @Column(name = "record_id")
    private Long recordId;

    /**
     * NIHSS题目序号
     */
    @Column(name = "index")
    private Long detailIndex;

    /**
     * 得分
     */
    @Column(name = "result")
    private Long result;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getDetailIndex() {
        return detailIndex;
    }

    public void setDetailIndex(Long detailIndex) {
        this.detailIndex = detailIndex;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
