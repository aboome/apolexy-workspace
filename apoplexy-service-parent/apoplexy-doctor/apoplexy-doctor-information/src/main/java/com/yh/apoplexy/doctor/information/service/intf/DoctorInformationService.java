package com.yh.apoplexy.doctor.information.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 医生学术更新服务接口类
 * Created by wunder on 16/9/6 18:26.
 */
public interface DoctorInformationService {

    /**
     * 查询医生学术更新内容
     * @param con
     * @return
     */
    public DoctorNewsDmo find(DoctorNewsDmo con);

    /**
     * 分页查询医生学术更新内容
     * @param con
     * @param page
     * @return
     */
    public List<DoctorNewsDmo> selectListByPage(DoctorNewsDmo con, Page page);

}
