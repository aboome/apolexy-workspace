package com.yh.apoplexy.assist.dmo.common;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.*;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * APP下载统计表
 * Created by wunder on 16/9/7 00:31.
 */
@Table(name = "t_app_download")
public class AppDownloadDmo extends Entity {

    private static final long serialVersionUID = 6377113881846396120L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * IMEI号
     */
    @Column(name = "iem_id")
    private String iemId;

    /**
     * APP类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 手机类型
     */
    @Column(name = "client_type")
    private String clientType;

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

    public String getIemId() {
        return iemId;
    }

    public void setIemId(String iemId) {
        this.iemId = iemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
