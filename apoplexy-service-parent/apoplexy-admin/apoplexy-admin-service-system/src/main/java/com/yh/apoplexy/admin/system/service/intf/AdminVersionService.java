package com.yh.apoplexy.admin.system.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.SystemVersionInfoDmo;
import com.yjh.framework.lang.Result;

/**
 * 
 * 
 * 版本升级管理
 * @author zhangbiao
 *
 */
public interface AdminVersionService {
	 /**
	  * 查询版本号
	  * @return
	  */
     public List<SystemVersionInfoDmo>  findVersion(SystemVersionInfoDmo con);
     /**
      * 
      * 修改版本号
      * @return
      */
     public Result modifyVersion(SystemVersionInfoDmo con);
}
