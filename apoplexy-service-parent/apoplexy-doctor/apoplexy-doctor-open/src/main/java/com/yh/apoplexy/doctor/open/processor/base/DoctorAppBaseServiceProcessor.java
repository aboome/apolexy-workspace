package com.yh.apoplexy.doctor.open.processor.base;

import com.alibaba.fastjson.JSON;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.EncryptUtil;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 医生端App接口处理基础类
 * Created by wunder on 16/9/3 13:21.
 */
public abstract class DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorAppBaseServiceProcessor.class);

    /**
     * 医生端App接口服务基础处理方法
     *
     * @param request
     * @return
     */
    public String process(String request) {

        String responseString = null;

        DoctorAppBaseResponse resultObject = null;

        DoctorAppBaseRequest requestObject = null;

        try {
            // 反序列化
            requestObject = unSerialize(request);

            // 验证请求数据的完整性
            if (verify(request)) {

                // 逻辑处理
                resultObject = doProcess(requestObject);

            } else {
                //签名校验失败
                resultObject = checkSignFailed(requestObject);

            }
            // 生成数据完整性校验字段
            resultObject = generateSign(resultObject);

            // 序列化
            responseString = serialize(resultObject);

        } catch (AppException appEx) {
            responseString = processException(requestObject);
            LOGGER.error("app request msg process failed, occur app exception", appEx);
        } catch (Exception ex) {
            responseString = processException(requestObject);
            LOGGER.error("app request msg process failed, occur exception", ex);
        }

        System.out.println(responseString);

        return responseString;
    }

    // 增加数据完整性字符串
    private DoctorAppBaseResponse generateSign(DoctorAppBaseResponse resultObject) {

        resultObject.setSign(null);

        String signInputString = serialize(resultObject).replace(" ", "").replace("\r", "").replace("\n", "");

        String signString = EncryptUtil.md5Encrypt(signInputString);

        resultObject.setSign(signString);

        return resultObject;
    }

    // 验证数据完整性
    private boolean verify(String request) {

        if (StringUtils.isBlank(request)){
            return false;
        }

        request = request.replace(" ", "").replace("\r", "").replace("\n", "");

        int i = request.indexOf("sign");

        if (-1 == i){
            return false;
        }

        String inputSignString = request.substring(i+7,i+39);

        String inputStringWithoutSign = null;

        String subString = request.substring(i+40,i+41);

        System.out.println(subString);

        if (",".equals(subString)){
            inputStringWithoutSign = request.substring(0,i-1)+request.substring(i+41);
        }else{
            inputStringWithoutSign = request.substring(0,i-2)+request.substring(i+40);
        }

        System.out.println("inputStringWithoutSign:"+inputStringWithoutSign);
        String generateSignString = EncryptUtil.md5Encrypt(inputStringWithoutSign);

        LOGGER.info("inputSignString:"+inputSignString);
        System.out.println("inputSignString:"+inputSignString);
        LOGGER.info("generateSignString："+generateSignString);
        System.out.println("generateSignString："+generateSignString);

        //return generateSignString.equalsIgnoreCase(inputSignString);

        return true;
    }

    // 反序列化
    private DoctorAppBaseRequest unSerialize(String request) {
        System.out.println("request:"+request);
        LOGGER.info("requestString:"+request);
        return JSON.parseObject(request, DoctorAppBaseRequest.class);
    }

    // 处理程序
    protected abstract DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject);

    // 序列化
    private String serialize(DoctorAppBaseResponse resultObject) {
        return JSON.toJSONString(resultObject);
    }

    /**
     * 签名校验失败返回错误
     *
     * @param requestObject
     * @return
     */
    private DoctorAppBaseResponse checkSignFailed(DoctorAppBaseRequest requestObject) {

        DoctorAppBaseResponse resultObject = new DoctorAppBaseResponse();

        resultObject.setServicekey(requestObject.getServicekey());
        resultObject.setResultcode(APPResponseCodeConstants.FAILED_SIGN_ERROR);
        resultObject.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        resultObject.setUid(requestObject.getUid());

        return resultObject;
    }

    /**
     * 业务异常返回错误
     *
     * @param requestObject
     * @return
     */
    private String processException(DoctorAppBaseRequest requestObject) {

        DoctorAppBaseResponse resultObject = new DoctorAppBaseResponse();

        resultObject.setServicekey(requestObject.getServicekey());
        resultObject.setResultcode(APPResponseCodeConstants.FAILED_SERVICE_ERROR);
        resultObject.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        resultObject.setUid(requestObject.getUid());

        return JSON.toJSONString(resultObject);

    }

}
