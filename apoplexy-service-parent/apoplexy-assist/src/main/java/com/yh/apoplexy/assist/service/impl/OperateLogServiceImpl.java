/**
 * 
 */
package com.yh.apoplexy.assist.service.impl;

import java.util.List;

import com.yh.apoplexy.common.constants.OperateLogConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.OperateLogDmo;
import com.yh.apoplexy.assist.dto.admin.log.SystemLogInputDto;
import com.yh.apoplexy.assist.service.intf.OperateLogService;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;

/**
 * 操作日志服务
 * 
 * @author CC
 * 
 */
@Service("operateLogService")
@ServiceTrace
public class OperateLogServiceImpl implements OperateLogService {

	@Autowired
	private CommonDao<OperateLogDmo> commonDao;

	@Override
	public Result addOperateLog(String model, String operateEnum, Long userId, String userName, String operateDesc) {

		Result result = new Result();

		if (StringUtils.isBlank(model) || StringUtils.isBlank(operateEnum) || StringUtils.isBlank(userName)
				|| null == userId || StringUtils.isBlank(operateDesc)) {
			result.fail("parameter error");
			return result;
		}

		OperateLogDmo operateLogDmo = new OperateLogDmo();
		
		operateLogDmo.setModel(model);
		operateLogDmo.setModelName(OperateLogConstants.MODEL_NAME_MAP.get(model));
		operateLogDmo.setOperateDesc(operateDesc);
		operateLogDmo.setOperateEnum(operateEnum);
		operateLogDmo.setOperateTime(DateUtil.getDate());
		operateLogDmo.setOperateUserId(userId);
		operateLogDmo.setOperateUserName(userName);


		int i = commonDao.insert(operateLogDmo);

		return SqlAssertUtils.insertAssert(i);
	}

	/**
	 * 分页查询系统操作日志
	 */
	public List<OperateLogDmo> queryOperateLogList(SystemLogInputDto con,Page page) {
		return commonDao.selectListByPage("OperateLogMapper.queryOperateLogCount", "OperateLogMapper.queryOperateLogList", con, page);
	}
}
