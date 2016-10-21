package com.yh.apoplexy.admin.base.service.intf;

import java.util.LinkedHashMap;
import java.util.List;

import com.yh.apoplexy.admin.base.service.result.ImportDoctorResult;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dto.admin.common.AdminDoctorInfoDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

public interface AdminDoctorService {

	/**
	 * 查询医生列表
	 * @param doctorInfoDmo
	 * @return
	 */
	public List<AdminDoctorInfoDto> selectListByPage(DoctorInfoDmo doctorInfoDmo, Page page);

    /**
     * 查询医生列表
     * @param con
     * @return
     */
    public List<DoctorInfoDmo> selectList(DoctorInfoDmo con);
	
	/**
	 * 查询医生详情
	 * @param doctorInfoDmo
	 * @return
	 */
	public DoctorInfoDmo selectDoctorDetails(DoctorInfoDmo doctorInfoDmo);
	
	/**
	 * 新增医生信息
	 * @param doctorInfoDmo
	 * @return
	 */
	public Result addDoctorDetails(DoctorInfoDmo doctorInfoDmo);
	
	/**
	 * 修改医生信息
	 * @param doctorInfoDmo
	 * @return
	 */
	public Result updateDoctorDetails(DoctorInfoDmo doctorInfoDmo);
	
	/**
	 * 删除医生信息
	 * @param doctorInfoDmo
	 * @return
	 */
	public Result deleteDoctorDetails(DoctorInfoDmo doctorInfoDmo);
	
	/**
	 * 批量增加医生信息
	 * @param adminImportDoctorListDto
	 * @return
	 */
	public ImportDoctorResult parseImportExcel(List<LinkedHashMap<String, Object>> excelData);
}
