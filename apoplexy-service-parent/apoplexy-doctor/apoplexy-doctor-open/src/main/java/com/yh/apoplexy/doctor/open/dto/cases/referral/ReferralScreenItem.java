package com.yh.apoplexy.doctor.open.dto.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 转诊筛选选项题目项
 * Created by wunder on 16/9/1 10:07.
 */
public class ReferralScreenItem implements Serializable {

    private static final long serialVersionUID = 961132249186218130L;

    /**
     * 序号
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String index;

    /**
     * 答案
     * 1：是
     * 0：否
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String answer;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
