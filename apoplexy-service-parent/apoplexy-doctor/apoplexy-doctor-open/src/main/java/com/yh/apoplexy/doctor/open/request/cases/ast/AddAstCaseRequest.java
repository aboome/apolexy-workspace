package com.yh.apoplexy.doctor.open.request.cases.ast;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCaseItem;

import java.io.Serializable;

/**
 * 新增AST病例(doc-0035)请求
 * Created by wunder on 16/9/2 09:14.
 */
public class AddAstCaseRequest extends AstCaseItem {

    private static final long serialVersionUID = 4109869624152933936L;

    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
