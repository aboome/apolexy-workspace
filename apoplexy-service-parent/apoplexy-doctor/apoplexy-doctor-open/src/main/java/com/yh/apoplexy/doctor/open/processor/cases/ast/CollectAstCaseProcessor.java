package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCollectionDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseCollectService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.CollectAstCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.CollectAstCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AST病例详情-收藏/取消收藏(doc-0041)处理类
 * Created by wunder on 16/9/10 18:27.
 */
public class CollectAstCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectAstCaseProcessor.class);

    @Autowired
    private AstCaseCollectService astCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        CollectAstCaseResponse collectAstCaseResponse = new CollectAstCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        CollectAstCaseRequest collectAstCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(), CollectAstCaseRequest.class);

        if (null == collectAstCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(collectAstCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(collectAstCaseRequest.getUserId());

        Long recordId = Long.parseLong(collectAstCaseRequest.getRecordId());

        String operator = collectAstCaseRequest.getOperator();

        AstCollectionDmo astCollectionDmo = new AstCollectionDmo();

        astCollectionDmo.setUserId(userId);
        astCollectionDmo.setRecordId(recordId);

        Result result = new Result();

        if (AppConstants.CollectionOperator.COLLECTION.equals(operator)){

            result = astCaseCollectService.submitCollect(astCollectionDmo);

        }else if (AppConstants.CollectionOperator.CANCEL_COLLECTION.equals(operator)){

            result = astCaseCollectService.cancelCollect(astCollectionDmo);

        }else {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        if (!result.isSuccess()){

            collectAstCaseResponse.setStatus(APPResponseCodeConstants.CaseCollection.FAILED);
            collectAstCaseResponse.setMessage("提交失败");

        }else {

            collectAstCaseResponse.setStatus(APPResponseCodeConstants.CaseCollection.SUCCESS);
            collectAstCaseResponse.setMessage("提交成功");

        }

        response.setParameter(collectAstCaseResponse);

        return response;
    }
}
