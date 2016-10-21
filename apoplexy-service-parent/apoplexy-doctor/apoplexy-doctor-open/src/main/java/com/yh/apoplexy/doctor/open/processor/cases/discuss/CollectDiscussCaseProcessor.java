package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCollectionDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseCollectService;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.CollectDiscussCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.CollectDiscussCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 病例讨论-收藏/取消收藏(doc-0008)处理类
 * Created by wunder on 16/9/7 16:28.
 */
public class CollectDiscussCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectDiscussCaseProcessor.class);

    @Autowired
    private DiscussCaseCollectService discussCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        CollectDiscussCaseResponse collectDiscussCaseResponse = new CollectDiscussCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        CollectDiscussCaseRequest collectDiscussCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(), CollectDiscussCaseRequest.class);

        if (null == collectDiscussCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(collectDiscussCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(collectDiscussCaseRequest.getUserId());

        Long recordId = Long.parseLong(collectDiscussCaseRequest.getRecordId());

        String operator = collectDiscussCaseRequest.getOperator();

        CaseCollectionDmo caseCollectionDmo = new CaseCollectionDmo();

        caseCollectionDmo.setUserId(userId);
        caseCollectionDmo.setRecordId(recordId);

        Result result = new Result();

        if (AppConstants.CollectionOperator.COLLECTION.equals(operator)){

            result = discussCaseCollectService.submitCollect(caseCollectionDmo);

        }else if (AppConstants.CollectionOperator.CANCEL_COLLECTION.equals(operator)){

            result = discussCaseCollectService.cancelCollect(caseCollectionDmo);

        }else {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        if (!result.isSuccess()){

            collectDiscussCaseResponse.setStatus(APPResponseCodeConstants.CaseCollection.FAILED);
            collectDiscussCaseResponse.setMessage("提交失败");

        }else {

            collectDiscussCaseResponse.setStatus(APPResponseCodeConstants.CaseCollection.SUCCESS);
            collectDiscussCaseResponse.setMessage("提交成功");

        }

        response.setParameter(collectDiscussCaseResponse);

        return response;
    }
}
