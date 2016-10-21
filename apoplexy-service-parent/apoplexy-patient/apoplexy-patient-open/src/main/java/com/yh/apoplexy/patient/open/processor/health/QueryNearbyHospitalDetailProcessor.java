package com.yh.apoplexy.patient.open.processor.health;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.base.service.intf.HospitalInfoService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.health.QueryNearbyHospitalDetailRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.health.QueryNearbyHospitalDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询医院详情(pat-0014)处理类
 * Created by wunder on 16/9/13 11:29.
 */
public class QueryNearbyHospitalDetailProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryNearbyHospitalDetailProcessor.class);

    @Autowired
    private HospitalInfoService hospitalInfoService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryNearbyHospitalDetailResponse queryNearbyHospitalDetailResponse = new QueryNearbyHospitalDetailResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryNearbyHospitalDetailRequest queryNearbyHospitalDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryNearbyHospitalDetailRequest.class);

        //参数校验
        if (null == queryNearbyHospitalDetailRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryNearbyHospitalDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        HospitalInfoDmo hospitalInfoCon = new HospitalInfoDmo();

        hospitalInfoCon.setId(Long.parseLong(queryNearbyHospitalDetailRequest.getHospitalId()));
        hospitalInfoCon.setStatus(Constants.HospitalStatus.NORMAL);

        HospitalInfoDmo hospitalInfoDmo = hospitalInfoService.find(hospitalInfoCon);

        if (null == hospitalInfoDmo){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        queryNearbyHospitalDetailResponse.setHospitalId(String.valueOf(hospitalInfoDmo.getId()));
        queryNearbyHospitalDetailResponse.setHospitalName(hospitalInfoDmo.getHospitalName());
        queryNearbyHospitalDetailResponse.setAddr(hospitalInfoDmo.getHospitalAddr());
        queryNearbyHospitalDetailResponse.setDesc(hospitalInfoDmo.getHospitalDesc());
        queryNearbyHospitalDetailResponse.setImageId(String.valueOf(hospitalInfoDmo.getImageId()));
        queryNearbyHospitalDetailResponse.setHospitalLevel(hospitalInfoDmo.getLevel());
        queryNearbyHospitalDetailResponse.setHospitalUnion(hospitalInfoDmo.getHospitalUnion());

        response.setParameter(queryNearbyHospitalDetailResponse);

        return response;
    }
}
