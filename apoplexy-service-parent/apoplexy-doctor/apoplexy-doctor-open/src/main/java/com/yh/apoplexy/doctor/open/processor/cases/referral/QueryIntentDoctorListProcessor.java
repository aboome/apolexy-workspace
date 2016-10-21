package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.QueryIntentDoctorDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralIntentDoctorDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralReceiveService;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReceiveDoctorItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.QueryIntentDoctorListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.QueryIntentDoctorListResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我的申请详情-查询意向接诊医生列表(doc-0025)处理类
 * Created by wunder on 16/9/10 12:35.
 */
public class QueryIntentDoctorListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryIntentDoctorListProcessor.class);

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Autowired
    private ReferralCaseService referralCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryIntentDoctorListResponse queryIntentDoctorListResponse = new QueryIntentDoctorListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryIntentDoctorListRequest queryIntentDoctorListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryIntentDoctorListRequest.class);

        if (null == queryIntentDoctorListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryIntentDoctorListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryIntentDoctorListRequest.getRecordId());

        ReferralCaseDmo con = new ReferralCaseDmo();

        con.setId(recordId);

        ReferralCaseDmo referralCaseDmo = referralCaseService.find(con);

        if (null  == referralCaseDmo){

            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        QueryIntentDoctorDto queryIntentDoctorDto = new QueryIntentDoctorDto();

        queryIntentDoctorDto.setRecordId(recordId);
        List<String> receiveStatus = new ArrayList<>();

        if (Constants.ReferralCaseStatus.REFERRALED.equals(referralCaseDmo.getStatus())
                ||Constants.ReferralCaseStatus.SCORED.equals(referralCaseDmo.getStatus())){

            receiveStatus.add(Constants.MyRecvStatus.RECV_SUCCESS);

        }else {

            receiveStatus.add(Constants.MyRecvStatus.RECVING);

        }

        queryIntentDoctorDto.setReceiveStatus(receiveStatus);

        List<ReferralIntentDoctorDto> intentDoctorDtoList = referralReceiveService.queryIntentDoctorList(queryIntentDoctorDto);

        List<ReceiveDoctorItem> receiveDoctorItemList = new ArrayList<>();

        ReceiveDoctorItem receiveDoctorItem = null;

        if (!CollectionUtils.isEmpty(intentDoctorDtoList)){

            for (ReferralIntentDoctorDto referralIntentDoctorDto : intentDoctorDtoList) {

                receiveDoctorItem = new ReceiveDoctorItem();

                receiveDoctorItem.setDoctorId(String.valueOf(referralIntentDoctorDto.getDoctorId()));
                receiveDoctorItem.setDoctorName(referralIntentDoctorDto.getDoctorName());
                receiveDoctorItem.setDoctorPhoto(referralIntentDoctorDto.getDoctorPhoto());
                receiveDoctorItem.setHospital(referralIntentDoctorDto.getHospital());
                receiveDoctorItem.setRecvTime(DateUtil.format(referralIntentDoctorDto.getRecvTime(), DateUtil.yyyyMMddHHmmss));

                receiveDoctorItemList.add(receiveDoctorItem);

            }

        }

        queryIntentDoctorListResponse.setList(receiveDoctorItemList);

        response.setParameter(queryIntentDoctorListResponse);

        return response;
    }
}
