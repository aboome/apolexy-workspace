package com.yh.apoplexy.doctor.cases.discuss.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseInfoDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.QueryDiscussCaseDto;
import com.yh.apoplexy.doctor.cases.discuss.result.DiscussCasePermissionResult;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 讨论病例服务接口类
 * Created by wunder on 16/9/6 15:28.
 */
public interface DiscussCaseService {

    /**
     * 新增讨论病例信息
     * @param discussCaseDmo
     * @return
     */
    public Result insert(DiscussCaseDmo discussCaseDmo);

    /**
     * 更新讨论病例信息
     * @param discussCaseDmo
     * @return
     */
    public Result update(DiscussCaseDmo discussCaseDmo);

    /**
     * 删除讨论病例信息
     * @param discussCaseDmo
     * @return
     */
    public Result delete(DiscussCaseDmo discussCaseDmo);

    /**
     * 查找讨论病例信息
     * @param con
     * @return
     */
    public DiscussCaseDmo find(DiscussCaseDmo con);

    /**
     * 分页查询讨论病例列表
     * @param con
     * @param page
     * @return
     */
    public List<DiscussCaseInfoDto> selectListByPage(QueryDiscussCaseDto con, Page page);

    /**
     * 分页查询我发布的讨论病例列表
     * @param con
     * @param page
     * @return
     */
    public List<DiscussCaseInfoDto> queryMyPostListByPage(QueryDiscussCaseDto con, Page page);

    /**
     * 分页查询我收藏的讨论病例列表
     * @param con
     * @param page
     * @return
     */
    public List<DiscussCaseInfoDto> queryMyCollectListByPage(QueryDiscussCaseDto con, Page page);

    /**
     * 分页查询我参与的讨论病例列表
     * @param con
     * @param page
     * @return
     */
    public List<DiscussCaseInfoDto> queryMyPartakeListByPage(QueryDiscussCaseDto con, Page page);

    /**
     * 新增病例讨论（包括图片和视频）
     * @param discussCaseDetailDto
     * @return
     */
    public Result addDiscussCase(DiscussCaseDetailDto discussCaseDetailDto);

    /**
     * 查询讨论病例详情
     * @param con
     * @return
     */
    public DiscussCaseDetailDto findDiscussCaseDetail(DiscussCaseDmo con);

    /**
     * 检查医生的病例访问权限
     * @param recordId
     * @param userId
     * @return
     */
    public DiscussCasePermissionResult checkDoctorCasePermission(Long recordId, Long userId);

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
     * 删除讨论病例
     * @param recordId
     * @param userId
     * @return
     */
    public Result deleteCase(Long recordId,Long userId);
}
