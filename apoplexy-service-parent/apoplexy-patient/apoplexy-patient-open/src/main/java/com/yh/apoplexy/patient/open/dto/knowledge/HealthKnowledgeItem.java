package com.yh.apoplexy.patient.open.dto.knowledge;

import java.io.Serializable;

/**
 * 健康知识实体项
 * Created by wunder on 16/9/1 10:54.
 */
public class HealthKnowledgeItem implements Serializable {

    private static final long serialVersionUID = -6797287690732152498L;

    /**
     * 宣教记录的id
     */
    private String newId;

    /**
     * 标题
     */
    private String title;

    /**
     * 宣教资料类型
     */
    private String type;

    /**
     * 摘要
     */
    private String sub;

    /**
     * 列表中的小图
     */
    private String smallImage;

    /**
     * 宣教资料录入时间
     */
    private String date;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
