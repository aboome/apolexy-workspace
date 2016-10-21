package com.yh.apoplexy.admin.portal.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.apoplexy.admin.portal.common.controller.BaseController;
import com.yh.apoplexy.admin.portal.common.vo.PageVo;
import com.yh.apoplexy.admin.portal.system.form.AdminComReplyDeleForm;
import com.yh.apoplexy.admin.portal.system.form.AdminComReplyDetailForm;
import com.yh.apoplexy.admin.portal.system.form.AdminComReplyListForm;
import com.yh.apoplexy.admin.system.service.intf.AdminAdviceReplyService;
import com.yh.apoplexy.assist.dmo.common.IdeaInfoDmo;
import com.yh.apoplexy.assist.utils.OperateLogUtil;
import com.yh.apoplexy.common.constants.OperateLogConstants;
import com.yh.apoplexy.common.dto.UserSession;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.web.result.JsonResult;
/**
 * 意见反馈管理
 * @author zhangbiao
 *
 */

@Controller
@RequestMapping(value = "/comReply")
public class AdminComReplyController extends BaseController {

	@Autowired
	private  AdminAdviceReplyService adminAdviceReplyService;
	

	@RequestMapping(value = "/comReplycom", method = RequestMethod.GET)
	public String comReplycom(HttpServletRequest request, Model model) {

		UserSession userSession = getUserSession(request);
 
		return systemView("ideaList");
	}
	
	/**
	 * 
	 * 查询意见反馈列表
	 * @param request
	 * @param response
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/queryIdeaReplyList", method = RequestMethod.POST)
	public String  queryIdeaReplyList(HttpServletRequest request,
			HttpServletResponse response, Model model,  AdminComReplyListForm form 
			 ) {
		
		 Page page = new Page();
		 page.setCurrentPage(Integer.valueOf(form.getPageNum()));
		 page.setPageSize(Integer.valueOf(form.getPageSize())); 
		 List<IdeaInfoDmo> list = adminAdviceReplyService.queryAdviceReplyList(page);
		 model.addAttribute("comReplyList",list);
		 model.addAttribute("page", PageVo.createPageVo(request, page));  
 		 return systemView("ideaListtable"); 
	}
	
	
	/**
	 * 
	 * 查询意见反馈详情
	 * @param request
	 * @param response
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/queryIdeaReplyDetail", method = RequestMethod.POST)
	public String  queryIdeaReplyDetail(HttpServletRequest request,
			HttpServletResponse response, Model model, AdminComReplyDetailForm form 
			 ) {
		
		 String id = form.getId();
		 IdeaInfoDmo con = new IdeaInfoDmo();
		 
		 
		 if(!StringUtils.isBlank(id)){
			  con.setId(Long.valueOf(id));
		 }
		 IdeaInfoDmo comReplyDetail =  adminAdviceReplyService.queryAdviceReplyDetail(con);
		 model.addAttribute("comReplyDetail",comReplyDetail);
 		 return baseView(""); 
	}
	 
	/**
	 * 
	 * 删除意见反馈
	 * @param request
	 * @param response
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/deleteIdeaReply", method = RequestMethod.POST)
	public
    @ResponseBody
	JsonResult    deleteIdeaReply(HttpServletRequest request,
			HttpServletResponse response, Model model,
			AdminComReplyDeleForm form) {
		
		    JsonResult jsonResult = new JsonResult(); 
		    String id = form.getId();
		    
		    IdeaInfoDmo con = new IdeaInfoDmo();
		    
		    if(!StringUtils.isBlank(id)){
		    	con.setId(Long.valueOf(id));  
		    }
		  
		    Result result = adminAdviceReplyService.deleteAdviceReply(con);
		    if(result.isSuccess()){
				jsonResult.setSuccess(true);
			}else{
				jsonResult.fail("删除意见反馈失败");
			}
		    
	        UserSession userSession = getUserSession(request);
		    
	        OperateLogUtil.addOperateLog(OperateLogConstants.Model.ADMIN,
	                OperateLogConstants.OperateEnum.DELETE_IDEAREPLY,
	                Long.parseLong(userSession.getUserId()),
	                userSession.getName(),
	                OperateLogConstants.OperateDesc.DELETE_IDEAREPLY);
		    
			return jsonResult;
	}
	
	
}
