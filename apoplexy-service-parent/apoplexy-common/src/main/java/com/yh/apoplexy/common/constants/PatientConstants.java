/**
 * 
 */
package com.yh.apoplexy.common.constants;

/**
 * 患者常量
 * 
 * @author CC
 *
 */
public class PatientConstants {
	
	/**
	 * 健康宣教资讯状态
	 */
	public class NewsStatus {
		
		/**
		 * 正常
		 */
		public static final String NORMAL = "00";
		
		/**
		 * 删除
		 */
		public static final String DELETE = "90";
	}
	
	/**
	 * 初筛类型
	 */
	public class ScreenType {
		
		/**
		 * 既往史
		 */
		public static final String HISTORY = "0";
		
		/**
		 * 初筛
		 */
		public static final String FIRST_SCREEN = "1";
	}
	
	/**
	 * 筛查规则状态
	 */
	public class ScreenRuleStatus {
		
		/**
		 * 正常
		 */
		public static final String NORMAL = "00";
		
		/**
		 * 删除
		 */
		public static final String DELETE = "90";
	}
	
	/**
	 * 积分事件
	 */
	public class ScoreEvent {

        /**
         * 高危筛查
         */
        public static final String HEALTH_SCREEN = "01";

        /**
         * 体征数据
         */
        public static final String HEALTH_DATA = "02";

        /**
         * 中风急救
         */
        public static final String EMERGENCY = "03";
		
	}
	
	

}
