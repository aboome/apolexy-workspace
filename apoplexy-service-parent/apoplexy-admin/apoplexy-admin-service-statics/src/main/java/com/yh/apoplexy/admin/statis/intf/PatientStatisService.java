package com.yh.apoplexy.admin.statis.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.assist.dto.patient.member.PatientMemberDto;

/**
 * 
 * 平台患者注册统计列表
 * @author zhangbiao
 *
 */
public interface PatientStatisService {
	 /**
     * 根据地区统计已注册患者
     * @return
     */
    public List<PatientMemberDto> queryPatientCount(PatientMemberDmo memberDmo );

    /**
     * 按照是否填写FAST量表统计患者数量
     * @param patientMemberDmo
     * @return
     */
    public List<PatientMemberDto> countPatientByFast(PatientMemberDmo patientMemberDmo);
    
    /**
     * 按照高危标示统计患者数量
     * @param patientMemberDmo
     * @return
     */
    public List<PatientMemberDto> countPatientByIncidence(PatientMemberDmo patientMemberDmo);
    
}
