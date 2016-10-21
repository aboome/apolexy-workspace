package com.yh.apoplexy.admin.statis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.statis.intf.PatientStatisService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;

import com.yh.apoplexy.assist.dto.patient.member.PatientMemberDto;
import com.yjh.framework.core.trace.ServiceTrace;


@Service("patientStatisService")
@ServiceTrace
public class PatientStatisServiceImpl implements PatientStatisService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<PatientMemberDto> queryPatientCount(PatientMemberDmo memberDmo) {

        return commonDao.selectList("StatisticsMapper.queryPatientCount", memberDmo);
    }

    @Override
    public List<PatientMemberDto> countPatientByFast(PatientMemberDmo patientMemberDmo) {
        return commonDao.selectList("StatisticsMapper.countPatientByFast", patientMemberDmo);
    }

	@Override
	public List<PatientMemberDto> countPatientByIncidence(
			PatientMemberDmo patientMemberDmo) {
		
		return commonDao.selectList("StatisticsMapper.countPatientByIncidence",patientMemberDmo);
	}


}
