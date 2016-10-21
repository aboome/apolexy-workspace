package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.discuss.service.intf.AstCommentService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.ReplyAstCaseDiscussRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.ReplyAstCaseDiscussResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AST病例详情-回复评论(doc-0039)处理类
 * Created by wunder on 16/9/10 17:44.
 */
public class ReplyAstCaseDiscussProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyAstCaseDiscussProcessor.class);

    @Autowired
    private AstCommentService astCommentService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        ReplyAstCaseDiscussResponse replyAstCaseDiscussResponse = new ReplyAstCaseDiscussResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ReplyAstCaseDiscussRequest replyAstCaseDiscussRequest = JSONObject.parseObject(requestObject.getParameter().toString(),ReplyAstCaseDiscussRequest.class);

        if (null == replyAstCaseDiscussRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(replyAstCaseDiscussRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(replyAstCaseDiscussRequest.getRecordId());

        Long fromUserId = Long.parseLong(replyAstCaseDiscussRequest.getFromUserId());

        Long toUserId = Long.parseLong(replyAstCaseDiscussRequest.getToUserId());

        String content = replyAstCaseDiscussRequest.getContent();

        AstCasesCommentDmo astCasesCommentDmo = new AstCasesCommentDmo();

        astCasesCommentDmo.setFromUserId(fromUserId);
        astCasesCommentDmo.setToUserId(toUserId);
        astCasesCommentDmo.setParentId(recordId);
        astCasesCommentDmo.setContent(content);

        Result result = astCommentService.replyCaseComment(astCasesCommentDmo);

        if (!result.isSuccess()){

            replyAstCaseDiscussResponse.setStatus(APPResponseCodeConstants.AstReplyComment.FAILED);
            replyAstCaseDiscussResponse.setMessage("提交失败");

        }else {

            replyAstCaseDiscussResponse.setStatus(APPResponseCodeConstants.AstReplyComment.SUCCESS);
            replyAstCaseDiscussResponse.setMessage("提交成功");

        }

        response.setParameter(replyAstCaseDiscussResponse);

        return response;

    }
}
