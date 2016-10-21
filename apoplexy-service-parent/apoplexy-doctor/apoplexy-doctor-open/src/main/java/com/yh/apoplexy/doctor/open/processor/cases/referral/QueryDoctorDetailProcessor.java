package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.CalStarLevelDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCommentService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCommentItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.QueryDoctorDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.QueryDoctorDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我的申请详情-查询医生详细信息(doc-0061)处理类
 * Created by wunder on 16/9/10 19:07.
 */
public class QueryDoctorDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDoctorDetailProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private ReferralCommentService referralCommentService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryDoctorDetailResponse queryDoctorDetailResponse = new QueryDoctorDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryDoctorDetailRequest queryDoctorDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryDoctorDetailRequest.class);

        if (null == queryDoctorDetailRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryDoctorDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long doctorId = Long.parseLong(queryDoctorDetailRequest.getDoctorId());

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(doctorId);
        doctorMemberCon.setStatus(Constants.DoctorStatus.NORMAL);

        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);

        if (null == doctorMemberDto){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        queryDoctorDetailResponse.setDoctorId(String.valueOf(doctorMemberDto.getId()));
        queryDoctorDetailResponse.setDoctorName(doctorMemberDto.getDoctorName());
        queryDoctorDetailResponse.setPhoto(doctorMemberDto.getPhone());
        queryDoctorDetailResponse.setPhone(doctorMemberDto.getPhone());
        queryDoctorDetailResponse.setSex(doctorMemberDto.getSex());
        queryDoctorDetailResponse.setHospital(doctorMemberDto.getHospital());
        queryDoctorDetailResponse.setHospitalLevel(String.valueOf(doctorMemberDto.getHospitalLevel()));
        queryDoctorDetailResponse.setDepartment(doctorMemberDto.getDepartment());
        queryDoctorDetailResponse.setJob(doctorMemberDto.getJob());
        queryDoctorDetailResponse.setTitle(doctorMemberDto.getTitle());
        queryDoctorDetailResponse.setEffectiveTime(DateUtil.format(doctorMemberDto.getEffectiveTime(),DateUtil.yyyyMMddHHmmss));
        queryDoctorDetailResponse.setReferralCount(String.valueOf(referralCaseService.countMyReferral(doctorId)));

        List<ReferralCommentItem> referralCommentItemList = new ArrayList<>();

        ReferralCommentItem referralCommentItem = null;

        ReferralCommentDmo con = new ReferralCommentDmo();

        con.setUserId(doctorId);

        List<CalStarLevelDto> starLevelDtoList = referralCommentService.queryScoreInfo(con);

        if (!CollectionUtils.isEmpty(starLevelDtoList)){

            for (CalStarLevelDto calStarLevelDto: starLevelDtoList){

                referralCommentItem = new ReferralCommentItem();

                referralCommentItem.setStar(String.valueOf(calStarLevelDto.getScoreSum()));
                referralCommentItem.setCount(String.valueOf(calStarLevelDto.getScoreCount()));

                referralCommentItemList.add(referralCommentItem);

            }

        }

        queryDoctorDetailResponse.setReferralCommentList(referralCommentItemList);

        response.setParameter(queryDoctorDetailResponse);

        return response;

    }
}
