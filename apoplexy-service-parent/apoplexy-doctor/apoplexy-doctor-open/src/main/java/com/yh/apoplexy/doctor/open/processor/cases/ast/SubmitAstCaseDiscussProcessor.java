package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.discuss.service.intf.AstCommentService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.processor.cases.discuss.SubmitCaseCommentProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.SubmitAstCaseDiscussRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.SubmitAstCaseDiscussResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AST病例详情-评论(doc-0038)处理类
 * Created by wunder on 16/9/10 17:38.
 */
public class SubmitAstCaseDiscussProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitAstCaseDiscussProcessor.class);

    @Autowired
    private AstCommentService astCommentService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        SubmitAstCaseDiscussResponse submitAstCaseDiscussResponse = new SubmitAstCaseDiscussResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SubmitAstCaseDiscussRequest submitAstCaseDiscussRequest = JSONObject.parseObject(requestObject.getParameter().toString(),SubmitAstCaseDiscussRequest.class);

        if (null == submitAstCaseDiscussRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(submitAstCaseDiscussRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(submitAstCaseDiscussRequest.getUserId());

        Long recordId = Long.parseLong(submitAstCaseDiscussRequest.getRecordId());

        String discussContent = submitAstCaseDiscussRequest.getDiscussContent();

        AstCasesCommentDmo astCasesCommentDmo = new AstCasesCommentDmo();

        astCasesCommentDmo.setRecordId(recordId);
        astCasesCommentDmo.setFromUserId(userId);
        astCasesCommentDmo.setContent(discussContent);

        Result result = astCommentService.submitCaseComment(astCasesCommentDmo);

        if (!result.isSuccess()){

            submitAstCaseDiscussResponse.setStatus(APPResponseCodeConstants.AstComment.FAILED);
            submitAstCaseDiscussResponse.setMessage("提交失败");

        }else {

            submitAstCaseDiscussResponse.setStatus(APPResponseCodeConstants.AstComment.SUCCESS);
            submitAstCaseDiscussResponse.setMessage("提交成功");

        }

        response.setParameter(submitAstCaseDiscussResponse);

        return response;

    }
}
