package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.QueryDoctorBaseInfoRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.QueryDoctorBaseInfoResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人中心-查询个人基础资料(doc-0053)处理类
 * Created by wunder on 16/9/6 19:07.
 */
public class QueryDoctorBaseInfoProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDoctorBaseInfoProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryDoctorBaseInfoResponse queryDoctorBaseInfoResponse = new QueryDoctorBaseInfoResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryDoctorBaseInfoRequest queryDoctorBaseInfoRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryDoctorBaseInfoRequest.class);

        if (null == queryDoctorBaseInfoRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryDoctorBaseInfoRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryDoctorBaseInfoRequest.getUserId());

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(userId);
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);

        if (null == doctorMemberDto){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        queryDoctorBaseInfoResponse.setUserId(String.valueOf(doctorMemberDto.getId()));
        queryDoctorBaseInfoResponse.setUserName(doctorMemberDto.getDoctorName());
        queryDoctorBaseInfoResponse.setPhone(doctorMemberDto.getPhone());
        queryDoctorBaseInfoResponse.setSex(doctorMemberDto.getSex());
        queryDoctorBaseInfoResponse.setHospital(doctorMemberDto.getHospital());
        queryDoctorBaseInfoResponse.setHospitalLevel(String.valueOf(doctorMemberDto.getHospitalLevel()));
        queryDoctorBaseInfoResponse.setDepartment(doctorMemberDto.getDepartment());
        queryDoctorBaseInfoResponse.setTitle(doctorMemberDto.getTitle());

        response.setParameter(queryDoctorBaseInfoResponse);

        return response;

    }
}
