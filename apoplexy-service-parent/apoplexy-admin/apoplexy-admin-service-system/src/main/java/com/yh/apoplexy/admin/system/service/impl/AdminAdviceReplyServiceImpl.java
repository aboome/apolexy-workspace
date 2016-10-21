package com.yh.apoplexy.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.system.service.intf.AdminAdviceReplyService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.IdeaInfoDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * 
 * 意见反馈管理
 * @author zhangbiao
 *
 */

@Service("adminAdviceReplyService")
@ServiceTrace 
public class AdminAdviceReplyServiceImpl implements AdminAdviceReplyService {
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * 查询意见反馈列表
	 */
	@Override
	public List<IdeaInfoDmo> queryAdviceReplyList(Page page) {
		
	    return commonDao.selectListByPage("AdviceReplyMapper.queryAdviceReplyListCount", "AdviceReplyMapper.queryAdviceReplyList", null, page);
		 
	}
    /**
     * 
     * 查询意见反馈详情
     */
	@Override
	public IdeaInfoDmo queryAdviceReplyDetail(IdeaInfoDmo con) {
		if(con == null){
		  return null;
		}
	   if(con.getId() == null){
		  return null;
	   }
	   
	   return (IdeaInfoDmo)commonDao.selectOne(con);
	}

	
	/**
	 * 
	 * 删除意见反馈
	 */
	@Override
	public Result deleteAdviceReply(IdeaInfoDmo con) {
		Result result = new Result();
		
		if(con == null){
			result.fail("","传入参数为空");
			return result;
		}
		if(con.getId() == null){
			result.fail("","传入参数为空");
			return result;
		}
		
		int i = commonDao.delete(con);
		
	    return SqlAssertUtils.deleteAssert(i);
		
	}

}
