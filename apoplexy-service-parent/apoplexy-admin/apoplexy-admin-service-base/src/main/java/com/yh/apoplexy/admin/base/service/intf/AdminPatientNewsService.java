package com.yh.apoplexy.admin.base.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

public interface AdminPatientNewsService {
	
	/**
	 * 患者查询宣教列表
	 * @param adminPatientNewsDmo
	 * @param page
	 * @return
	 */
	
	public List<PatientNewsDmo> selectListByPage(PatientNewsDmo adminPatientNewsDmo,Page page);
	
	
    /**
     * 查询患者宣教详情
     * @param adminPatientNewsDmo
     * @return
     */
    public PatientNewsDmo selectDoctorNewsDetails(PatientNewsDmo adminPatientNewsDmo);
    
    /**
     * 新增患者宣教
     * @param adminPatientNewsDmo
     * @return
     */
    public Result addPatientNews(PatientNewsDmo adminPatientNewsDmo);
    
    /**
     * 修改患者宣教
     * @param adminPatientNewsDmo
     * @return
     */
    public Result updatePatientNewsDetails(PatientNewsDmo adminPatientNewsDmo);
    
    /**
     * 删除患者宣教
     * @param adminPatientNewsDmo
     * @return
     */
    public Result deletePatientNewsDetails(PatientNewsDmo adminPatientNewsDmo);
}
