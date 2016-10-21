package com.yh.apoplexy.common.constants;

/**
 * Properties配置文件
 */
public class PropertiesConstants {

    /**
     * application-env.properties 配置文件
     */
    public static final String APPLICATION_ENV_CONFIG = "properties/application-env";

    public static final String REMOTE_UPDATE_BASE_PATH = "remote.update.base.path";

    public static final String DOCTOR_REGISTER_SMS_TEMPLATE = "doctor.register.sms.template";

    public static final String DOCTOR_RESET_PASSWORD_SMS_TEMPLATE = "doctor.reset.password.sms.template";

    public static final String PATIENT_REGISTER_SMS_TEMPLATE = "patient.register.sms.template";

    public static final String PATIENT_RESET_PASSWORD_SMS_TEMPLATE = "patient.reset.password.sms.template";

    public static final String VALIDATE_CODE_EFFECTIVE_TIME = "validate.code.effective.time";

    public static final String VALIDATE_CODE_INTERVAL_TIME = "validate.code.interval.time";

    public static final String VALIDATE_CODE_INTERVAL_COUNT = "validate.code.interval.count";

    public static final String LOGIN_FAILED_LOCK_TIMES = "login.failed.lock.times";

    public static final String LOGIN_FAILED_LOCK_HOURS = "login.failed.lock.hours";

    public static final String RETRIEVE_PASSWORD_LENGTH = "retrieve.password.length";

    public static final String NEARBY_HOSPITAL_NUMBER = "nearby.hospital.number";

    public static final String BAIDU_MAP_AK = "baidu.map.ak";

    public static final String BAIDU_MAP_SK = "baidu.map.sk";
    
    /**
     * 极光消息推送集成配置项
     */
    public static final String YH_JPUSH_APPKEY = "yh.jpush.appKey";
    
    public static final String YH_JPUSH_MASTERSECRET = "yh.jpush.masterSecret";
    
    /**
     * 导入医院、医生路径配置项
     */
    public static final String YH_HOSPITAL_IMPORT_PATH = "import.hospital.excel.path";
    
    public static final String YH_DOCTOR_IMPORT_PATH = "import.doctor.excel.path";

}
