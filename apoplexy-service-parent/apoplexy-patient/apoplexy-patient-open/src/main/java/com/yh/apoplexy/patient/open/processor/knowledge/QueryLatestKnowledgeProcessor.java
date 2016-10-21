package com.yh.apoplexy.patient.open.processor.knowledge;

import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.patient.knowledge.service.intf.PatientKnowledgeService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.knowledge.QueryLatestKnowledgeResponse;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询最新健康宣教信息(pat-0020)处理类
 * Created by wunder on 16/9/22 20:09.
 */
public class QueryLatestKnowledgeProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryLatestKnowledgeProcessor.class);

    @Autowired
    private PatientKnowledgeService patientKnowledgeService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryLatestKnowledgeResponse queryLatestKnowledgeResponse = new QueryLatestKnowledgeResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        PatientNewsDmo con = new PatientNewsDmo();

        PatientNewsDmo patientNewsDmo = patientKnowledgeService.findLatestKnowledge(con);

        if (null != patientNewsDmo) {

            queryLatestKnowledgeResponse.setRecordId(String.valueOf(patientNewsDmo.getId()));

        }

        response.setParameter(queryLatestKnowledgeResponse);
        return response;
    }
}
