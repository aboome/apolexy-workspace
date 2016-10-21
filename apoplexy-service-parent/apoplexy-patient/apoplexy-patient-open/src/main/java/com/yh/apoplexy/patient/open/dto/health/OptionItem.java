package com.yh.apoplexy.patient.open.dto.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 健康题目项
 * Created by wunder on 16/9/1 10:07.
 */
public class OptionItem implements Serializable {

    private static final long serialVersionUID = 961132249186218130L;

    /**
     * 序号
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String index;

    /**
     * 答案
     * 1：是
     * 0：否
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
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
