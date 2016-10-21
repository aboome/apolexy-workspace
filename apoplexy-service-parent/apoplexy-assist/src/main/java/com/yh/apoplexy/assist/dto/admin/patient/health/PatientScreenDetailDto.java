package com.yh.apoplexy.assist.dto.admin.patient.health;

import com.yjh.framework.core.entity.Entity;

/**
 * 高危筛查详情实体类
 * Created by wunder on 16/9/19 18:46.
 */
public class PatientScreenDetailDto extends Entity {

    private static final long serialVersionUID = 4425996595920315727L;

    private Long detailIndex;

    private String question;

    private String result;

    public Long getDetailIndex() {
        return detailIndex;
    }

    public void setDetailIndex(Long detailIndex) {
        this.detailIndex = detailIndex;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
