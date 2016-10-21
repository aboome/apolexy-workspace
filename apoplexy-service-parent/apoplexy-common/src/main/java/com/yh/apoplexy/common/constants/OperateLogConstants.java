package com.yh.apoplexy.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志常量类
 * Created by wunder on 2016/10/9 22:13.
 */
public class OperateLogConstants {

    /**
     * 模块
     */
    public class Model {

        /**
         * ADMIN
         */
        public static final String ADMIN = "0";

        /**
         * 医生端APP
         */
        public static final String DOCTOR_APP = "1";

        /**
         * 患者端APP
         */
        public static final String PATIENT_APP = "2";

    }

    /**
     * 模块名称
     */
    public class ModelName {

        /**
         * ADMIN
         */
        public static final String ADMIN = "czzs-admin";

        /**
         * 医生端APP
         */
        public static final String DOCTOR_APP = "czzs-doctor";

        /**
         * 患者端APP
         */
        public static final String PATIENT_APP = "czzs-patient";

    }

    /**
     * 操作枚举
     */
    public class OperateEnum{

        /**
         * 管理员登录
         */
        public static final String ADMIN_LOGIN = "00";

        /**
         * 新增医院信息
         */
        public static final String ADD_HOSPITAL= "01";
        
        /**
         * 修改医院信息
         */
        public static final String UPDATE_HOSPITAL= "02";
        
        /**
         * 删除医院信息
         */
        public static final String DELETE_HOSPITAL= "03";
        
        

        
        /**
         * 导入医院信息
         */
        public static final String IMPORT_HOSPITAL= "04";
        
        
        /**
         * 新增医生信息
         */
        public static final String ADD_DOCTOR= "20";
        
        
        /**
         * 修改医生信息
         */
        public static final String UPDATE_DOCTOR= "21";
        
        /**
         * 删除医生信息
         */
        public static final String DELETE_DOCTOR= "22";
        
        /**
         * 导入医生信息
         */
        public static final String IMPORT_DOCTOR= "23";
        
        
        
        /**
         * 新增主页推荐位
         */
        public static final String ADD_BANNERINFO= "24";
        
        
        /**
         * 修改主页推荐位
         */
        public static final String UPDATE_BANNERINFO= "25";
        
        
        /**
         *删除主页推荐位
         */
        public static final String DELETE_BANNERINFO= "26";
        

        /**
         *新增开机动画
         */
        public static final String ADD_LANDINGPAGE= "28";
        
        /**
         *修改开机动画
         */
        public static final String UPDATE_LANDINGPAGE= "29";
        
        
        /**
         *删除开机动画
         */
        public static final String DELETE_LANDINGPAGE= "50";
        
        
        
        /**
         *新增用户信息
         */
        public static final String ADD_USERMANAGEMENT= "51";
        
        /**
         *修改用户信息
         */
        public static final String UPDATE_USERMANAGEMENT= "52";
        
        
        /**
         *删除用户信息
         */
        public static final String DELETE_USERMANAGEMENT= "53";
        
        
        /**
         *解锁用户
         */
        public static final String UNLOCK_USER= "53";
        
        
        /**
         *绑定角色
         */
        public static final String BINDING_USER= "54";
        
        
        /**
         *重置管理员密码
         */
        public static final String RESET_PASSWORD= "55";

        /**
         *修改管理员密码
         */
        public static final String MODIFY_PASSWORD= "27";
        
        /**
         *新增角色
         */
        public static final String ADD_ROLEINFO= "56";
        
        /**
         *修改角色
         */
        public static final String UPDATE_ROLEINFO= "57";
        
        
        /**
         *删除角色
         */
        public static final String DELETE_ROLEINFO= "58";
        
        /**
         *删除意见反馈
         */
        public static final String DELETE_IDEAREPLY= "59";
        
        /**
         *新增菜单
         */
        public static final String ADD_MENU= "60";
        
        /**
         *修改菜单
         */
        public static final String UPDATE_MENU= "61";
        
        /**
         *删除菜单
         */
        public static final String DELETE_MENU= "62";
        
        /**
         *修改版本号
         */
        public static final String UPDATE_VERSION= "63";
        
        
        /**
         * 医生注册
         */
        public static final String DOCTOR_REGISTER = "10";

        /**
         * 医生登录
         */
        public static final String DOCTOR_LOGIN = "11";

        /**
         * 患者注册
         */
        public static final String PATIENT_REGISTER = "20";

        /**
         * 患者登录
         */
        public static final String PATIENT_LOGIN = "21";

    }

    /**
     * 操作描述
     */
    public class OperateDesc{

        /**
         * 管理员登录
         */
        public static final String ADMIN_LOGIN = "管理员登录";

        /**
         * 新增医院信息
         */
        public static final String ADD_HOSPITAL= "新增医院信息";
        
        
        /**
         * 修改医院信息
         */
        public static final String UPDATE_HOSPITAL= "修改医院信息";
        
        /**
         * 删除医院信息
         */
        public static final String DELETE_HOSPITAL= "删除医院信息";
        
        
        
        /**
         * 新增医生信息
         */
        public static final String ADD_DOCTOR= "新增医生信息";
        
        /**
         * 修改医生信息
         */
        public static final String UPDATE_DOCTOR= "修改医生信息";
        
        /**
         * 删除医生信息
         */
        public static final String DELETE_DOCTOR= "删除医生信息";
        
        
        /**
         * 导入医生信息
         */
        public static final String IMPORT_DOCTOR= "导入医生信息";
        
        /**
         * 导入医院信息
         */
        public static final String IMPORT_HOSPITAL= "导入医院信息";
        
        
        /**
         * 新增主页推荐位
         */
        public static final String ADD_BANNERINFO= "新增主页推荐位";
        
        
        /**
         * 修改主页推荐位
         */
        public static final String UPDATE_BANNERINFO= "修改主页推荐位";
        
        /**
         *删除主页推荐位
         */
        public static final String DELETE_BANNERINFO= "删除主页推荐位";

        /**
         *新增开机动画
         */
        public static final String ADD_LANDINGPAGE= "新增开机动画";
        
        
        /**
         *修改开机动画
         */
        public static final String UPDATE_LANDINGPAGE= "修改开机动画";
        
        
        /**
         *删除开机动画
         */
        public static final String DELETE_LANDINGPAGE= "删除开机动画";
        
        
        /**
         *新增用户信息
         */
        public static final String ADD_USERMANAGEMENT= "新增用户信息";
        
        
        /**
         *修改用户信息
         */
        public static final String UPDATE_USERMANAGEMENT= "修改用户信息";
        
        
        /**
         *删除用户信息
         */
        public static final String DELETE_USERMANAGEMENT= "删除用户信息";
        
        
        /**
         *解锁用户
         */
        public static final String UNLOCK_USER= "解锁用户";
        
        
        /**
         *绑定角色
         */
        public static final String BINDING_USER= "绑定角色";
        
        
        /**
         *重置管理员密码
         */
        public static final String RESET_PASSWORD= "重置管理员密码";

        /**
         *修改管理员密码
         */
        public static final String MODIFY_PASSWORD= "修改管理员密码";
        
        
        /**
         *新增角色
         */
        public static final String ADD_ROLEINFO= "新增角色";
        
        
        /**
         *修改角色
         */
        public static final String UPDATE_ROLEINFO= "修改角色";
        
        
        /**
         *删除角色
         */
        public static final String DELETE_ROLEINFO= "删除角色";
        
        
        /**
         *删除意见反馈
         */
        public static final String DELETE_IDEAREPLY= "删除意见反馈";
        
        /**
         *新增菜单
         */
        public static final String ADD_MENU= "新增菜单";
        
        
        /**
         *修改菜单
         */
        public static final String UPDATE_MENU= "修改菜单";
        
        
        /**
         *删除菜单
         */
        public static final String DELETE_MENU= "删除菜单";
        
        /**
         *修改版本号
         */
        public static final String UPDATE_VERSION= "修改版本号";
        
        
        /**
         * 医生注册
         */
        public static final String DOCTOR_REGISTER = "医生注册";

        /**
         * 医生登录
         */
        public static final String DOCTOR_LOGIN = "医生登录";

        /**
         * 患者注册
         */
        public static final String PATIENT_REGISTER = "患者注册";

        /**
         * 患者登录
         */
        public static final String PATIENT_LOGIN = "患者登录";

    }

    public static final Map<String, String> MODEL_NAME_MAP = new HashMap<String, String>() {

        private static final long serialVersionUID = 7077435493298893498L;

        {
            put(Model.ADMIN, ModelName.ADMIN);
            put(Model.DOCTOR_APP, ModelName.DOCTOR_APP);
            put(Model.PATIENT_APP, ModelName.PATIENT_APP);
        }
    };

}
