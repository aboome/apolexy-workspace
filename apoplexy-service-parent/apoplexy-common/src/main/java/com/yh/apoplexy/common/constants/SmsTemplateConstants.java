package com.yh.apoplexy.common.constants;

import com.yh.apoplexy.common.utils.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证码模板常量类
 * Created by wunder on 16/9/6 22:47.
 */
public class SmsTemplateConstants {

    public static final String DOCTOR_REGISTER = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.DOCTOR_REGISTER_SMS_TEMPLATE);

    public static final String DOCTOR_RESET_PWD = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.DOCTOR_RESET_PASSWORD_SMS_TEMPLATE);

    public static final String PATIENT_REGISTER = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.PATIENT_REGISTER_SMS_TEMPLATE);

    public static final String PATIENT_RESET_PWD = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.PATIENT_RESET_PASSWORD_SMS_TEMPLATE);

    public static final Map<String, String> TYPE_TEMPLATE_MAP = new HashMap<String, String>() {

        private static final long serialVersionUID = 7077435493298893498L;

        {
            put(AppConstants.SendValidateCodeType.PATIENT_REGISTER, PATIENT_REGISTER);
            put(AppConstants.SendValidateCodeType.PATIENT_RESET_PWD, PATIENT_RESET_PWD);
            put(AppConstants.SendValidateCodeType.DOCTOR_REGISTER, DOCTOR_REGISTER);
            put(AppConstants.SendValidateCodeType.DOCTOR_RESET_PWD, DOCTOR_RESET_PWD);
            put(AppConstants.SendValidateCodeType.PATIENT_MODIFY_BASE_INFO,PATIENT_REGISTER);
        }
    };
}
