package com.yh.apoplexy.common.constants;

import com.yh.apoplexy.common.utils.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 积分常量类
 * Created by wunder on 16/9/20 11:18.
 */
public class ScoreConstants {

    //发布病例讨论
    public static final String SCORE_DOCTOR_DISCUSS_CASE = "0";

    public static final String SCORE_DOCTOR_DISCUSS_CASE_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_DISCUSS_CASE);

    //回复病例讨论
    public static final String SCORE_DOCTOR_DISCUSS_COMMENT = "1";

    public static final String SCORE_DOCTOR_DISCUSS_COMMENT_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_DISCUSS_COMMENT);

    //发布转诊病例
    public static final String SCORE_DOCTOR_REFERRAL_CASE = "2";

    public static final String SCORE_DOCTOR_REFERRAL_CASE_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_REFERRAL_CASE);

    //确认接诊
    public static final String SCORE_DOCTOR_REFERRAL_CONFIRM = "3";

    public static final String SCORE_DOCTOR_REFERRAL_CONFIRM_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_REFERRAL_CONFIRM);

    //接诊成功
    public static final String SCORE_DOCTOR_RECEIVE_SUCCESS = "4";

    public static final String SCORE_DOCTOR_RECEIVE_SUCCESS_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_RECEIVE_SUCCESS);

    //转诊被评价
    public static final String SCORE_DOCTOR_REFERRAL_COMMENT = "5";

    public static final String SCORE_DOCTOR_REFERRAL_COMMENT_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_REFERRAL_COMMENT);

    //评价转诊
    public static final String SCORE_DOCTOR_RECEIVE_COMMENT = "6";

    public static final String SCORE_DOCTOR_RECEIVE_COMMENT_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_RECEIVE_COMMENT);

    //发布AST病例
    public static final String SCORE_DOCTOR_AST_CASE = "7";

    public static final String SCORE_DOCTOR_AST_CASE_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_AST_CASE);

    //回复AST病例
    public static final String SCORE_DOCTOR_AST_COMMENT = "8";

    public static final String SCORE_DOCTOR_AST_COMMENT_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_DOCTOR_AST_COMMENT);

    //首次填写高危筛查
    public static final String SCORE_PATIENT_HEALTH_SCREEN = "9";

    public static final String SCORE_PATIENT_HEALTH_SCREEN_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_PATIENT_HEALTH_SCREEN);

    //每日首次填写体征数据
    public static final String SCORE_PATIENT_HEALTH_DATA = "10";

    public static final String SCORE_PATIENT_HEALTH_DATA_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_PATIENT_HEALTH_DATA);

    //首次填写中风急救
    public static final String SCORE_PATIENT_APOPLEXY_EMERGENCY = "11";

    public static final String SCORE_PATIENT_APOPLEXY_EMERGENCY_EVENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.SCORE_PATIENT_APOPLEXY_EMERGENCY);


    public static final Map<String,String> SCORE_TYPE_EVENT_MAP = new HashMap<String, String>(){

        private static final long serialVersionUID = 5717190454923886052L;

        {
            this.put(SCORE_DOCTOR_DISCUSS_CASE, SCORE_DOCTOR_DISCUSS_CASE_EVENT);
            this.put(SCORE_DOCTOR_DISCUSS_COMMENT, SCORE_DOCTOR_DISCUSS_COMMENT_EVENT);
            this.put(SCORE_DOCTOR_REFERRAL_CASE, SCORE_DOCTOR_REFERRAL_CASE_EVENT);
            this.put(SCORE_DOCTOR_REFERRAL_CONFIRM, SCORE_DOCTOR_REFERRAL_CONFIRM_EVENT);
            this.put(SCORE_DOCTOR_RECEIVE_SUCCESS, SCORE_DOCTOR_RECEIVE_SUCCESS_EVENT);
            this.put(SCORE_DOCTOR_REFERRAL_COMMENT, SCORE_DOCTOR_REFERRAL_COMMENT_EVENT);
            this.put(SCORE_DOCTOR_RECEIVE_COMMENT, SCORE_DOCTOR_RECEIVE_COMMENT_EVENT);
            this.put(SCORE_DOCTOR_AST_CASE, SCORE_DOCTOR_AST_CASE_EVENT);
            this.put(SCORE_DOCTOR_AST_COMMENT, SCORE_DOCTOR_AST_COMMENT_EVENT);
            this.put(SCORE_PATIENT_HEALTH_SCREEN, SCORE_PATIENT_HEALTH_SCREEN_EVENT);
            this.put(SCORE_PATIENT_HEALTH_DATA, SCORE_PATIENT_HEALTH_DATA_EVENT);
            this.put(SCORE_PATIENT_APOPLEXY_EMERGENCY, SCORE_PATIENT_APOPLEXY_EMERGENCY_EVENT);
        }

    };
}
