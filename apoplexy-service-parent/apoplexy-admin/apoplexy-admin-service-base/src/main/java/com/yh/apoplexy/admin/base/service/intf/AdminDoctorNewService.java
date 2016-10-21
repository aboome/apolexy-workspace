package com.yh.apoplexy.admin.base.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

public interface AdminDoctorNewService {
	/**
	 * 医生查询学术更新/最新资讯列表
	 * 
	 * @param doctorNewsDmo
	 * @param page
	 * @return
	 */
	public List<DoctorNewsDmo> selectListByPage(DoctorNewsDmo doctorNewsDmo, Page page);

	/**
	 * 医生查询学术更新/最新资讯详情
	 * 
	 * @param doctorNewsDmo
	 * @return
	 */
	public DoctorNewsDmo selectDoctorNewsDetails(
			DoctorNewsDmo doctorNewsDmo);

	/**
	 * 新增医生学术更新/最新资讯
	 * 
	 * @param doctorNewsDmo
	 * @return
	 */
	public Result addDoctorNews(DoctorNewsDmo doctorNewsDmo);

	/**
	 * 修改医生修改学术更新/最新资讯
	 * 
	 * @param doctorNewsDmo
	 * @return
	 */
	public Result updateDoctorNewsDetails(DoctorNewsDmo doctorNewsDmo);

	/**
	 * 删除医生学术更新/最新资讯
	 * 
	 * @param doctorNewsDmo
	 * @return
	 */
	public Result deleteDoctorNewsDetails(DoctorNewsDmo doctorNewsDmo);

    /**
     * 推送资讯消息
     * @param doctorNewsDmo
     * @return
     */
    public Result pushMessage(DoctorNewsDmo doctorNewsDmo);
}
