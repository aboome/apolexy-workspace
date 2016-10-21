package com.yh.apoplexy.doctor.open.response.cases.ast;

import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussDetailItem;

import java.util.List;

/**
 * 我的-我参与的详情(doc-0046)响应
 * Created by wunder on 16/9/2 14:49.
 */
public class QueryMyPartakeAstCaseDetailResponse extends AstCaseItem {

    private static final long serialVersionUID = -5328693723492266969L;

    private List<DiscussDetailItem> discussList;

    public List<DiscussDetailItem> getDiscussList() {
        return discussList;
    }

    public void setDiscussList(List<DiscussDetailItem> discussList) {
        this.discussList = discussList;
    }
}
