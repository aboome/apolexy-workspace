package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.discuss.service.intf.CaseCommentService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.SubmitCaseCommentRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.SubmitCaseCommentResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 病例讨论-评论(doc-0009)处理类
 * Created by wunder on 16/9/7 18:11.
 */
public class SubmitCaseCommentProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitCaseCommentProcessor.class);

    @Autowired
    private CaseCommentService caseCommentService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        SubmitCaseCommentResponse submitCaseCommentResponse = new SubmitCaseCommentResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SubmitCaseCommentRequest submitCaseCommentRequest = JSONObject.parseObject(requestObject.getParameter().toString(),SubmitCaseCommentRequest.class);

        if (null == submitCaseCommentRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(submitCaseCommentRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(submitCaseCommentRequest.getUserId());

        Long recordId = Long.parseLong(submitCaseCommentRequest.getRecordId());

        String discussContent = submitCaseCommentRequest.getDiscussContent();

        CaseCommentDmo caseCommentDmo = new CaseCommentDmo();

        caseCommentDmo.setRecordId(recordId);
        caseCommentDmo.setFromUserId(userId);
        caseCommentDmo.setContent(discussContent);

        Result result = caseCommentService.submitCaseComment(caseCommentDmo);

        if (!result.isSuccess()){

            submitCaseCommentResponse.setStatus(APPResponseCodeConstants.CaseComment.FAILED);
            submitCaseCommentResponse.setMessage("提交失败");

        }else {

            submitCaseCommentResponse.setStatus(APPResponseCodeConstants.CaseComment.SUCCESS);
            submitCaseCommentResponse.setMessage("提交成功");

        }

        response.setParameter(submitCaseCommentResponse);

        return response;
    }
}
