/**
 * 
 */
package com.yh.apoplexy.common.constants;

/**
 * APP使用的常量类
 * 
 * @author CC
 *
 */
public class AppConstants {
	
	/**
	 * 发送验证码类型
	 */
	public class SendValidateCodeType {
		
		/**
		 * 患者注册
		 */
		public static final String PATIENT_REGISTER = "00";
		
		/**
		 * 患者重置密码
		 */
		public static final String PATIENT_RESET_PWD = "01";

        /**
         * 患者修改个人资料
         */
        public static final String PATIENT_MODIFY_BASE_INFO = "02";
		
		/**
		 * 医生注册
		 */
		public static final String DOCTOR_REGISTER = "10";
		
		/**
		 * 医生重置密码
		 */
		public static final String DOCTOR_RESET_PWD = "11";
	}
	
	/**
	 * 验证验证码是否强制失效
	 */
	public class VerifyValidateCodeInvalid {
		
		/**
		 * 不强制失效
		 */
		public static final String NOT_INVALID = "0";
		
		/**
		 * 强制失效
		 */
		public static final String INVALID = "1";
	}
	
	/**
	 * 客户端类型
	 */
	public class ClientType {
		
		/**
		 * 公用
		 */
		public static final String COMMON = "0";
		
		/**
		 * 医生端
		 */
		public static final String DOCTOR = "1";
		
		/**
		 * 患者端
		 */
		public static final String PATIENT = "2";
	}
	
	/**
	 * 是否需要升级
	 */
	public class NeedUpgrade {
		
		/**
		 * 需要
		 */
		public static final String NEED = "1";
		
		/**
		 * 不需要
		 */
		public static final String NOT_NEED = "0";
	}
	
	/**
	 * 收藏操作
	 */
	public class CollectionOperator {
		
		/**
		 * 添加收藏
		 */
		public static final String COLLECTION = "1";
		
		/**
		 * 取消收藏
		 */
		public static final String CANCEL_COLLECTION = "0";
	}
	
	/**
	 * 点赞操作
	 */
	public class LikeOperator {
		
		/**
		 * 添加点赞
		 */
		public static final String LIKE = "1";
		
		/**
		 * 取消点赞
		 */
		public static final String CANCEL_LIKE = "0";
	}
	

}
