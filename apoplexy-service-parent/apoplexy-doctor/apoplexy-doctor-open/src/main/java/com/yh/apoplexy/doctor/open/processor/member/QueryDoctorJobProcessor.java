package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.QueryDoctorJobRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.QueryDoctorJobResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人中心-查询工作岗位信息(doc-0054)处理类
 * Created by wunder on 16/9/6 19:49.
 */
public class QueryDoctorJobProcessor extends DoctorAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDoctorJobProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryDoctorJobResponse queryDoctorJobResponse = new QueryDoctorJobResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryDoctorJobRequest queryDoctorJobRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryDoctorJobRequest.class);

        if (null == queryDoctorJobRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryDoctorJobRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryDoctorJobRequest.getUserId());

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(userId);
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(doctorMemberCon);

        if (null == doctorMemberDmo){

            throw new AppException(new Result());
        }

        queryDoctorJobResponse.setPost(doctorMemberDmo.getJob());
        queryDoctorJobResponse.setEffectiveTime(DateUtil.format(doctorMemberDmo.getEffectiveTime(),DateUtil.yyyyMMddHHmmss));

        response.setParameter(queryDoctorJobResponse);

        return response;

    }
}
