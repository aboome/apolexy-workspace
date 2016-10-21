package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.CancelReferralCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.CancelReferralCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 我的-我的申请详情-撤销转诊(doc-0027)处理类
 * Created by wunder on 16/9/10 13:50.
 */
public class CancelReferralCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelReferralCaseProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        CancelReferralCaseResponse cancelReferralCaseResponse = new CancelReferralCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        CancelReferralCaseRequest cancelReferralCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),CancelReferralCaseRequest.class);

        if (null == cancelReferralCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(cancelReferralCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(cancelReferralCaseRequest.getUserId());

        Long recordId = Long.parseLong(cancelReferralCaseRequest.getRecordId());

        Result result = referralCaseService.cancelCase(recordId,userId);

        if (!result.isSuccess()){

            cancelReferralCaseResponse.setStatus(APPResponseCodeConstants.CancelReferral.FAILED);
            cancelReferralCaseResponse.setMessage("提交失败");

        }else {

            cancelReferralCaseResponse.setStatus(APPResponseCodeConstants.CancelReferral.SUCCESS);
            cancelReferralCaseResponse.setMessage("提交成功");

        }

        response.setParameter(cancelReferralCaseResponse);

        return response;
    }
}
