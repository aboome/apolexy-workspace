package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.SystemVersionInfoDmo;
import com.yh.apoplexy.base.service.intf.SystemVersionService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.AppUpdateRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.AppUpdateResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * APP版本升级接口 (common-0010)处理类
 * Created by wunder on 16/9/4 14:01.
 */
public class AppUpdateProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUpdateProcessor.class);

    @Autowired
    private SystemVersionService systemVersionService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        AppUpdateResponse appUpdateResponse = new AppUpdateResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        AppUpdateRequest appUpdateRequest = JSONObject.parseObject(requestObject.getParameter().toString(),AppUpdateRequest.class);

        if (null == appUpdateRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(appUpdateRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        SystemVersionInfoDmo con = new SystemVersionInfoDmo();

        con.setModel(appUpdateRequest.getType());
        con.setStatus(Constants.SystemVersionStatus.NORMAL);

        SystemVersionInfoDmo systemVersionInfoDmo = systemVersionService.selectOne(con);

        if (null == systemVersionInfoDmo){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            response.setParameter(appUpdateResponse);

            return response;

        }

        String currentVersion = appUpdateRequest.getVersion();

        String latestVersion = systemVersionInfoDmo.getVersion();

        //判断是否需要升级
        if (currentVersion.equals(latestVersion)){
            appUpdateResponse.setIsNeedUpgrade(AppConstants.NeedUpgrade.NOT_NEED);
        }else {
            appUpdateResponse.setIsNeedUpgrade(AppConstants.NeedUpgrade.NEED);
        }

        appUpdateResponse.setUpgradeVersion(systemVersionInfoDmo.getVersion());
        appUpdateResponse.setUpgradeUrl(systemVersionInfoDmo.getUpgradeUrl());

        response.setParameter(appUpdateResponse);

        return response;

    }
}
