/**
 * 
 */
package com.yh.apoplexy.common.constants;

/**
 * APP响应码常量
 * 
 */
public class APPResponseCodeConstants {

	/**
	 * 消息响应成功
	 */
	public static final String SUCCESS = "0000";

	/**
	 * 参数非法
	 */
	public static final String FAILED_PARAMETER_ERROR = "0001";

	/**
	 * 业务不存在
	 */
	public static final String FAILED_SERVICE_NOT_EXIST = "0099";

	/**
	 * 业务异常
	 */
	public static final String FAILED_SERVICE_ERROR = "9900";

	/**
	 * 签名验证失败
	 */
	public static final String FAILED_SIGN_ERROR = "9998";

	/**
	 * 请求消息格式错误
	 */
	public static final String FAILED_MSG_FORMAT = "9999";

	/**
	 * 发送验证码
	 */
	public class SendValidate {

		/**
		 * 发送成功
		 */
		public static final String SUCCESS = "0000";

		/**
		 * 1小时内发送验证码过于频繁
		 */
		public static final String FAILED_SEND_COUNT = "0001";

		/**
		 * 手机号码有误
		 */
		public static final String FAILED_PHONE_ERROR = "0002";

        /**
         * 手机号码未注册
         */
        public static final String NOT_REGISTER = "0003";

        /**
         * 手机号码已注册
         */
        public static final String ALREADY_REGISTER = "0004";
	}

	/**
	 * 验证验证码
	 */
	public class VerifyValidateCode {

		/**
		 * 验证成功
		 */
		public static final String SUCCESS = "0000";

		/**
		 * 验证码不正确
		 */
		public static final String FAILED_CODE_ERROR = "0001";

		/**
		 * 验证码已失效
		 */
		public static final String FAILED_CODE_UNEFFECT = "0002";
	}

	/**
	 * 图片上传
	 */
	public class ImageUpload {

		/**
		 * 图片上传成功
		 */
		public static final String SUCCESS = "0000";

		/**
		 * 图片上传失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 视频上传
	 */
	public class VideoUpload {

		/**
		 * 视频上传成功
		 */
		public static final String SUCCESS = "0000";

		/**
		 * 视频上传失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 绑定客户端ID
	 */
	public class BindClientId {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 绑定失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 患者注册
	 */
	public class PatientRegister {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 手机验证码错误
		 */
		public static final String FAILED_VALIDATECODE_ERROR = "0001";
		
		/**
		 * 手机号码已存在
		 */
		public static final String FAILED_PHONE_EXIST = "0002";
	}
	
	/**
	 * 患者登录
	 */
	public class PatientLogin {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 用户名或者密码错误
		 */
		public static final String FAILED_USERNAME_PWD_ERROR = "0001";
		
		/**
		 * 账户已被锁定
		 */
		public static final String FAILED_USERNAME_LOCK = "0002";
	}
	
	/**
	 * 患者重置密码
	 */
	public class PatientResetPwd {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 手机验证码错误
		 */
		public static final String FAILED_VALIDATECODE_ERROR = "0001";
		
		/**
		 * 手机验证码已失效
		 */
		public static final String FAILED_VALIDATECODE_UNEFFECT = "0002";
		
		/**
		 * 重置密码失败
		 */
		public static final String FAILED_RESET_PWD = "0003";
	}
	
	/**
	 * 健康筛查
	 */
	public class HealthTest {
		
		/**
		 * 提交成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 提交失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 体征数据
	 */
	public class HealthData {
		
		/**
		 * 提交成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 提交失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * FAST自测表单
	 */
	public class FastTest {
		
		/**
		 * 提交成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 提交失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 修改个人基本资料
	 */
	public class ModifyMemberInfo {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";

        /**
         * 手机验证码错误
         */
        public static final String FAILED_VALIDATE_CODE_ERROR = "0002";

        /**
         * 手机验证码已失效
         */
        public static final String FAILED_VALIDATE_CODE_INVALID = "0003";
	}
	
	/**
	 * 修改登录密码
	 */
	public class ModifyPwd {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 医生注册
	 */
	public class DoctorRegister {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 验证码不正确
		 */
		public static final String FAILED_VALIDATECODE_ERROR = "0001";
		
		/**
		 * 手机号码已存在
		 */
		public static final String FAILED_PHONE_EXIST = "0002";
		
		/**
		 * 医生信息在后台中不存在
		 */
		public static final String FAILED_DOCTOR_NOT_EXIST = "0003";
	}
	
	/**
	 * 医生登录
	 */
	public class DoctorLogin {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 用户名密码错误
		 */
		public static final String FAILED_USER_PWD_ERROR = "0001";
		
		/**
		 * 用户已被锁定
		 */
		public static final String FAILED_USER_LOCK = "0002";
	}
	
	/**
	 * 医生重置密码
	 */
	public class DoctorResetPwd {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 手机验证码错误
		 */
		public static final String FAILED_VALIDATECODE_ERROR = "0001";
		
		/**
		 * 手机验证码已失效
		 */
		public static final String FAILED_VALIDATECODE_UNEFFECT = "0002";
		
		/**
		 * 重置密码失败
		 */
		public static final String FAILED_RESET = "0003";
	}
	
	/**
	 * 新增病例讨论
	 */
	public class AddCase {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 新增失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 添加收藏或者取消收藏操作
	 */
	public class CaseCollection {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 病例讨论
	 */
	public class CaseComment {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 病例讨论回复评论
	 */
	public class CaseReplyComment {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 添加点赞或者取消点赞操作
	 */
	public class CaseLike {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 我的-删除病例讨论
	 */
	public class CaseDelete {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	
	/**
	 * 新增转诊记录
	 */
	public class AddReferralCase {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 接诊
	 */
	public class RecvReferralCase {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 确认转诊
	 */
	public class SureReferral {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 撤销转诊
	 */
	public class CancelReferral {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 评分
	 */
	public class ScoreReferral {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 取消接诊
	 */
	public class CancelMyRecv {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 新增AST病例
	 */
	public class AddAst {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * AST病例讨论
	 */
	public class AstComment {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * AST病例回复讨论
	 */
	public class AstReplyComment {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * AST病例点赞/取消点赞
	 */
	public class AstLikeOperate {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * AST病例收藏/取消收藏
	 */
	public class AstCollectionOperate {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 撤销AST病例
	 */
	public class CancelMyAst {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 修改工作岗位
	 */
	public class ModifyMemberJob {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	/**
	 * 修改登录密码
	 */
	public class ModifyMemberPWD {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	
	/**
	 * APP下载统计
	 */
	public class AppDownload {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}
	
	
	/**
	 * 意见反馈
	 */
	public class IdeaCommit {
		
		/**
		 * 成功
		 */
		public static final String SUCCESS = "0000";
		
		/**
		 * 失败
		 */
		public static final String FAILED = "0001";
	}

    /**
     * 病例是否已收藏
     */
    public class CaseHasCollect {

        /**
         * 已收藏
         */
        public static final String YES = "1";

        /**
         * 未收藏
         */
        public static final String NO = "0";
    }

    /**
     * 病例是否已点赞
     */
    public class CaseHasLike {

        /**
         * 已点赞
         */
        public static final String YES = "1";

        /**
         * 未点赞
         */
        public static final String NO = "0";
    }

    /**
     * 转诊病例是否已评价
     */
    public class ReferralHasScored {

        /**
         * 已评价
         */
        public static final String YES = "1";

        /**
         * 未评价
         */
        public static final String NO = "0";
    }

    /**
     * 转诊病例是否已接诊
     */
    public class ReferralHasReceived {

        /**
         * 已接诊
         */
        public static final String YES = "1";

        /**
         * 未接诊
         */
        public static final String NO = "0";
    }
	
	
	
	
	
    
	
}
