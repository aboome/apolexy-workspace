package com.yh.apoplexy.admin.portal.doctor.forms;

/**
 * 查询ast病例的回复列表
 *
 * @author zhangbiao
 */
public class AdminAstCommentForm {

    private String id;

    private String pageSize;

    private String pageNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }


}
