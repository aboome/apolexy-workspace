package com.yh.apoplexy.patient.open.response.knowledge;

import java.io.Serializable;

/**
 * 查询健康宣教详情 (pat-0011)响应
 * Created by wunder on 16/9/1 11:00.
 */
public class QueryHealthKnowledgeDetailResponse implements Serializable {

    private static final long serialVersionUID = -965608793941171571L;

    /**
     * 健康宣教ID
     */
    private String newId;

    /**
     * 标题
     */
    private String title;

    /**
     * 时间
     */
    private String date;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 类型
     */
    private String type;

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
