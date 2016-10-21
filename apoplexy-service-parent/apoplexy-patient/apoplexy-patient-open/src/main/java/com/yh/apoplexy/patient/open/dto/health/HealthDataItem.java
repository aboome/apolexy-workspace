package com.yh.apoplexy.patient.open.dto.health;

import java.io.Serializable;

/**
 * 健康数据实体项
 * Created by wunder on 16/9/1 10:49.
 */
public class HealthDataItem implements Serializable {

    private static final long serialVersionUID = 1323006668093082038L;

    /**
     * 记录ID
     */
    private String recordId;

    /**
     * 记录日期
     */
    private String recordDate;

    /**
     * 高压
     */
    private String bloodPressureHigh;

    /**
     * 低压
     */
    private String bloodPressureLow;

    /**
     * 血糖
     */
    private String bloodSugar;

    /**
     * 血脂
     */
    private String bloodFat;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
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
