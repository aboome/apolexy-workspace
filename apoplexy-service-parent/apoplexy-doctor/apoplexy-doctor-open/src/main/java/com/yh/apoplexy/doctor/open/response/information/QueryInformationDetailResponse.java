package com.yh.apoplexy.doctor.open.response.information;

import java.io.Serializable;

/**
 * 查询最新资讯详情(doc-0052)响应
 * Created by wunder on 16/9/2 15:21.
 */
public class QueryInformationDetailResponse implements Serializable {

    private static final long serialVersionUID = 2903897982364007915L;

    /**
     * 健康宣教ID
     */
    private String newId;

    /**
     * 标题
     */
    private String tile;

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

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
