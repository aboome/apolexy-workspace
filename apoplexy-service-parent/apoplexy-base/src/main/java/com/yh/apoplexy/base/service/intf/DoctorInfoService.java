package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yjh.framework.lang.Result;

/**
 * 医生信息服务接口类
 * Created by wunder on 16/9/6 10:29.
 */
public interface DoctorInfoService {

    /**
     * 查询医生信息
     * @param doctorInfoDmo
     * @return
     */
    public DoctorInfoDmo find(DoctorInfoDmo doctorInfoDmo);

    /**
     * 根据手机号码查询医生信息
     * @param phone
     * @return
     */
    public DoctorInfoDmo findDoctorByPhone(String phone);

}
