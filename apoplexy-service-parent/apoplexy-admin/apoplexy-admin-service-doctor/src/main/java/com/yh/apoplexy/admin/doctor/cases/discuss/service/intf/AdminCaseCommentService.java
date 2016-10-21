package com.yh.apoplexy.admin.doctor.cases.discuss.service.intf;


import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminChildDiscussCommentDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminDiscussCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.ChildCaseCommentDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 讨论病例评论服务接口类
 * Created by wunder on 16/9/7 18:14.
 */
public interface AdminCaseCommentService {

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
     * 分页查询病例评论列表
     * @param con
     * @return
     */
    public List<AdminDiscussCommentDto> queryCommentList(CaseCommentDmo con);

    /**
     * 查询子评论列表
     * @param con
     * @return
     */
    public List<AdminChildDiscussCommentDto> queryChildCommentList(CaseCommentDmo con);

    /**
     * 查询子评论数
     * @param con
     * @return
     */
    public Long countChildComment(CaseCommentDmo con);

}
