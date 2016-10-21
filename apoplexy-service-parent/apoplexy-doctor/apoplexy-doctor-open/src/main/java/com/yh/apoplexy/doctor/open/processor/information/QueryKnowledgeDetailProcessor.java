package com.yh.apoplexy.doctor.open.processor.information;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.information.service.intf.DoctorInformationService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.information.QueryKnowledgeDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.information.QueryKnowledgeDetailResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询学习更新详情(doc-0050)处理类
 * Created by wunder on 16/9/6 18:38.
 */
public class QueryKnowledgeDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryKnowledgeDetailProcessor.class);

    @Autowired
    private DoctorInformationService doctorInformationService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryKnowledgeDetailResponse queryKnowledgeDetailResponse = new QueryKnowledgeDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryKnowledgeDetailRequest queryKnowledgeDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryKnowledgeDetailRequest.class);

        if (null == queryKnowledgeDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryKnowledgeDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long newsId = Long.parseLong(queryKnowledgeDetailRequest.getNewId());

        DoctorNewsDmo con = new DoctorNewsDmo();

        con.setId(newsId);
        con.setStatus(DoctorConstants.NewsStatus.NORMAL);

        DoctorNewsDmo doctorNewsDmo = doctorInformationService.find(con);

        if (null == doctorNewsDmo){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        queryKnowledgeDetailResponse.setNewId(String.valueOf(doctorNewsDmo.getId()));
        queryKnowledgeDetailResponse.setTile(doctorNewsDmo.getTitle());
        queryKnowledgeDetailResponse.setSubTitle(doctorNewsDmo.getSubTitle());
        queryKnowledgeDetailResponse.setContent(doctorNewsDmo.getContent());
        queryKnowledgeDetailResponse.setDate(DateUtil.format(doctorNewsDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
        queryKnowledgeDetailResponse.setContentType(doctorNewsDmo.getContentType());

        response.setParameter(queryKnowledgeDetailResponse);

        return response;

    }
}
