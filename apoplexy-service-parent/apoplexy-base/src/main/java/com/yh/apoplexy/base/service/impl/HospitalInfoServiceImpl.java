package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dto.patient.health.QueryHospitalListDto;
import com.yh.apoplexy.base.service.intf.HospitalInfoService;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yjh.framework.core.trace.ServiceTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 医院信息服务实现类
 * Created by wunder on 16/9/5 15:15.
 */
@Service("hospitalInfoService")
@ServiceTrace
public class HospitalInfoServiceImpl implements HospitalInfoService {

    private String hospitalNumber = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.NEARBY_HOSPITAL_NUMBER);

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<HospitalInfoDmo> selectNearHospitalList(String lat, String lon) {

        QueryHospitalListDto con = new QueryHospitalListDto();

        con.setLon(lat);
        con.setLat(lon);
        con.setNumber(hospitalNumber);

        List<String> hospitalLevelList = new ArrayList<>();

        hospitalLevelList.add(Constants.HospitalLevel.COUNTY_HOSPITAL);
        hospitalLevelList.add(Constants.HospitalLevel.CITY_HOSPITAL);

        con.setHospitalLevelList(hospitalLevelList);

        con.setHospitalLevelList(hospitalLevelList);

        return commonDao.selectList("HospitalInfoMapper.queryNearHospitalList",con);
    }

    @Override
    public HospitalInfoDmo find(HospitalInfoDmo hospitalInfoDmo) {
        return (HospitalInfoDmo)commonDao.selectOne(hospitalInfoDmo);
    }

    @Override
    public List<HospitalInfoDmo> selectNearScreenList(String lat, String lon) {

        QueryHospitalListDto con = new QueryHospitalListDto();

        con.setLon(lat);
        con.setLat(lon);
        con.setNumber(hospitalNumber);

        List<String> hospitalLevelList = new ArrayList<>();

        hospitalLevelList.add(Constants.HospitalLevel.COUNTY_HOSPITAL);
        hospitalLevelList.add(Constants.HospitalLevel.TOWNSHIP_HOSPITAL);

        con.setHospitalLevelList(hospitalLevelList);

        return commonDao.selectList("HospitalInfoMapper.queryNearScreenList",con);
    }

}
