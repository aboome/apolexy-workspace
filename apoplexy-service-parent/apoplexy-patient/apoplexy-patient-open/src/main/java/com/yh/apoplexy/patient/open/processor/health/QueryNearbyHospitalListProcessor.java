package com.yh.apoplexy.patient.open.processor.health;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.base.service.intf.HospitalInfoService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.BaiDuMapUtil;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.common.utils.MapUtil;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.dto.health.HospitalInfoItem;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.health.QueryNearbyHospitalListRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.health.QueryNearbyHospitalListResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询附近的医院列表 (pat-0013)处理类
 * Created by wunder on 16/9/13 10:47.
 */
public class QueryNearbyHospitalListProcessor extends PatientAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryNearbyHospitalListProcessor.class);

    @Autowired
    private HospitalInfoService hospitalInfoService;

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryNearbyHospitalListResponse queryNearbyHospitalListResponse = new QueryNearbyHospitalListResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryNearbyHospitalListRequest queryNearbyHospitalListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryNearbyHospitalListRequest.class);

        //参数校验
        if (null == queryNearbyHospitalListRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryNearbyHospitalListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryNearbyHospitalListRequest.getUserId());
        String lonString = queryNearbyHospitalListRequest.getLon();
        String latString = queryNearbyHospitalListRequest.getLat();

        Double lon = Double.parseDouble(lonString);
        Double lat = Double.parseDouble(latString);

        PatientMemberDmo patientMemberCon = new PatientMemberDmo();

        patientMemberCon.setId(userId);
        patientMemberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询患者信息
        PatientMemberDmo patientMemberDmo = patientMemberService.findPatientMember(patientMemberCon);

        if (null != patientMemberDmo){

            String areaCode = BaiDuMapUtil.getLocationInfo(latString,lonString);

            if (StringUtils.isNotBlank(areaCode)){

                patientMemberDmo.setAreaCode(areaCode);
                patientMemberService.update(patientMemberDmo);
            }

        }

        List<HospitalInfoDmo> hospitalInfoDmoList = hospitalInfoService.selectNearHospitalList(lonString,latString);

        List<HospitalInfoItem> hospitalInfoItemList = new ArrayList<>();

        HospitalInfoItem hospitalInfoItem = null;

        if (CollectionUtils.isEmpty(hospitalInfoDmoList)){

            queryNearbyHospitalListResponse.setList(hospitalInfoItemList);

            response.setParameter(queryNearbyHospitalListResponse);

            return response;

        }

        for (HospitalInfoDmo hospitalInfoDmo: hospitalInfoDmoList){

            hospitalInfoItem = new HospitalInfoItem();

            hospitalInfoItem.setHospitalId(String.valueOf(hospitalInfoDmo.getId()));
            hospitalInfoItem.setHospitalName(hospitalInfoDmo.getHospitalName());
            hospitalInfoItem.setLon(String.valueOf(hospitalInfoDmo.getLon()));
            hospitalInfoItem.setLat(String.valueOf(hospitalInfoDmo.getLat()));
            hospitalInfoItem.setHospitalLevel(hospitalInfoDmo.getLevel());
            hospitalInfoItem.setHospitalUnion(hospitalInfoDmo.getHospitalUnion());

            if (null != hospitalInfoDmo.getLon()&&null != hospitalInfoDmo.getLat()){
                Double distance = MapUtil.distanceBetween(hospitalInfoDmo.getLon(),hospitalInfoDmo.getLat(),lon,lat);
                hospitalInfoItem.setDistance(String.valueOf(distance));
            }

            hospitalInfoItemList.add(hospitalInfoItem);

        }

        queryNearbyHospitalListResponse.setList(hospitalInfoItemList);

        response.setParameter(queryNearbyHospitalListResponse);

        return response;

    }
}
