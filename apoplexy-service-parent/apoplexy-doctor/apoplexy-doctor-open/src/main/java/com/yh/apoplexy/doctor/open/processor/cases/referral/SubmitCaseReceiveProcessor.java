package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralReceiveDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralReceiveService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.SubmitCaseReceiveRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.SubmitCaseReceiveResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 转诊病例详情-接诊(doc-0022)处理类
 * Created by wunder on 16/9/9 20:56.
 */
public class SubmitCaseReceiveProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitCaseReceiveProcessor.class);

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        SubmitCaseReceiveResponse submitCaseReceiveResponse = new SubmitCaseReceiveResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SubmitCaseReceiveRequest submitCaseReceiveRequest = JSONObject.parseObject(requestObject.getParameter().toString(),SubmitCaseReceiveRequest.class);

        if (null == submitCaseReceiveRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(submitCaseReceiveRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }


        Long userId = Long.parseLong(submitCaseReceiveRequest.getUserId());

        Long recordId = Long.parseLong(submitCaseReceiveRequest.getRecordId());

        ReferralReceiveDmo referralReceiveDmo = new ReferralReceiveDmo();

        referralReceiveDmo.setUserId(userId);

        referralReceiveDmo.setRecordId(recordId);

        Result result = referralReceiveService.submitReferralReceive(referralReceiveDmo);

        if (!result.isSuccess()){

            submitCaseReceiveResponse.setStatus(APPResponseCodeConstants.RecvReferralCase.FAILED);
            submitCaseReceiveResponse.setMessage("提交失败");

        }else {

            submitCaseReceiveResponse.setStatus(APPResponseCodeConstants.RecvReferralCase.SUCCESS);
            submitCaseReceiveResponse.setMessage("提交成功");

        }

        response.setParameter(submitCaseReceiveResponse);

        return response;
    }
}
