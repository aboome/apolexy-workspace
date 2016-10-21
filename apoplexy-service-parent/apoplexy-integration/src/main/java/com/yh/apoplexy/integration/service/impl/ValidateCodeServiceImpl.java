package com.yh.apoplexy.integration.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.ValidateCodeDmo;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.constants.SmsTemplateConstants;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.message.dto.SingleSmsMessage;
import com.yjh.framework.message.helper.SmsSendHelper;
import com.yjh.framework.utils.DateUtil;
import com.yjh.framework.web.util.SpringUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 短信验证码服务实现类
 * Created by wunder on 16/9/3 16:29.
 */
@Service("validateCodeService")
@ServiceTrace
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private CommonDao commonDao;

    String effectiveTime = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.VALIDATE_CODE_EFFECTIVE_TIME);

    String intervalTimeString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.VALIDATE_CODE_INTERVAL_TIME);

    String intervalCountString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.VALIDATE_CODE_INTERVAL_COUNT);

    @Override
    public Result sendValidateCode(ValidateCodeDmo validateCodeDmo, String type) {

        Result result = new Result();

        // 检查参数合法性
        result = checkParameter(validateCodeDmo);

        if (!result.isSuccess()) {
            result.fail("SMS-0000", "发送验证码手机号码错误");
            return result;
        }

        // 检查对应手机号码是否已经在1小时中发送过三次验证码
        result = checkPhoneSendCounts(validateCodeDmo);

        if (!result.isSuccess()) {
            result.fail("SMS-0001", "发送验证码过于频繁");
            return result;
        }

        // 检查之前是否存在未失效的验证码，并将之改为失效
        result = checkExistValidateCodeAndCancel(validateCodeDmo);

        if (!result.isSuccess()) {
            result.fail("SMS-0002", "系统内部异常");
            return result;
        }

        // 生成并持久化验证码
        result = generateValidateCode(validateCodeDmo);

        if (!result.isSuccess()) {
            result.fail("SMS-0003", "系统内部异常");
            return result;
        }

        // 发送手机验证码
        result = sendValidateCodeSms(validateCodeDmo,type);

        if (!result.isSuccess()) {
            result.fail("SMS-0004", "短信发送异常");
            return result;
        }

        return result;
    }

    @Override
    public Result verifyValidateCode(String phone, String code, String invalid) {

        Result result = new Result();

        ValidateCodeDmo con = new ValidateCodeDmo();

        con.setPhone(phone);
        con.setStatus(Constants.ValidateCodeStatus.WAIT_VALIDATE);

        ValidateCodeDmo validateCodeDmo = (ValidateCodeDmo) commonDao.selectOne(con);

        if (null == validateCodeDmo) {
            result.fail("VERIFY-0000", "验证码已失效.");
            return result;
        }

        String existCode = validateCodeDmo.getValidateCode();

        if (!existCode.equals(code)) {
            updateValidateCodeError(validateCodeDmo);
            result.fail("VERIFY-0001", "验证码错误.");
            return result;
        }

        //强制失效
        if (AppConstants.VerifyValidateCodeInvalid.INVALID.equals(invalid)) {
            validateCodeDmo.setStatus(Constants.ValidateCodeStatus.VALIDATE_SUCCESS);
            commonDao.update(validateCodeDmo);
        }

        return result;
    }


    /**
     * 参数检查
     *
     * @param validateCodeDmo
     * @return
     */
    private Result checkParameter(ValidateCodeDmo validateCodeDmo) {

        Result result = new Result();

        String phone = validateCodeDmo.getPhone();

        if (StringUtils.isBlank(phone)) {
            result.fail("", "用户手机号码为空。");
            return result;
        }

        return result;
    }

    /**
     * 生成验证码
     *
     * @param validateCodeDmo
     * @return
     */
    private Result generateValidateCode(ValidateCodeDmo validateCodeDmo) {

        String validateCode = RandomStringUtils.randomNumeric(6);

        validateCodeDmo.setValidateCode(validateCode);
        validateCodeDmo.setCreateTime(DateUtil.getDate());
        validateCodeDmo.setVerifyTimes(0L);
        validateCodeDmo.setStatus(Constants.ValidateCodeStatus.WAIT_VALIDATE);

        int i = commonDao.insert(validateCodeDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    /**
     * 发送短信验证码短信
     *
     * @param validateCodeDmo
     * @return
     */
    private Result sendValidateCodeSms(ValidateCodeDmo validateCodeDmo,String type) {

        Result result = new Result();

        SingleSmsMessage message = new SingleSmsMessage();

        List<String> content = new ArrayList<>();

        content.add(validateCodeDmo.getValidateCode());
        content.add(effectiveTime);

        message.setMessageTo(validateCodeDmo.getPhone());
        message.setTemplateId(SmsTemplateConstants.TYPE_TEMPLATE_MAP.get(type));
        message.setContent(content);

        SmsSendHelper smsSendHelper = new SmsSendHelper();

        if (AppConstants.SendValidateCodeType.DOCTOR_REGISTER.equals(type)||AppConstants.SendValidateCodeType.DOCTOR_RESET_PWD.equals(type)){
            smsSendHelper= (SmsSendHelper)SpringUtil.getBean("doctorSmsManager");
        }else if (AppConstants.SendValidateCodeType.PATIENT_REGISTER.equals(type)||AppConstants.SendValidateCodeType.PATIENT_RESET_PWD.equals(type)||AppConstants.SendValidateCodeType.PATIENT_MODIFY_BASE_INFO.equals(type)){
            smsSendHelper= (SmsSendHelper) SpringUtil.getBean("patientSmsManager");
        }else {
            result.fail("SVC-0001", "短信发送异常,类型参数异常");
        }

        try {
            smsSendHelper.sendSingleSms(message);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("SVC-0002", "短信发送异常");
        }

        return result;
    }

    /**
     * 检查验证码是否存在
     *
     * @param validateCodeDmo
     * @return
     */
    private Result checkExistValidateCodeAndCancel(ValidateCodeDmo validateCodeDmo) {

        Result result = new Result();

        ValidateCodeDmo con = new ValidateCodeDmo();

        con.setPhone(validateCodeDmo.getPhone());
        con.setStatus(Constants.ValidateCodeStatus.WAIT_VALIDATE);

        List<ValidateCodeDmo> validateCodeDmoList = commonDao.selectList(con);

        if (CollectionUtils.isEmpty(validateCodeDmoList)) {
            return result;
        }

        for (ValidateCodeDmo existVerifyCode : validateCodeDmoList) {
            existVerifyCode.setStatus(Constants.ValidateCodeStatus.VALIDATE_FAILED);
            commonDao.update(existVerifyCode);
        }

        return result;
    }

    /**
     * 检查短信验证码发送次数
     *
     * @param validateCodeDmo
     * @return
     */
    private Result checkPhoneSendCounts(ValidateCodeDmo validateCodeDmo) {

        Result result = new Result();

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("phone",validateCodeDmo.getPhone());
        conMap.put("intervalTime",intervalTimeString);

        List<ValidateCodeDmo> validateCodeList = commonDao.selectList("ValidateCodeMapper.queryValidateCode", conMap);

        if (CollectionUtils.isEmpty(validateCodeList)) {
            return result;
        }

        long count = validateCodeList.size();

        Long intervalCount = Long.parseLong(intervalCountString);

        if (count >= intervalCount) {
            result.fail("", "发送验证码过于频繁。");
            return result;
        }

        return result;
    }

    /**
     * 验证码校验失败操作
     *
     * @param validateCodeDmo
     */
    private void updateValidateCodeError(ValidateCodeDmo validateCodeDmo) {

        if (validateCodeDmo.getVerifyTimes() + 1 >= 3) {
            validateCodeDmo.setStatus(Constants.ValidateCodeStatus.VALIDATE_FAILED);
        }

        validateCodeDmo.setVerifyTimes(validateCodeDmo.getVerifyTimes() + 1);

        commonDao.update(validateCodeDmo);

    }

}
