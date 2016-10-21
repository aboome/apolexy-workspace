/**
 *
 */
package com.yh.apoplexy.common.constants;

/**
 * 医生常量
 *
 * @author CC
 */
public class DoctorConstants {

    /**
     * 资讯分类
     */
    public class NewsClass {

        /**
         * 学术知识
         */
        public static final String KNOWLEDGE = "1";

        /**
         *新闻资讯
         */
        public static final String NEWS = "2";
    }

    /**
     * 资讯状态
     */
    public class NewsStatus {

        /**
         * 正常
         */
        public static final String NORMAL = "00";

        /**
         * 已删除
         */
        public static final String DELETE = "90";
    }

    /**
     * 病例讨论状态
     */
    public class CaseStatus {

        /**
         * 正常
         */
        public static final String NORMAL = "00";

        /**
         * 已删除
         */
        public static final String DELETE = "90";
    }

    /**
     * 评论类型
     */
    public class CommentType {

        /**
         * 父评论
         */
        public static final String PARENT_COMMENT = "0";

        /**
         * 子评论
         */
        public static final String CHILD_COMMENT = "1";
    }

    /**
     * 评论状态
     */
    public class CommentStatus {

        /**
         * 正常
         */
        public static final String NORMAL = "00";

        /**
         * 已删除
         */
        public static final String DELETE = "90";
    }

    /**
     * 病例讨论收藏状态
     */
    public class CaseCollectionStatus {

        /**
         * 正常
         */
        public static final String NORMAL = "00";

        /**
         * 已删除
         */
        public static final String DELETE = "90";
    }

    /**
     * 病例讨论点赞状态
     */
    public class CaseLikeStatus {

        /**
         * 正常
         */
        public static final String NORMAL = "00";

        /**
         * 已删除
         */
        public static final String DELETE = "90";
    }

    /**
     * NIHSS测试来源类别
     */
    public class NihssTestSrcType {

        /**
         * 病例转诊
         */
        public static final String REFERRAL = "1";

        /**
         * AST病例
         */
        public static final String AST = "2";
    }

    /**
     * AST病例类型
     */
    public class AstMrsType {


    }

    /**
     * AST图片类型
     */
    public class AstResourcesType {

        /**
         * CT图片
         */
        public static final String CT = "0";
        /**
         * CTA图片
         */
        public static final String CTA = "1";

        /**
         * CTP图片
         */
        public static final String CTP = "2";
    }

    /**
     * 积分事件枚举
     */
    public class ScoreEvent {

        /**
         * 发布病例讨论
         */
        public static final String POST_DISCUSS = "00";

        /**
         * 回复病例讨论
         */
        public static final String REPLAY_DISCUSS = "01";

        /**
         * 发布转诊
         */
        public static final String POST_REFERRAL = "02";

        /**
         * 接诊成功
         */
        public static final String RECEIVE_SUCCESS = "03";

        /**
         * 转诊成功
         */
        public static final String REFERRAL_SUCCESS = "04";

        /**
         * 转诊评分
         */
        public static final String SCORE_REFERRAL = "05";

        /**
         * 转诊被评分
         */
        public static final String REFERRAL_SCORED = "06";

        /**
         * 发布AST病例
         */
        public static final String POST_AST = "07";

        /**
         * 回复AST病例
         */
        public static final String REPLY_AST = "08";

    }


}
