package com.yh.apoplexy.common.constants;

import com.yh.apoplexy.common.utils.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息常量类
 * Created by wunder on 16/9/20 09:57.
 */
public class MessageConstants {

    //病例讨论评论
    public static final String MESSAGE_DISCUSS_COMMENT = "0";

    public static final String MESSAGE_DISCUSS_COMMENT_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_DISCUSS_COMMENT_TITLE);

    public static final String MESSAGE_DISCUSS_COMMENT_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_DISCUSS_COMMENT_CONTENT);

    //病例讨论评论回复
    public static final String MESSAGE_DISCUSS_REPLY = "1";

    public static final String MESSAGE_DISCUSS_REPLY_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_DISCUSS_REPLY_TITLE);

    public static final String MESSAGE_DISCUSS_REPLY_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_DISCUSS_REPLY_CONTENT);

    //病例转诊接单通知
    public static final String MESSAGE_REFERRAL_RECEIVE = "2";

    public static final String MESSAGE_REFERRAL_RECEIVE_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_REFERRAL_RECEIVE_TITLE);

    public static final String MESSAGE_REFERRAL_RECEIVE_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_REFERRAL_RECEIVE_CONTENT);

    //病例转诊转诊成功通知
    public static final String MESSAGE_REFERRAL_CONFIRM = "3";

    public static final String MESSAGE_REFERRAL_CONFIRM_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_REFERRAL_CONFIRM_TITLE);

    public static final String MESSAGE_REFERRAL_CONFIRM_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_REFERRAL_CONFIRM_CONTENT);

    //病例转诊评价通知
    public static final String MESSAGE_REFERRAL_COMMENT = "4";

    public static final String MESSAGE_REFERRAL_COMMENT_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_REFERRAL_COMMENT_TITLE);

    public static final String MESSAGE_REFERRAL_COMMENT_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_REFERRAL_COMMENT_CONTENT);

    //病例转诊接单成功通知
    public static final String MESSAGE_RECEIVE_SUCCESS = "5";

    public static final String MESSAGE_RECEIVE_SUCCESS_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_RECEIVE_SUCCESS_TITLE);

    public static final String MESSAGE_RECEIVE_SUCCESS_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_RECEIVE_SUCCESS_CONTENT);

    //病例转诊接单失败通知
    public static final String MESSAGE_RECEIVE_FAILED = "6";

    public static final String MESSAGE_RECEIVE_FAILED_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_RECEIVE_FAILED_TITLE);

    public static final String MESSAGE_RECEIVE_FAILED_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_RECEIVE_FAILED_CONTENT);

    //AST病例评论
    public static final String MESSAGE_AST_COMMENT = "7";

    public static final String MESSAGE_AST_COMMENT_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_AST_COMMENT_TITLE);

    public static final String MESSAGE_AST_COMMENT_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_AST_COMMENT_CONTENT);

    //AST病例评论回复
    public static final String MESSAGE_AST_REPLY = "8";

    public static final String MESSAGE_AST_REPLY_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_AST_REPLY_TITLE);

    public static final String MESSAGE_AST_REPLY_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_AST_REPLY_CONTENT);

    //新资讯提醒
    public static final String MESSAGE_INFORMATION_KNOWLEDGE = "9";

    public static final String MESSAGE_INFORMATION_KNOWLEDGE_TITLE = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_INFORMATION_KNOWLEDGE_TITLE);

    public static final String MESSAGE_INFORMATION_KNOWLEDGE_CONTENT = PropertiesUtil.getString(MessagePropertiesConstants.APPLICATION_MESSAGE_CONFIG, MessagePropertiesConstants.MESSAGE_INFORMATION_KNOWLEDGE_CONTENT);

    public static final Map<String,String> MESSAGE_TYPE_TITLE_MAP = new HashMap<String, String>(){

        private static final long serialVersionUID = 5717190454923886052L;

        {
            put(MESSAGE_DISCUSS_COMMENT, MESSAGE_DISCUSS_COMMENT_TITLE);
            put(MESSAGE_DISCUSS_REPLY, MESSAGE_DISCUSS_REPLY_TITLE);
            put(MESSAGE_REFERRAL_RECEIVE, MESSAGE_REFERRAL_RECEIVE_TITLE);
            put(MESSAGE_REFERRAL_CONFIRM, MESSAGE_REFERRAL_CONFIRM_TITLE);
            put(MESSAGE_REFERRAL_COMMENT, MESSAGE_REFERRAL_COMMENT_TITLE);
            put(MESSAGE_RECEIVE_SUCCESS, MESSAGE_RECEIVE_SUCCESS_TITLE);
            put(MESSAGE_RECEIVE_FAILED, MESSAGE_RECEIVE_FAILED_TITLE);
            put(MESSAGE_AST_COMMENT, MESSAGE_AST_COMMENT_TITLE);
            put(MESSAGE_AST_REPLY, MESSAGE_AST_REPLY_TITLE);
            put(MESSAGE_INFORMATION_KNOWLEDGE, MESSAGE_INFORMATION_KNOWLEDGE_TITLE);
        }

    };

    public static final Map<String,String> MESSAGE_TYPE_CONTENT_MAP = new HashMap<String, String>(){

        private static final long serialVersionUID = 5717190454923886052L;

        {
            put(MESSAGE_DISCUSS_COMMENT, MESSAGE_DISCUSS_COMMENT_CONTENT);
            put(MESSAGE_DISCUSS_REPLY, MESSAGE_DISCUSS_REPLY_CONTENT);
            put(MESSAGE_REFERRAL_RECEIVE, MESSAGE_REFERRAL_RECEIVE_CONTENT);
            put(MESSAGE_REFERRAL_CONFIRM, MESSAGE_REFERRAL_CONFIRM_CONTENT);
            put(MESSAGE_REFERRAL_COMMENT, MESSAGE_REFERRAL_COMMENT_CONTENT);
            put(MESSAGE_RECEIVE_SUCCESS, MESSAGE_RECEIVE_SUCCESS_CONTENT);
            put(MESSAGE_RECEIVE_FAILED, MESSAGE_RECEIVE_FAILED_CONTENT);
            put(MESSAGE_AST_COMMENT, MESSAGE_AST_COMMENT_CONTENT);
            put(MESSAGE_AST_REPLY, MESSAGE_AST_REPLY_CONTENT);
            put(MESSAGE_INFORMATION_KNOWLEDGE, MESSAGE_INFORMATION_KNOWLEDGE_CONTENT);
        }

    };

    public static final Map<String,String> MESSAGE_TYPE_TYPE_MAP = new HashMap<String, String>(){

        private static final long serialVersionUID = 8774637491313655254L;

        {
            put(MESSAGE_DISCUSS_COMMENT, Constants.DoctorMessageType.DISCUSS_CASE);
            put(MESSAGE_DISCUSS_REPLY, Constants.DoctorMessageType.DISCUSS_CASE);
            put(MESSAGE_REFERRAL_RECEIVE, Constants.DoctorMessageType.REFERRAL);
            put(MESSAGE_REFERRAL_CONFIRM, Constants.DoctorMessageType.REFERRAL);
            put(MESSAGE_REFERRAL_COMMENT, Constants.DoctorMessageType.REFERRAL);
            put(MESSAGE_RECEIVE_SUCCESS, Constants.DoctorMessageType.REFERRAL);
            put(MESSAGE_RECEIVE_FAILED, Constants.DoctorMessageType.REFERRAL);
            put(MESSAGE_AST_COMMENT, Constants.DoctorMessageType.AST);
            put(MESSAGE_AST_REPLY, Constants.DoctorMessageType.AST);
            put(MESSAGE_INFORMATION_KNOWLEDGE, Constants.DoctorMessageType.NEWS);
        }
    };

}
