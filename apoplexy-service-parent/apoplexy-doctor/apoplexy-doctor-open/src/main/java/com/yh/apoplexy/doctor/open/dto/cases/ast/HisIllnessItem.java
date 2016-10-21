package com.yh.apoplexy.doctor.open.dto.cases.ast;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 既往史实体项
 * Created by wunder on 16/9/1 20:47.
 */
public class HisIllnessItem implements Serializable{

    private static final long serialVersionUID = 7523611097096774004L;

    /**
     * 既往史序号
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String index;

    /**
     * 答案(0：否;1：是)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String answer;

    /**
     * 最后一次发病时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SMALL_TEXT_REGULAR)
    private String time;

    /**
     * 描述
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SMALL_TEXT_REGULAR)
    private String desc;


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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
