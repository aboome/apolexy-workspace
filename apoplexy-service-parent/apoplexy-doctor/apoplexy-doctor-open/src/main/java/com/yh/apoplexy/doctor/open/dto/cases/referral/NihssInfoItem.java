package com.yh.apoplexy.doctor.open.dto.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * NIHSS信息实体项
 * Created by wunder on 16/9/1 19:10.
 */
public class NihssInfoItem implements Serializable {

    private static final long serialVersionUID = -729827071695062756L;

    /**
     * nihss题目序号
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String index;

    /**
     * 得分
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String fee;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
