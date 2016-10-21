package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralReceiveService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.CancelReceiveCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.CancelReceiveCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 我的-我的接诊详情-取消接诊(doc-0033)处理类
 * Created by wunder on 16/9/10 14:40.
 */
public class CancelReceiveCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelReceiveCaseProcessor.class);

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        CancelReceiveCaseResponse cancelReceiveCaseResponse = new CancelReceiveCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        CancelReceiveCaseRequest cancelReceiveCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),CancelReceiveCaseRequest.class);

        if (null == cancelReceiveCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(cancelReceiveCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(cancelReceiveCaseRequest.getUserId());

        Long recordId = Long.parseLong(cancelReceiveCaseRequest.getRecordId());

        Result result = referralReceiveService.cancelReceive(recordId,userId);

        if (!result.isSuccess()){

            cancelReceiveCaseResponse.setStatus(APPResponseCodeConstants.CancelMyRecv.FAILED);
            cancelReceiveCaseResponse.setMessage("提交失败");

        }else {

            cancelReceiveCaseResponse.setStatus(APPResponseCodeConstants.CancelMyRecv.SUCCESS);
            cancelReceiveCaseResponse.setMessage("提交成功");

        }

        response.setParameter(cancelReceiveCaseResponse);

        return response;
    }
}
