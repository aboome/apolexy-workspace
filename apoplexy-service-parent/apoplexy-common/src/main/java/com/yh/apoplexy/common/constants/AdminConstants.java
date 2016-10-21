package com.yh.apoplexy.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * admin端常量
 * Created by wunder on 16/9/19 15:01.
 */
public class AdminConstants {

    /**
     * 既往史序号问题常量
     */
    public static final Map<String, String> HISTORY_INDEX_QUESTION_MAP = new HashMap<String, String>() {

        private static final long serialVersionUID = 5066976142030809049L;

        {
            put("1", "脑卒中");
            put("2", "短暂性脑缺血发作（TIA）");

        }
    };

    /**
     * 初筛序号问题常量
     */
    public static final Map<String, String> FIRST_SCREEN_INDEX_QUESTION_MAP = new HashMap<String, String>() {

        private static final long serialVersionUID = 5066976142030809049L;

        {
            put("1", "高血压病史（≥140/90 mmHg），或正在服用降压药；");
            put("2", "房颤或明显的脉搏不齐；");
            put("3", "吸烟；");
            put("4", "血脂异常或未知；");
            put("5", "糖尿病；");
            put("6", "很少进行体育活动（体育锻炼的标准是每周锻炼≥3次、每次≥30分钟、持续时间超过1年。从事农业体力劳动可视为有体育活动）；");
            put("7", "明显超重或肥胖（BMI≥ 26kg/㎡）；");
            put("8", "有卒中家族史；");

        }
    };
    
    /**
     * 中风急救问题列表
     */
    public static final Map<String, String> ZF_INDEX_QUESTION_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = -9154345711117562691L;

		{
            put("1", "是否感觉一侧面部无力或者麻木");
            put("2", "是否感觉一只手没有力气或根本无法抬起");
            put("3","是否说话困难或者言语含糊不清");
           
        }
    };
    
    /**
     * nihss问题列表
     */
    public static final Map<String, String> AST_NIHSS_QUESTION_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = -4382697111652311425L;

		{
            put("1a", "意识水平");
            put("1b", "意识水平提问");
            put("1c", "意识水平指令");
            put("2", "凝视");
            put("3", "视野");
            put("4", "面瘫");
            put("5", "上肢运动");
            put("6", "下肢运动");
            put("7", "共济失调");
            put("8", "感觉");
            put("9", "语言");
            put("10", "构音障碍");
            put("11", "忽视症");
        }
    };
    
    /**
     * ast既往史问题列表
     */
    public static final Map<String, String> AST_HISTORY_INDEX_QUESTION_MAP = new HashMap<String, String>() {


		private static final long serialVersionUID = -8217611816935282446L;

		{
            put("1", "重大头颅外伤史");
            put("2", "脑出血");
            put("3", "脑梗死");
            put("4", "动脉穿刺");
            put("5", "颅内出血史");
            put("6", "颅内或椎管内手术史");
            put("7", "活动性出血");
            put("8", "急性出血倾向");
            put("9", "24小时内接受低分子肝素抗凝");
            put("10", "48小时内接受肝素治疗");
            put("11", "大型外科手术");
            put("12", "严重外伤");
            put("13", "胃肠或泌尿系统出血");
            put("14", "心肌梗死");
            put("15", "糖尿病");
            put("16", "高血压");
            put("17", "房颤及瓣膜病变");
            put("18", "肿瘤");
            put("19", "高脂血症");
        }
    };
    
    /**
     * ast既往用药史
     */
    public static final Map<String, String> AST_HISTORY_MEDICATION_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = -4776958103475800487L;

		{
            put("1", "华法林");
            put("2", "达吡加群");
            put("3", "利伐沙班");
            put("4", "替格瑞洛");
            put("5", "氯吡格雷");
            put("6", "阿司匹林");
            put("7", "其他抗栓药物");
        }
    };
}


