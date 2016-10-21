package com.yh.apoplexy.doctor.open.dto.common;

import java.io.Serializable;

/**
 * 通知消息详情实体项
 * Created by wunder on 16/9/2 16:07.
 */
public class MessageDetailItem implements Serializable {

    private static final long serialVersionUID = -3593142800244936709L;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息类型(1：病例讨论;2：病例转诊;3：资讯;4：系统)
     */
    private String type;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息时间
     */
    private String messageTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
