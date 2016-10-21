package com.yh.apoplexy.doctor.open.processor.information;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.information.service.intf.DoctorInformationService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.information.QueryInformationDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.information.QueryInformationDetailResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询最新资讯详情(doc-0052)处理类
 * Created by wunder on 16/9/6 18:37.
 */
public class QueryInformationDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryInformationDetailProcessor.class);

    @Autowired
    private DoctorInformationService doctorInformationService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryInformationDetailResponse queryInformationDetailResponse = new QueryInformationDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryInformationDetailRequest queryInformationDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryInformationDetailRequest.class);

        if (null == queryInformationDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryInformationDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long newsId = Long.parseLong(queryInformationDetailRequest.getNewId());

        DoctorNewsDmo con = new DoctorNewsDmo();

        con.setId(newsId);
        con.setStatus(DoctorConstants.NewsStatus.NORMAL);

        DoctorNewsDmo doctorNewsDmo = doctorInformationService.find(con);

        if (null == doctorNewsDmo){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        queryInformationDetailResponse.setNewId(String.valueOf(doctorNewsDmo.getId()));
        queryInformationDetailResponse.setTile(doctorNewsDmo.getTitle());
        queryInformationDetailResponse.setSubTitle(doctorNewsDmo.getSubTitle());
        queryInformationDetailResponse.setContent(doctorNewsDmo.getContent());
        queryInformationDetailResponse.setDate(DateUtil.format(doctorNewsDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
        queryInformationDetailResponse.setContentType(doctorNewsDmo.getContentType());

        response.setParameter(queryInformationDetailResponse);

        return response;

    }
}
