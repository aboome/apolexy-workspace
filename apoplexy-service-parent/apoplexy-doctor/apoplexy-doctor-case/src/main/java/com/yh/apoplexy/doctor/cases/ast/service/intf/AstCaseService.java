package com.yh.apoplexy.doctor.cases.ast.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseInfoDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.QueryAstCaseDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * AST服务接口类
 * Created by wunder on 16/9/10 15:14.
 */
public interface AstCaseService {

    /**
     * 新增病例信息
     * @param astCasesDmo
     * @return
     */
    public Result insert(AstCasesDmo astCasesDmo);

    /**
     * 更新病例信息
     * @param astCasesDmo
     * @return
     */
    public Result update(AstCasesDmo astCasesDmo);

    /**
     * 删除病例信息
     * @param astCasesDmo
     * @return
     */
    public Result delete(AstCasesDmo astCasesDmo);

    /**
     * 查找病例信息
     * @param con
     * @return
     */
    public AstCasesDmo find(AstCasesDmo con);

    /**
     * 分页查询病例列表
     * @param con
     * @param page
     * @return
     */
    public List<AstCaseInfoDto> selectListByPage(QueryAstCaseDto con, Page page);

    /**
     * 分页查询我发布的病例列表
     * @param con
     * @param page
     * @return
     */
    public List<AstCaseInfoDto> queryMyPostListByPage(QueryAstCaseDto con, Page page);

    /**
     * 分页查询我收藏的病例列表
     * @param con
     * @param page
     * @return
     */
    public List<AstCaseInfoDto> queryMyCollectListByPage(QueryAstCaseDto con, Page page);

    /**
     * 分页查询我参与的病例列表
     * @param con
     * @param page
     * @return
     */
    public List<AstCaseInfoDto> queryMyPartakeListByPage(QueryAstCaseDto con, Page page);

    /**
     * 新增病例讨论（包括图片）
     * @param astCaseDetailDto
     * @return
     */
    public Result addCase(AstCaseDetailDto astCaseDetailDto);

    /**
     * 查询讨论病例详情
     * @param con
     * @return
     */
    public AstCaseDetailDto findCaseDetail(AstCasesDmo con);

    /**
     * 增加阅读量
     *
     * @param recordId
     * @return
     */
    public Result increaseReadCount(Long recordId);

    /**
     * 增加收藏量
     *
     * @param recordId
     * @return
     */
    public Result increaseCollectCount(Long recordId);

    /**
     * 增加点赞量
     *
     * @param recordId
     * @return
     */
    public Result increaseLikeCount(Long recordId);

    /**
     * 增加评论量
     *
     * @param recordId
     * @return
     */
    public Result increaseCommentCount(Long recordId);

    /**
     * 减少收藏量
     *
     * @param recordId
     * @return
     */
    public Result decreaseCollectCount(Long recordId);

    /**
     * 减少点赞量
     *
     * @param recordId
     * @return
     */
    public Result decreaseLikeCount(Long recordId);

    /**
     * 减少评论量
     *
     * @param recordId
     * @return
     */
    public Result decreaseCommentCount(Long recordId);

    /**
     * 删除病例
     * @param recordId
     * @param userId
     * @return
     */
    public Result deleteCase(Long recordId,Long userId);
}
