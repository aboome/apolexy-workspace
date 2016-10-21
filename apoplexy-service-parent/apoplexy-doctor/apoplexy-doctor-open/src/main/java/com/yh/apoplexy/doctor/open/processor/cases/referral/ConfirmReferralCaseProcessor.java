package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.QueryIntentDoctorDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralIntentDoctorDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.dto.ConfirmReferralDto;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralReceiveService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.AddReferralCaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.ConfirmReferralCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.AddReferralCaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.ConfirmReferralCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我的申请详情-确认转诊(doc-0026)处理类
 * Created by wunder on 16/9/10 12:44.
 */
public class ConfirmReferralCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmReferralCaseProcessor.class);

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        ConfirmReferralCaseResponse confirmReferralCaseResponse = new ConfirmReferralCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ConfirmReferralCaseRequest confirmReferralCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(), ConfirmReferralCaseRequest.class);

        if (null == confirmReferralCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(confirmReferralCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(confirmReferralCaseRequest.getUserId());

        Long recordId = Long.parseLong(confirmReferralCaseRequest.getRecordId());

        Long toDoctorId = Long.parseLong(confirmReferralCaseRequest.getToDoctorId());

        ConfirmReferralDto confirmReferralDto = new ConfirmReferralDto();

        confirmReferralDto.setUserId(userId);
        confirmReferralDto.setRecordId(recordId);
        confirmReferralDto.setToDoctorId(toDoctorId);

        Result result = referralReceiveService.confirmReferral(confirmReferralDto);

        if (!result.isSuccess()){

            confirmReferralCaseResponse.setStatus(APPResponseCodeConstants.SureReferral.FAILED);
            confirmReferralCaseResponse.setMessage("提交失败");

            response.setParameter(confirmReferralCaseResponse);

            return response;

        }

        QueryIntentDoctorDto con = new QueryIntentDoctorDto();

        con.setRecordId(recordId);

        List<String> receiveStatus = new ArrayList<>();

        receiveStatus.add(Constants.MyRecvStatus.RECV_SUCCESS);
        receiveStatus.add(Constants.MyRecvStatus.RECV_FAILED);

        con.setReceiveStatus(receiveStatus);

        List<ReferralIntentDoctorDto> intentDoctorDtoList = referralReceiveService.queryIntentDoctorList(con);

        if (!CollectionUtils.isEmpty(intentDoctorDtoList)){

            for (ReferralIntentDoctorDto referralIntentDoctorDto: intentDoctorDtoList){

                if (userId.equals(referralIntentDoctorDto.getDoctorId())){

                    //TODO wunder
                    //推送接诊成功消息

                }else {
                    //推送接诊失败消息

                }
            }

        }

        confirmReferralCaseResponse.setStatus(APPResponseCodeConstants.SureReferral.SUCCESS);
        confirmReferralCaseResponse.setMessage("提交成功");

        response.setParameter(confirmReferralCaseResponse);

        return response;
    }
}
