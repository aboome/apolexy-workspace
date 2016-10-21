package com.yh.apoplexy.assist.dmo.doctor.cases.ast;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * AST病例图片信息表
 * Created by wunder on 16/9/10 15:39.
 */
@Table(name = "t_doctor_ast_resources")
public class AstResourcesDmo extends Entity {

    private static final long serialVersionUID = 8187125177432481540L;

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
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 资源id
     */
    @Column(name = "image_id")
    private String imageId;

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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
