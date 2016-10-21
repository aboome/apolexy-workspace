package com.yh.apoplexy.admin.doctor.cases.ast.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminAstCommentDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminChildAstCommentDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * Created by wunder on 16/9/27 14:15.
 */
public interface AdminAstCommentService {

    /**
     * 新增病例评论信息
     * @param astCasesCommentDmo
     * @return
     */
    public Result insert(AstCasesCommentDmo astCasesCommentDmo);

    /**
     * 更新病例评论信息
     * @param astCasesCommentDmo
     * @return
     */
    public Result update(AstCasesCommentDmo astCasesCommentDmo);

    /**
     * 删除病例评论信息
     * @param astCasesCommentDmo
     * @return
     */
    public Result delete(AstCasesCommentDmo astCasesCommentDmo);

    /**
     * 查找病例评论信息
     * @param con
     * @return
     */
    public AstCasesCommentDmo find(AstCasesCommentDmo con);

    /**
     * 分页查询病例评论列表
     * @param con
     * @return
     */
    public List<AdminAstCommentDto> queryCommentList(AstCasesCommentDmo con);

    /**
     * 查询子评论列表
     * @param con
     * @return
     */
    public List<AdminChildAstCommentDto> queryChildCommentList(AstCasesCommentDmo con);

    /**
     * 查询子评论数
     * @param con
     * @return
     */
    public Long countChildComment(AstCasesCommentDmo con);

}
