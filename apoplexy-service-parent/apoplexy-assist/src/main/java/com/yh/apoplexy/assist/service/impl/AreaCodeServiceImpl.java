/**
 * 
 */
package com.yh.apoplexy.assist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.AreaCodeDmo;
import com.yh.apoplexy.assist.service.intf.AreaCodeService;
import com.yjh.framework.core.trace.ServiceTrace;

/**
 * 区域服务实现
 * 
 * @author CC
 *
 */
@Service("areaCodeService")
@ServiceTrace
public class AreaCodeServiceImpl implements AreaCodeService {
	
	@Autowired
	private CommonDao<AreaCodeDmo> commonDao;

}
