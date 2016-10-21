package com.yh.apoplexy.assist.dmo.common;


import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 验证码表
 * Created by wunder on 16/9/3 16:11.
 */
@Table(name = "t_validatecode")
public class ValidateCodeDmo extends Entity{

    private static final long serialVersionUID = -3045274147995118387L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 验证码
     */
    @Column(name = "validate_code")
    private String validateCode;

    /**
     * 状态，00：待验证；10：验证成功；20：失效
     */
    @Column(name = "status")
    private String status;

    /**
     * 验证次数
     */
    @Column(name = "verify_times")
    private Long verifyTimes;

    /**
     * 验证码发送时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVerifyTimes() {
        return verifyTimes;
    }

    public void setVerifyTimes(Long verifyTimes) {
        this.verifyTimes = verifyTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
