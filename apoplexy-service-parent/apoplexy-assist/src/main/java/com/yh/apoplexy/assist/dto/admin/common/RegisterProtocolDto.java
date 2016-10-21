package com.yh.apoplexy.assist.dto.admin.common;

import com.yjh.framework.core.entity.Entity;

/**
 * Created by wunder on 16/9/24 13:13.
 */
public class RegisterProtocolDto extends Entity {

    private static final long serialVersionUID = -1270595912638509289L;

    private Long id;

    private String owner;

    private String protocolContent;

    private String createTime;

    private String lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProtocolContent() {
        return protocolContent;
    }

    public void setProtocolContent(String protocolContent) {
        this.protocolContent = protocolContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
