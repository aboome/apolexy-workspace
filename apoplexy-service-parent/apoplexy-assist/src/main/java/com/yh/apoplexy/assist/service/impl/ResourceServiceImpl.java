/**
 * 
 */
package com.yh.apoplexy.assist.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.ResourceInfoDmo;
import com.yh.apoplexy.assist.service.intf.ResourceService;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.entity.Entity;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;

/**
 * 资源操作实现类
 * 
 * @author CC
 *
 */
@Service("resourceService")
@ServiceTrace
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private CommonDao<ResourceInfoDmo> commonDao;

	@Override
	public Result addResource(String resourceUuid, String resourceName, String resourceType) {
		
		Result result = new Result();
		
		if (StringUtils.isBlank(resourceUuid) || StringUtils.isBlank(resourceType)) {
			result.fail("parameter error");
			return result;
		}
		
		ResourceInfoDmo resourceInfoDmo = new ResourceInfoDmo();
		
		resourceInfoDmo.setCreateTime(DateUtil.getDate());
		resourceInfoDmo.setOwner("");
		resourceInfoDmo.setResourceName(resourceName);
		resourceInfoDmo.setResourceType(resourceType);
		resourceInfoDmo.setResourceUuid(resourceUuid);
		resourceInfoDmo.setStatus(Constants.ResourcesStatus.NORMAL);
		
		int i = commonDao.insert(resourceInfoDmo);
		
		return SqlAssertUtils.insertAssert(i);
	}
	
	

}
