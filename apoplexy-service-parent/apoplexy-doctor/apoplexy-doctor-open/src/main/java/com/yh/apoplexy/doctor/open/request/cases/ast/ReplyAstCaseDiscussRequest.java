package com.yh.apoplexy.doctor.open.request.cases.ast;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * AST病例详情-回复评论(doc-0039)请求
 * Created by wunder on 16/9/2 14:21.
 */
public class ReplyAstCaseDiscussRequest implements Serializable {

    private static final long serialVersionUID = -6155522618054911986L;

    /**
     * 发起回复用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String fromUserId;

    /**
     * 发起回复用户名称
     */
    private String fromUserName;

    /**
     * 收到回复用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String toUserId;

    /**
     * 收到回复用户名称
     */
    private String toUserName;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 父评论ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
