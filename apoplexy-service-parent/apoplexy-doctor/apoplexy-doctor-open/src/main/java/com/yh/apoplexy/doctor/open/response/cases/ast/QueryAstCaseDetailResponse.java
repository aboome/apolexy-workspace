package com.yh.apoplexy.doctor.open.response.cases.ast;

import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussDetailItem;

import java.util.List;

/**
 * 查询AST病例详情(doc-0037)响应
 * Created by wunder on 16/9/2 11:41.
 */
public class QueryAstCaseDetailResponse extends AstCaseItem {

    private static final long serialVersionUID = -6164723069188953936L;

    private List<DiscussDetailItem> discussList;

    public List<DiscussDetailItem> getDiscussList() {
        return discussList;
    }

    public void setDiscussList(List<DiscussDetailItem> discussList) {
        this.discussList = discussList;
    }
}
