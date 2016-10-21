package com.yh.apoplexy.doctor.open.response.cases.ast;

import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCommentItem;

import java.io.Serializable;
import java.util.List;

/**
 * 查询AST病例评论列表(doc-0063)响应
 * Created by wunder on 16/9/11 10:18.
 */
public class QueryAstCommentListResponse implements Serializable {

    private static final long serialVersionUID = -841992630571618745L;

    /**
     * 评论列表
     */
    private List<AstCommentItem> commentList;

    /**
     * 总记录数
     */
    private String total;

    /**
     * 当前页数
     */
    private String pageNum;

    /**
     * 每页记录数
     */
    private String pageSize;

    public List<AstCommentItem> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<AstCommentItem> commentList) {
        this.commentList = commentList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
