package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.discuss.service.intf.CaseCommentService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.ReplyCaseCommentRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.ReplyCaseCommentResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 病例讨论-评论回复(doc-0010)处理类
 * Created by wunder on 16/9/7 19:17.
 */
public class ReplyCaseCommentProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyCaseCommentProcessor.class);

    @Autowired
    private CaseCommentService caseCommentService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        ReplyCaseCommentResponse replyCaseCommentResponse = new ReplyCaseCommentResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ReplyCaseCommentRequest replyCaseCommentRequest = JSONObject.parseObject(requestObject.getParameter().toString(),ReplyCaseCommentRequest.class);

        if (null == replyCaseCommentRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(replyCaseCommentRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(replyCaseCommentRequest.getRecordId());

        Long fromUserId = Long.parseLong(replyCaseCommentRequest.getFromUserId());

        Long toUserId = Long.parseLong(replyCaseCommentRequest.getToUserId());

        String content = replyCaseCommentRequest.getContent();

        CaseCommentDmo caseCommentDmo = new CaseCommentDmo();

        caseCommentDmo.setFromUserId(fromUserId);
        caseCommentDmo.setToUserId(toUserId);
        caseCommentDmo.setParentId(recordId);
        caseCommentDmo.setContent(content);

        Result result = caseCommentService.replyCaseComment(caseCommentDmo);

        if (!result.isSuccess()){

            replyCaseCommentResponse.setStatus(APPResponseCodeConstants.CaseReplyComment.FAILED);
            replyCaseCommentResponse.setMessage("提交失败");

        }else {

            replyCaseCommentResponse.setStatus(APPResponseCodeConstants.CaseReplyComment.SUCCESS);
            replyCaseCommentResponse.setMessage("提交成功");

        }

        response.setParameter(replyCaseCommentResponse);

        return response;

    }
}
