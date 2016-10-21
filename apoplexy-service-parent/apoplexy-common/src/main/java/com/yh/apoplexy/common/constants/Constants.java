/**
 * 
 */
package com.yh.apoplexy.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用常量类
 * 
 * @author CC
 *
 */
public class Constants {
	
	/**
	 * 性别
	 */
	public class Sex {
		
		/**
		 * 男
		 */
		public static final String MAN = "0";
		
		/**
		 * 女
		 */
		public static final String WOMAN = "1";
	}
	
	/**
	 * 问题答案
	 */
	public class QuestionAnswer {
		
		/**
		 * 是
		 */
		public static final String YES = "1";
		
		/**
		 * 否
		 */
		public static final String NO = "0";
	}
	
	/**
	 * 发布范围
	 */
	public class Range {
		
		/**
		 * 全部
		 */
		public static final String ALL = "0";
		
		/**
		 * “1+1+1”范围
		 */
		public static final String ONE_BY_ONE_BY_ONE = "1";
	}
	
	/**
	 * 病例讨论主题类型
	 */
	public class DiscussType {
		
		/**
		 * 求诊断
		 */
		public static final String NEED_CHECK = "0";
		
		/**
		 * 求治疗
		 */
		public static final String NEED_TREATMENT = "1";
		
		/**
		 * 求医生
		 */
		public static final String NEED_DOCTOR = "2";
	}

    public class DiscussTypeDesc {

        /**
         * 求诊断
         */
        public static final String NEED_CHECK = "求诊断";

        /**
         * 求治疗
         */
        public static final String NEED_TREATMENT = "求治疗";

        /**
         * 求医生
         */
        public static final String NEED_DOCTOR = "求医生";
    }

    /**
	 * 病例转诊主题类型
	 */
	public class ReferralType {

		/**
		 * 求诊断
		 */
		public static final String NEED_CHECK = "0";

		/**
		 * 求治疗
		 */
		public static final String NEED_TREATMENT = "1";

		/**
		 * 求医生
		 */
		public static final String NEED_DOCTOR = "2";
	}

    public class ReferralTypeDesc {

        /**
         * 求诊断
         */
        public static final String NEED_CHECK = "求诊断";

        /**
         * 求治疗
         */
        public static final String NEED_TREATMENT = "求治疗";

        /**
         * 求医生
         */
        public static final String NEED_DOCTOR = "求医生";
    }

	/**
	 * 转诊病例状态
	 */
	public class ReferralCaseStatus {
		
		/**
		 * 未转诊
		 */
		public static final String NOT_REFERRAL = "0";
		
		/**
		 * 已转诊
		 */
		public static final String REFERRALED = "1";
		
		/**
		 * 全部
		 */
		public static final String ALL = "2";

        /**
         * 取消
         */
        public static final String CANCEL = "3";

        /**
         * 已评价
         */
        public static final String SCORED = "4";
    }

    /**
	 * 转诊病例类型
	 */
	public class ReferralCaseType {
		
		/**
		 * 急性
		 */
		public static final String ACUTE = "0";
		
		/**
		 * 高危
		 */
		public static final String HIGH_RISK = "1";
	}
	
	/**
	 * 意识
	 */
	public class Consciousness {
		
		/**
		 * 清楚
		 */
		public static final String CLEAR = "0";
		
		/**
		 * 不清楚
		 */
		public static final String NOT_CLEAR = "1";
	}
	
	/**
	 * 皮肤损伤类型
	 */
	public class SkinType {
		
		/**
		 * 破损
		 */
		public static final String DAMAGE = "0";
	}
	
	/**
	 * 接诊状态
	 */
	public class MyRecvStatus {
		
		/**
		 * 意向接诊
		 */
		public static final String RECVING = "0";
		
		/**
		 * 接诊成功
		 */
		public static final String RECV_SUCCESS = "1";
		
		/**
		 * 接诊失败
		 */
		public static final String RECV_FAILED = "2";

        /**
         * 取消接诊
         */
        public static final String CANCEL = "3";
	}
	
	/**
	 * 急诊诊断结果
	 */
	public class EmergencyResult {
		
		/**
		 * 脑梗死
		 */
		public static final String CEREBRAL_INFARCTION = "0";
		
		/**
		 * 短暂性脑出血发作
		 */
		public static final String TRANSIENT_ISCHEMIC = "1";
		
		/**
		 * 脑出血
		 */
		public static final String CEREBRAL_HEMORRHAGE = "2";
		
		/**
		 * 其他
		 */
		public static final String OTHER = "3";
	}
	
	/**
	 * 否的原因
	 */
	public class NegativeReason {
		
		/**
		 * 超溶栓治疗窗
		 */
		public static final String OUT_THERAPY_WINDOW = "0";
		
		/**
		 * 患者放弃治疗
		 */
		public static final String PATIENT_NOT_TREATMENT = "1";
		
		/**
		 * 其他
		 */
		public static final String OTHER = "2";
	}
	
	/**
	 * 后续去向
	 */
	public class FollowGo {
		
		/**
		 * 未入院
		 */
		public static final String NOT_GO_HOSPITAL = "0";
		
		/**
		 * 入院
		 */
		public static final String GO_HOSPITAL = "1";
	}
	
	/**
	 * 新闻、资讯、宣教等内容类型
	 */
	public class NewsType {
		
		/**
		 * 图文
		 */
		public static final String IMAGE_TEXT = "1";
		
		/**
		 * 视频
		 */
		public static final String VIDEO = "2";
			
		/**
		 * PDF
		 */
		public static final String PDF = "3";
	}
	
	/**
	 * 医院级别
	 */
	public class HospitalLevel {
		
		/**
		 * 省级
		 */
		public static final String PROVINCE_HOSPITAL = "0";
		
		/**
		 * 市级
		 */
		public static final String CITY_HOSPITAL = "1";
		
		/**
		 * 区县级
		 */
		public static final String COUNTY_HOSPITAL = "2";
		
		/**
		 * 社区医院
		 */
		public static final String TOWNSHIP_HOSPITAL = "3";
	}
	
	
	/**
	 * 医生级别
	 */
	public class DoctorLevel {
		
		/**
		 * 省级
		 */
		public static final String PROVINCE_DOCTOR = "0";
		
		/**
		 * 市级
		 */
		public static final String CITY_DOCTOR = "1";
		
		/**
		 * 区县级
		 */
		public static final String COUNTY_DOCTOR = "2";
		
		/**
		 * 社区级
		 */
		public static final String TOWNSHIP_DOCTOR = "3";
	}
	
	/**
	 * 医生消息中心消息类型
	 */
	public class DoctorMessageType {
		
		/**
		 * 全部
		 */
		public static final String ALL = "0";
		
		/**
		 * 病例讨论
		 */
		public static final String DISCUSS_CASE = "1";
		
		/**
		 * 病例转诊
		 */
		public static final String REFERRAL = "2";

		/**
		 * 资讯
		 */
		public static final String NEWS = "3";
		
		/**
		 * 系统
		 */
		public static final String SYSTEM = "4";

        /**
         * AST病例
         */
        public static final String AST = "5";
	}
	
	/**
	 * 验证码表状态
	 */
    public class ValidateCodeStatus {
    	
    	/**
    	 * 待验证
    	 */
    	public static final String WAIT_VALIDATE = "00";
    	
    	/**
    	 * 验证成功
    	 */
    	public static final String VALIDATE_SUCCESS = "10";
    	
    	/**
    	 * 验证失败
    	 */
    	public static final String VALIDATE_FAILED = "90";
    }
    
    /**
     * 医院状态
     */
    public class HospitalStatus {
    	
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
     * 医生状态
     */
    public class DoctorStatus {
    	
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
     * 资源表状态
     */
    public class ResourcesStatus {
    	
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
     * 资源表类型
     */
    public class ResourcesType {
    	
    	/**
    	 * 图片
    	 */
    	public static final String IMAGE = "0";
    	
    	/**
    	 * 视频
    	 */
    	public static final String VIDEO = "1";
    	
    	/**
    	 * PDF
    	 */
    	public static final String PDF = "2";
    }
    
    /**
     * 归属
     */
    public class OwnType {
    	
    	/**
    	 * 公用
    	 */
    	public static final String COMMON = "0";
    	
    	/**
    	 * 医生
    	 */
    	public static final String DOCTOR = "1";
    	
    	/**
    	 * 患者
    	 */
    	public static final String PATIENT = "2";
    }
    
    /**
     * 首页推荐位状态
     */
    public class BannerStatus {
    	
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
     * 开机动画状态
     */
    public class StartPictureStatus {
    	
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
     * 消息推送状态
     */
    public class PushMessageStatus {
    	
    	/**
    	 * 发送成功
    	 */
    	public static final String NORMAL = "00";
    	
    	/**
    	 * 发送失败
    	 */
    	public static final String DELETE = "90";
    }
    
    /**
     * 管理员账号状态
     */
    public class AdminStatus {
    	
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
     * 角色状态
     */
    public class RoleStatus {
    	
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
     * 菜单状态
     */
    public class MenuStatus {
    	
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
     * 菜单等级
     */
    public class MenuLevel {
    	
    	/**
    	 * 一级
    	 */
    	public static final String FIRST = "1";
    	
    	/**
    	 * 二级
    	 */
    	public static final String SECOND = "2";
    	
    	/**
    	 * 三级
    	 */
    	public static final String THIRD = "3";
    }
    
    /**
     * 操作日志模块
     */
    public class OperatorModel {
    	
    	
    }
    
    /**
     * 操作日志枚举
     */
    public class OperatorEnum {
    	
    	
    }
    
    /**
     * 系统升级状态
     */
    public class SystemVersionStatus {
    	
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
	 * 会员状态
	 */
	public class MemberStatus {
		
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
	 * 登录状态
	 */
	public class LoginStatus {
		
		/**
		 * 正常
		 */
		public static final String NORMAL = "00";
		
		/**
		 * 锁定
		 */
		public static final String LOCK = "10";
		
		/**
		 * 删除
		 */
		public static final String DELETE = "90";
	}
	
	/**
	 * 客户端类型
	 */
	public class ClientType {
		
		/**
		 * android
		 */
		public static final String ANDROID = "1";
		
		/**
		 * IOS
		 */
		public static final String IOS = "2";
	}
	
	

	

    public static final Map<String,String> DISCUSS_TYPE_NAME_MAP = new HashMap<String, String>(){

        private static final long serialVersionUID = 5717190454923886052L;

        {
            put(DiscussType.NEED_CHECK, DiscussTypeDesc.NEED_CHECK);
            put(DiscussType.NEED_DOCTOR,DiscussTypeDesc.NEED_DOCTOR);
            put(DiscussType.NEED_TREATMENT, DiscussTypeDesc.NEED_TREATMENT);
        }

    };

    public static final Map<String,String> REFERRAL_TYPE_NAME_MAP = new HashMap<String, String>(){

		private static final long serialVersionUID = -9169364185769184705L;

		{
            put(ReferralType.NEED_CHECK, ReferralTypeDesc.NEED_CHECK);
            put(ReferralType.NEED_DOCTOR,ReferralTypeDesc.NEED_DOCTOR);
            put(ReferralType.NEED_TREATMENT, ReferralTypeDesc.NEED_TREATMENT);
        }

    };

    public class HospitalLevelDesc {

        /**
         * 省级
         */
        public static final String PROVINCE_HOSPITAL = "省级医院";

        /**
         * 市级
         */
        public static final String CITY_HOSPITAL = "市级医院";

        /**
         * 区县级
         */
        public static final String COUNTY_HOSPITAL = "区县级医院";

        /**
         * 社区医院
         */
        public static final String TOWNSHIP_HOSPITAL = "社区级医院";

    }
    
    public class DoctorLevelDesc {

        /**
         * 省级
         */
        public static final String PROVINCE_DOCTOR = "省级医生";

        /**
         * 市级
         */
        public static final String CITY_DOCTOR = "市级医生";

        /**
         * 区县级
         */
        public static final String COUNTY_DOCTOR = "区县级医生";

        /**
         * 社区级
         */
        public static final String TOWNSHIP_DOCTOR = "社区级医生";

    }
    

    
    
    

    public static final Map<String,String> HOSPITAL_LEVEL_NAME_MAP = new HashMap<String, String>(){

        private static final long serialVersionUID = -9169364185769184705L;

        {
            put(HospitalLevel.PROVINCE_HOSPITAL, HospitalLevelDesc.PROVINCE_HOSPITAL);
            put(HospitalLevel.CITY_HOSPITAL,HospitalLevelDesc.CITY_HOSPITAL);
            put(HospitalLevel.COUNTY_HOSPITAL, HospitalLevelDesc.COUNTY_HOSPITAL);
            put(HospitalLevel.TOWNSHIP_HOSPITAL, HospitalLevelDesc.TOWNSHIP_HOSPITAL);

        }

    };
    
    
    public static final Map<String,String> DOCTOR_LEVEL_NAME_MAP = new HashMap<String, String>(){


		private static final long serialVersionUID = 3181798029263356750L;

		{
            put(DoctorLevel.PROVINCE_DOCTOR, DoctorLevelDesc.PROVINCE_DOCTOR);
            put(DoctorLevel.CITY_DOCTOR,DoctorLevelDesc.CITY_DOCTOR);
            put(DoctorLevel.COUNTY_DOCTOR, DoctorLevelDesc.COUNTY_DOCTOR);
            put(DoctorLevel.TOWNSHIP_DOCTOR, DoctorLevelDesc.TOWNSHIP_DOCTOR);

        }

    };

    public class AreaCodeLevel {

        /**
         * 省级
         */
        public static final String PROVINCE = "1";

        /**
         * 市级
         */
        public static final String CITY = "2";

        /**
         * 区县级
         */
        public static final String COUNTY = "3";

        /**
         * 社区级
         */
        public static final String TOWNSHIP = "4";

    }

}
