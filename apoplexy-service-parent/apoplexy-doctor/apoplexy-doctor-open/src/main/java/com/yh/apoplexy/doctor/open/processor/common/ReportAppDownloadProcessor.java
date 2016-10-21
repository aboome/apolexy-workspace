package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.AppDownloadDmo;
import com.yh.apoplexy.base.service.intf.AppDownloadService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.ReportAppDownloadRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.ReportAppDownloadResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * APP下载统计 (common-0011)处理类
 * Created by wunder on 16/9/6 23:30.
 */
public class ReportAppDownloadProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportAppDownloadProcessor.class);

    @Autowired
    private AppDownloadService appDownloadService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        ReportAppDownloadResponse reportAppDownloadResponse = new ReportAppDownloadResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ReportAppDownloadRequest reportAppDownloadRequest = JSONObject.parseObject(requestObject.getParameter().toString(), ReportAppDownloadRequest.class);

        //参数错误
        if (null == reportAppDownloadRequest) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(reportAppDownloadRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String iemId = reportAppDownloadRequest.getIemId();

        String clientType = reportAppDownloadRequest.getClientType();

        String type = reportAppDownloadRequest.getType();

        AppDownloadDmo appDownloadDmo = new AppDownloadDmo();

        appDownloadDmo.setIemId(iemId);
        appDownloadDmo.setType(type);
        appDownloadDmo.setClientType(clientType);
        appDownloadDmo.setCreateTime(DateUtil.getDate());

        Result result = appDownloadService.report(appDownloadDmo);

        if (!result.isSuccess()){

            reportAppDownloadResponse.setResultcode(APPResponseCodeConstants.AppDownload.FAILED);
            reportAppDownloadResponse.setMessage("提交失败");

        }else {

            reportAppDownloadResponse.setResultcode(APPResponseCodeConstants.AppDownload.SUCCESS);
            reportAppDownloadResponse.setMessage("提交成功");

        }

        response.setParameter(reportAppDownloadResponse);
        return response;
    }
}
