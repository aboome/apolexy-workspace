package com.yh.apoplexy.patient.open.processor.knowledge;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.knowledge.service.intf.PatientKnowledgeService;
import com.yh.apoplexy.patient.open.dto.knowledge.HealthKnowledgeItem;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.knowledge.QueryHealthKnowledgeListRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.knowledge.QueryHealthKnowledgeListResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询健康宣教列表 (pat-0010)处理类
 * Created by wunder on 16/9/5 20:10.
 */
public class QueryHealthKnowledgeListProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryHealthKnowledgeListProcessor.class);

    @Autowired
    private PatientKnowledgeService patientKnowledgeService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryHealthKnowledgeListResponse queryHealthKnowledgeListResponse = new QueryHealthKnowledgeListResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryHealthKnowledgeListRequest queryHealthKnowledgeListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryHealthKnowledgeListRequest.class);

        //参数校验
        if (null == queryHealthKnowledgeListRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryHealthKnowledgeListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        int pageNum = Integer.parseInt(queryHealthKnowledgeListRequest.getCurPage());
        int pageSize = Integer.parseInt(queryHealthKnowledgeListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        PatientNewsDmo con = new PatientNewsDmo();

        con.setStatus(PatientConstants.NewsStatus.NORMAL);

        List<PatientNewsDmo> patientNewsDmoList = patientKnowledgeService.selectListByPage(con,page);

        if (CollectionUtils.isEmpty(patientNewsDmoList)){

            response.setParameter(queryHealthKnowledgeListResponse);
            return response;

        }

        List<HealthKnowledgeItem> healthKnowledgeItemList = new ArrayList<>();

        HealthKnowledgeItem healthKnowledgeItem = null;

        for (PatientNewsDmo patientNewsDmo : patientNewsDmoList){

            healthKnowledgeItem = new HealthKnowledgeItem();

            healthKnowledgeItem.setNewId(String.valueOf(patientNewsDmo.getId()));
            healthKnowledgeItem.setType(patientNewsDmo.getType());
            healthKnowledgeItem.setTitle(patientNewsDmo.getTitle());
            healthKnowledgeItem.setSub(patientNewsDmo.getSubTitle());
            healthKnowledgeItem.setSmallImage(patientNewsDmo.getSmallLogo());
            healthKnowledgeItem.setDate(DateUtil.format(patientNewsDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));

            healthKnowledgeItemList.add(healthKnowledgeItem);
        }

        queryHealthKnowledgeListResponse.setList(healthKnowledgeItemList);
        queryHealthKnowledgeListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryHealthKnowledgeListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryHealthKnowledgeListResponse.setTotal(String.valueOf(page.getCount()));

        response.setParameter(queryHealthKnowledgeListResponse);

        return response;
    }
}
