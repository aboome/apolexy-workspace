package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.QueryDoctorScoreInfoRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.QueryDoctorScoreInfoResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人中心-积分详情(doc-0056)处理类
 * Created by wunder on 16/9/6 19:52.
 */
public class QueryDoctorScoreInfoProcessor extends DoctorAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDoctorScoreInfoProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {


        QueryDoctorScoreInfoResponse queryDoctorScoreInfoResponse = new QueryDoctorScoreInfoResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryDoctorScoreInfoRequest queryDoctorScoreInfoRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryDoctorScoreInfoRequest.class);

        if (null == queryDoctorScoreInfoRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryDoctorScoreInfoRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryDoctorScoreInfoRequest.getUserId());

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(userId);
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(doctorMemberCon);

        if (null == doctorMemberDmo){

            throw new AppException(new Result());
        }

        queryDoctorScoreInfoResponse.setTotalScore(String.valueOf(doctorMemberDmo.getScore()));

        response.setParameter(queryDoctorScoreInfoResponse);

        return response;

    }
}
