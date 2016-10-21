package com.yh.apoplexy.patient.open.processor.health;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.base.service.intf.HospitalInfoService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.health.QueryNearbyScreenDetailRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.health.QueryNearbyScreenDetailResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询筛查点详情 (pat-0007)处理类
 * Created by wunder on 16/9/5 17:17.
 */
public class QueryNearbyScreenDetailProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryNearbyScreenDetailRequest.class);

    @Autowired
    private HospitalInfoService hospitalInfoService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryNearbyScreenDetailResponse queryNearbyScreenDetailResponse = new QueryNearbyScreenDetailResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryNearbyScreenDetailRequest queryNearbyScreenDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryNearbyScreenDetailRequest.class);

        //参数校验
        if (null == queryNearbyScreenDetailRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryNearbyScreenDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        HospitalInfoDmo hospitalInfoCon = new HospitalInfoDmo();

        hospitalInfoCon.setId(Long.parseLong(queryNearbyScreenDetailRequest.getHospitalId()));
        hospitalInfoCon.setStatus(Constants.HospitalStatus.NORMAL);

        HospitalInfoDmo hospitalInfoDmo = hospitalInfoService.find(hospitalInfoCon);

        if (null == hospitalInfoDmo){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        queryNearbyScreenDetailResponse.setHospitalId(String.valueOf(hospitalInfoDmo.getId()));
        queryNearbyScreenDetailResponse.setHospitalName(hospitalInfoDmo.getHospitalName());
        queryNearbyScreenDetailResponse.setAddr(hospitalInfoDmo.getHospitalAddr());
        queryNearbyScreenDetailResponse.setDesc(hospitalInfoDmo.getHospitalDesc());
        queryNearbyScreenDetailResponse.setImageId(String.valueOf(hospitalInfoDmo.getImageId()));
        queryNearbyScreenDetailResponse.setHospitalLevel(hospitalInfoDmo.getLevel());
        queryNearbyScreenDetailResponse.setHospitalUnion(hospitalInfoDmo.getHospitalUnion());

        response.setParameter(queryNearbyScreenDetailResponse);

        return response;
    }
}
