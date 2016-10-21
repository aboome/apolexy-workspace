package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseLikeDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.CaseLikeService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.LikeDiscussCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.LikeDiscussCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 病例讨论-点赞/取消点赞(doc-0011)处理类
 * Created by wunder on 16/9/7 20:01.
 */
public class LikeDiscussCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LikeDiscussCaseProcessor.class);

    @Autowired
    private CaseLikeService caseLikeService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        LikeDiscussCaseResponse likeDiscussCaseResponse = new LikeDiscussCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        LikeDiscussCaseRequest likeDiscussCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),LikeDiscussCaseRequest.class);

        if (null == likeDiscussCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(likeDiscussCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(likeDiscussCaseRequest.getUserId());

        Long recordId = Long.parseLong(likeDiscussCaseRequest.getRecordId());

        String operator = likeDiscussCaseRequest.getOperator();

        CaseLikeDmo caseLikeDmo = new CaseLikeDmo();

        caseLikeDmo.setUserId(userId);
        caseLikeDmo.setRecordId(recordId);

        Result result = new Result();

        if (AppConstants.LikeOperator.LIKE.equals(operator)){

            result = caseLikeService.submitLike(caseLikeDmo);

        }else if (AppConstants.LikeOperator.CANCEL_LIKE.equals(operator)){

            result = caseLikeService.cancelLike(caseLikeDmo);

        }else {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        if (!result.isSuccess()){

            likeDiscussCaseResponse.setStatus(APPResponseCodeConstants.CaseLike.FAILED);
            likeDiscussCaseResponse.setMessage("提交失败");

        }else {

            likeDiscussCaseResponse.setStatus(APPResponseCodeConstants.CaseLike.SUCCESS);
            likeDiscussCaseResponse.setMessage("提交成功");

        }

        response.setParameter(likeDiscussCaseResponse);

        return response;

    }
}
