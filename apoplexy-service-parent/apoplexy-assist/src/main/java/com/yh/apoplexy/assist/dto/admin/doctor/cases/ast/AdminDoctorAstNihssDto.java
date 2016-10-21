package com.yh.apoplexy.assist.dto.admin.doctor.cases.ast;

import com.yjh.framework.core.entity.Entity;

public class AdminDoctorAstNihssDto extends Entity {

    /**
     * ast详情实体类
     * Created by zhouqian on 16/9/26 14:24.
     */
    private static final long serialVersionUID = -2122311385687931684L;

    private Long detailIndex;

    private String question;

    private String result;

    /**
     * @return the detailIndex
     */
    public Long getDetailIndex() {
        return detailIndex;
    }

    /**
     * @param detailIndex the detailIndex to set
     */
    public void setDetailIndex(Long detailIndex) {
        this.detailIndex = detailIndex;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }


}
