package com.yh.apoplexy.patient.open.request.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 提交体征数据 (pat-0008)请求
 * Created by wunder on 16/9/1 10:36.
 */
public class SubmitHealthDataRequest implements Serializable {

    private static final long serialVersionUID = -7598788210712529893L;

    /**
     * 用户id
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 高压值
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String bloodPressureHigh;

    /**
     * 低压值
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String bloodPressureLow;

    /**
     * 血糖
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String bloodSugar;

    /**
     * 血脂
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String bloodFat;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(String bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public String getBloodPressureLow() {
        return bloodPressureLow;
    }

    public void setBloodPressureLow(String bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getBloodFat() {
        return bloodFat;
    }

    public void setBloodFat(String bloodFat) {
        this.bloodFat = bloodFat;
    }
}
