package com.yh.apoplexy.doctor.open.response.cases.ast;

import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussDetailItem;

import java.util.List;

/**
 * 我的-我发布的详情(doc-0043)响应
 * Created by wunder on 16/9/2 14:37.
 */
public class QueryMyPostAstCaseDetailResponse extends AstCaseItem {

    private static final long serialVersionUID = -16672020984732277L;

    private List<DiscussDetailItem> discussList;

    public List<DiscussDetailItem> getDiscussList() {
        return discussList;
    }

    public void setDiscussList(List<DiscussDetailItem> discussList) {
        this.discussList = discussList;
    }
}
