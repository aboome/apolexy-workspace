package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstLikeDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstLikeService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.LikeAstCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.LikeAstCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AST病例详情-点赞/取消点赞(doc-0040)处理类
 * Created by wunder on 16/9/10 17:49.
 */
public class LikeAstCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LikeAstCaseProcessor.class);

    @Autowired
    private AstLikeService astLikeService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        LikeAstCaseResponse likeAstCaseResponse = new LikeAstCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        LikeAstCaseRequest likeAstCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),LikeAstCaseRequest.class);

        if (null == likeAstCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(likeAstCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(likeAstCaseRequest.getUserId());

        Long recordId = Long.parseLong(likeAstCaseRequest.getRecordId());

        String operator = likeAstCaseRequest.getOperator();

        AstLikeDmo astLikeDmo = new AstLikeDmo();

        astLikeDmo.setUserId(userId);
        astLikeDmo.setRecordId(recordId);

        Result result = new Result();

        if (AppConstants.LikeOperator.LIKE.equals(operator)){

            result = astLikeService.submitLike(astLikeDmo);

        }else if (AppConstants.LikeOperator.CANCEL_LIKE.equals(operator)){

            result = astLikeService.cancelLike(astLikeDmo);

        }else {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        if (!result.isSuccess()){

            likeAstCaseResponse.setStatus(APPResponseCodeConstants.CaseLike.FAILED);
            likeAstCaseResponse.setMessage("提交失败");

        }else {

            likeAstCaseResponse.setStatus(APPResponseCodeConstants.CaseLike.SUCCESS);
            likeAstCaseResponse.setMessage("提交成功");

        }

        response.setParameter(likeAstCaseResponse);

        return response;

    }
}
