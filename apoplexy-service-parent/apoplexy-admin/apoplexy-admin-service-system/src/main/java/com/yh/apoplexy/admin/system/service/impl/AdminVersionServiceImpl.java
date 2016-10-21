package com.yh.apoplexy.admin.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.system.service.intf.AdminVersionService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.SystemVersionInfoDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;


@Service("adminVersionService")
@ServiceTrace 
public class AdminVersionServiceImpl implements AdminVersionService {
	
	@Autowired
	private CommonDao commonDao;
	
	
    /**
     * 
     * 查询版本号
     */
	@Override
	public List<SystemVersionInfoDmo> findVersion(SystemVersionInfoDmo con) {
		 
	   return  commonDao.selectList(con);
	}
	
	
	
    /**
     * 
     * 修改版本号
     */
	@Override
	public Result modifyVersion(SystemVersionInfoDmo con) {
		Result result = new Result();
		if(con == null){
			result.fail("","修改失败");
			return result;
		}
		if(con.getId() == null){
			result.fail("","修改失败");
			return result;
		}
		int i = commonDao.update(con);
		
	    return SqlAssertUtils.updateAssert(i);
	}

}
