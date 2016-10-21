package com.yh.apoplexy.admin.system.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.IdeaInfoDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * 
 * 意见反馈管理
 * 
 * @author zhangbiao
 *
 */
public interface AdminAdviceReplyService {
	  /**
	   * 
	   * 查询意见反馈列表
	   * @return
	   */
      public List<IdeaInfoDmo>  queryAdviceReplyList(Page page);
      
      /**
       * 
       * 查询意见反馈详情
       * @return
       */
      public IdeaInfoDmo queryAdviceReplyDetail(IdeaInfoDmo con);
      /**
       * 
       * 删除意见反馈
       * @param con
       * @return
       */
      public Result deleteAdviceReply(IdeaInfoDmo con);
      
      
}
