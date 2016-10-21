package com.yh.apoplexy.assist.dmo.doctor.cases.referral;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 病例转诊关联资源表
 * Created by wunder on 16/9/9 09:57.
 */
@Table(name = "t_doctor_referral_resources")
public class ReferralResourceDmo extends Entity {

    private static final long serialVersionUID = -7406410839507267450L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

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
     * 类型(图片/视频)
     */
    @Column(name = "type")
    private String type;

    /**
     * 资源id
     */
    @Column(name = "resource_id")
    private String resourceId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
