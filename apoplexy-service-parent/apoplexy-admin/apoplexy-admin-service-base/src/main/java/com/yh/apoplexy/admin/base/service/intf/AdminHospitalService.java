package com.yh.apoplexy.admin.base.service.intf;

import java.util.LinkedHashMap;
import java.util.List;

import com.yh.apoplexy.admin.base.service.result.ImportHospitalResult;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dto.admin.base.AdminHospitalDto;
import com.yh.apoplexy.assist.dto.admin.base.AdminImportHospitalListDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

public interface AdminHospitalService {

	/**
	 * 查询医院列表
	 * @param hospitalInfoDmo
	 * @param page
	 * @return
	 */
	public List<AdminHospitalDto> selectListByPage(HospitalInfoDmo hospitalInfoDmo,Page page);

    /**
     * 查询医院列表
     * @param con
     * @return
     */
    public List<HospitalInfoDmo> selectList(HospitalInfoDmo con);

	/**
	 * 查看医院详情
	 * @param hospitalInfoDmo
	 * @return
	 */
	
	public HospitalInfoDmo selectHospitalDetails(HospitalInfoDmo hospitalInfoDmo); 
	
	/**
	 * 查询下级医院
	 * @param hospitalInfoDmo
	 * @return
	 */
	public Long selectInferiorHospital(HospitalInfoDmo hospitalInfoDmo);
	
    /**
     * 新增医院信息	
     * @param hospitalInfoDmo
     * @return
     */
	public Result addHospitalDetails(AdminHospitalDto adminHospitalDto);
	
	/**
	 * 修改医院信息
	 * @param hospitalInfoDmo
	 * @return
	 */
	public Result updateHospitalDetails(AdminHospitalDto adminHospitalDto);
	
	/**
	 * 删除医院信息
	 * @param hospitalInfoDmo
	 * @return
	 */
	public Result deleteHospitalDetails(HospitalInfoDmo hospitalInfoDmo);
	
	/**
	 * 导入医院，记录总成功数据，总失败数
	 * 
	 * @param excelData
	 * @return
	 */
	public ImportHospitalResult parseImportExcel(List<LinkedHashMap<String, Object>> excelData);
	
}
