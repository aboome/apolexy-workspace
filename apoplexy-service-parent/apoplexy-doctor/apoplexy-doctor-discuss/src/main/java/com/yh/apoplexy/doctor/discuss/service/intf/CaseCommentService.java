package com.yh.apoplexy.doctor.discuss.service.intf;


import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.CaseCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.ChildCaseCommentDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 讨论病例评论服务接口类
 * Created by wunder on 16/9/7 18:14.
 */
public interface CaseCommentService {

    /**
     * 新增病例评论信息
     * @param caseCommentDmo
     * @return
     */
    public Result insert(CaseCommentDmo caseCommentDmo);

    /**
     * 更新病例评论信息
     * @param caseCommentDmo
     * @return
     */
    public Result update(CaseCommentDmo caseCommentDmo);

    /**
     * 删除病例评论信息
     * @param caseCommentDmo
     * @return
     */
    public Result delete(CaseCommentDmo caseCommentDmo);

    /**
     * 查找病例评论信息
     * @param con
     * @return
     */
    public CaseCommentDmo find(CaseCommentDmo con);

    /**
     * 提交病例评论信息
     * @param con
     * @return
     */
    public Result submitCaseComment(CaseCommentDmo con);

    /**
     * 回复病例评论信息
     * @param con
     * @return
     */
    public Result replyCaseComment(CaseCommentDmo con);

    /**
     * 分页查询病例评论列表
     * @param con
     * @param page
     * @return
     */
    public List<CaseCommentDto> queryCommentList(CaseCommentDmo con, Page page);

    /**
     * 查询子评论列表
     * @param con
     * @return
     */
    public List<ChildCaseCommentDto> queryChildCommentList(CaseCommentDmo con);

}
