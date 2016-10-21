package com.yh.apoplexy.patient.open.request.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.patient.open.dto.health.OptionItem;

import java.io.Serializable;
import java.util.List;

/**
 * 提交FAST自测表单 (pat-0012)请求
 * Created by wunder on 16/9/1 12:07.
 */
public class SubmitFASTFormRequest implements Serializable {

    private static final long serialVersionUID = -7841512508607772288L;

    /**
     * 用户id
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 年龄
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String age;

    /**
     * 性别：0：男   1：女
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String sex;

    /**
     * 提交时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String onsetTime;

    /**
     * 面部
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String face;

    /**
     * 手臂
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String arm;

    /**
     * 语言
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String speech;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOnsetTime() {
        return onsetTime;
    }

    public void setOnsetTime(String onsetTime) {
        this.onsetTime = onsetTime;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getArm() {
        return arm;
    }

    public void setArm(String arm) {
        this.arm = arm;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }
}
