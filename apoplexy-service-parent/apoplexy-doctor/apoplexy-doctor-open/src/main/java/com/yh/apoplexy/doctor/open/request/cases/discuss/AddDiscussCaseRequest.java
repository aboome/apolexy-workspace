package com.yh.apoplexy.doctor.open.request.cases.discuss;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussCaseItem;

/**
 * 新增病例讨论(doc-0006)请求
 * Created by wunder on 16/9/1 15:28.
 */
public class AddDiscussCaseRequest extends DiscussCaseItem {

    private static final long serialVersionUID = -5547186037430360338L;

    /**
     * 医生ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
