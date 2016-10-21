package com.yh.apoplexy.assist.dmo.doctor.cases.ast;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * AST病例既往史明细表
 * Created by wunder on 16/9/10 15:33.
 */
@Table(name = "t_doctor_ast_history")
public class AstHistoryDmo extends Entity {

    private static final long serialVersionUID = -1906338349097148867L;

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
     * 题目序号
     */
    @Column(name = "index")
    private Long detailIndex;

    /**
     * 结果
     */
    @Column(name = "result")
    private String result;

    /**
     * 描述一
     */
    @Column(name = "desc_one")
    private String descOne;

    /**
     * 描述二
     */
    @Column(name = "desc_two")
    private String descTwo;

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

    public String getDescOne() {
        return descOne;
    }

    public void setDescOne(String descOne) {
        this.descOne = descOne;
    }

    public String getDescTwo() {
        return descTwo;
    }

    public void setDescTwo(String descTwo) {
        this.descTwo = descTwo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
