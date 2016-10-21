package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.RegisterProtocolDmo;
import com.yh.apoplexy.base.service.intf.RegisterProtocolService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.QueryProtocolRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.QueryProtocolResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 注册协议接口 (common-0013)处理类
 * Created by wunder on 2016/10/9 19:36.
 */
public class QueryProtocolProcessor extends DoctorAppBaseServiceProcessor {

    @Autowired
    private RegisterProtocolService registerProtocolService;

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryProtocolProcessor.class);

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryProtocolResponse queryProtocolResponse = new QueryProtocolResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(),DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryProtocolRequest queryProtocolRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryProtocolRequest.class);

        //参数错误
        if (null == queryProtocolRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryProtocolRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        String type = queryProtocolRequest.getType();

        RegisterProtocolDmo con = new RegisterProtocolDmo();

        con.setOwner(type);

        RegisterProtocolDmo registerProtocolDmo = registerProtocolService.selectOne(con);

        if (null == registerProtocolDmo){

            response.setParameter(queryProtocolResponse);
            return response;

        }

        queryProtocolResponse.setProtocolContent(registerProtocolDmo.getProtocolContent());

        response.setParameter(queryProtocolResponse);
        return response;
    }
}
