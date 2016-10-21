package com.yh.apoplexy.doctor.open.response.cases.ast;

import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussDetailItem;

import java.util.List;

/**
 * 我的-我收藏的详情(doc-0048)响应
 * Created by wunder on 16/9/2 14:54.
 */
public class QueryMyCollectAstCaseDetailResponse extends AstCaseItem {

    private static final long serialVersionUID = 2088420234903703240L;

    private List<DiscussDetailItem> discussList;

    public List<DiscussDetailItem> getDiscussList() {
        return discussList;
    }

    public void setDiscussList(List<DiscussDetailItem> discussList) {
        this.discussList = discussList;
    }
}
