package com.yh.apoplexy.assist.dto.patient.health;

import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * 提交高危筛查实体类
 * Created by wunder on 16/9/26 20:57.
 */
public class SubmitHealthScreenDto extends Entity {

    private static final long serialVersionUID = 5729060140901932023L;

    private PatientScreenDmo patientScreenDmo;

    private List<PatientScreenDetailDmo> historyList;

    private List<PatientScreenDetailDmo> screenList;

    private Boolean incidence;

    public PatientScreenDmo getPatientScreenDmo() {
        return patientScreenDmo;
    }

    public void setPatientScreenDmo(PatientScreenDmo patientScreenDmo) {
        this.patientScreenDmo = patientScreenDmo;
    }

    public List<PatientScreenDetailDmo> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<PatientScreenDetailDmo> historyList) {
        this.historyList = historyList;
    }

    public List<PatientScreenDetailDmo> getScreenList() {
        return screenList;
    }

    public void setScreenList(List<PatientScreenDetailDmo> screenList) {
        this.screenList = screenList;
    }

    public Boolean getIncidence() {
        return incidence;
    }

    public void setIncidence(Boolean incidence) {
        this.incidence = incidence;
    }
}
