package com.yh.apoplexy.doctor.discuss.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.ChildAstCommentDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * AST病例评论服务接口类
 * Created by wunder on 16/9/10 17:12.
 */
public interface AstCommentService {

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
     * 提交病例评论信息
     * @param con
     * @return
     */
    public Result submitCaseComment(AstCasesCommentDmo con);

    /**
     * 回复病例评论信息
     * @param con
     * @return
     */
    public Result replyCaseComment(AstCasesCommentDmo con);

    /**
     * 分页查询病例评论列表
     * @param con
     * @param page
     * @return
     */
    public List<AstCommentDto> queryCommentList(AstCasesCommentDmo con, Page page);

    /**
     * 查询子评论列表
     * @param con
     * @return
     */
    public List<ChildAstCommentDto> queryChildCommentList(AstCasesCommentDmo con);
}
