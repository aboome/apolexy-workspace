package com.yh.apoplexy.patient.open.processor.knowledge;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.knowledge.service.intf.PatientKnowledgeService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.knowledge.QueryHealthKnowledgeDetailRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.knowledge.QueryHealthKnowledgeDetailResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询健康宣教详情 (pat-0011)处理类
 * Created by wunder on 16/9/5 20:22.
 */
public class QueryHealthKnowledgeDetailProcessor extends PatientAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryHealthKnowledgeDetailProcessor.class);

    @Autowired
    private PatientKnowledgeService patientKnowledgeService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryHealthKnowledgeDetailResponse queryHealthKnowledgeDetailResponse = new QueryHealthKnowledgeDetailResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryHealthKnowledgeDetailRequest queryHealthKnowledgeDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryHealthKnowledgeDetailRequest.class);

        //参数校验
        if (null == queryHealthKnowledgeDetailRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryHealthKnowledgeDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long newsId = Long.parseLong(queryHealthKnowledgeDetailRequest.getNewId());

        PatientNewsDmo con = new PatientNewsDmo();

        con.setId(newsId);
        con.setStatus(PatientConstants.NewsStatus.NORMAL);

        PatientNewsDmo patientNewsDmo = patientKnowledgeService.find(con);

        if (null == patientNewsDmo){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        queryHealthKnowledgeDetailResponse.setNewId(String.valueOf(patientNewsDmo.getId()));
        queryHealthKnowledgeDetailResponse.setTitle(patientNewsDmo.getTitle());
        queryHealthKnowledgeDetailResponse.setSubTitle(patientNewsDmo.getSubTitle());
        queryHealthKnowledgeDetailResponse.setContent(patientNewsDmo.getContent());
        queryHealthKnowledgeDetailResponse.setDate(DateUtil.format(patientNewsDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
        queryHealthKnowledgeDetailResponse.setType(patientNewsDmo.getType());
        queryHealthKnowledgeDetailResponse.setContentType(patientNewsDmo.getContentType());

        response.setParameter(queryHealthKnowledgeDetailResponse);

        return response;
    }
}
