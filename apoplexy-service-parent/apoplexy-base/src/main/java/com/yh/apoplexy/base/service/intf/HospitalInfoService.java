package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;

import java.util.List;

/**
 * 医院信息服务接口类
 * Created by wunder on 16/9/5 15:10.
 */
public interface HospitalInfoService {

    /**
     * 查询最近的医院列表
     * @param lat
     * @param lon
     * @return
     */
    public List<HospitalInfoDmo> selectNearHospitalList(String lat, String lon);

    /**
     * 查询医院信息详情
     * @param hospitalInfoDmo
     * @return
     */
    public HospitalInfoDmo find(HospitalInfoDmo hospitalInfoDmo);

    /**
     * 查询最近的筛选点列表
     * @param lat
     * @param lon
     * @return
     */
    public List<HospitalInfoDmo> selectNearScreenList(String lat, String lon);

}
