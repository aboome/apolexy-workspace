package com.yh.apoplexy.admin.base.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.base.service.intf.AdminRegisterProtocolService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.RegisterProtocolDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;

@Service("adminRegisterProtocolService")
@ServiceTrace
public class AdminRegisterProtocolServiceImpI implements AdminRegisterProtocolService{

	@Autowired
	private CommonDao commonDao;

	@Override
	public RegisterProtocolDmo selectOne(RegisterProtocolDmo registerProtocolDmo) {
	          
		return (RegisterProtocolDmo) commonDao.selectOne(registerProtocolDmo);

	}

	@Override
	public Result update(RegisterProtocolDmo registerProtocolDmo) {

        int i =commonDao.update(registerProtocolDmo);

        return SqlAssertUtils.updateAssert(i);

	}

}
