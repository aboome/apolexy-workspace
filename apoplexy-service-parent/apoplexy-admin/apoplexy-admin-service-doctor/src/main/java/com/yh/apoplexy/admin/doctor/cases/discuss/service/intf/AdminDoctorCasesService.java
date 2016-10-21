package com.yh.apoplexy.admin.doctor.cases.discuss.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CasesReplyDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminDiscussCaseDetailDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminQueryDiscussCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.CasesDiscussDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.CasesDiscussReplyDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * @author zhangbiao
 */
public interface AdminDoctorCasesService {

    /**
     * 查找病例讨论信息
     *
     * @param con
     * @return
     */
    public DiscussCaseDmo find(DiscussCaseDmo con);

    /**
     * 更新病例讨论信息
     *
     * @param con
     * @return
     */
    public Result update(DiscussCaseDmo con);

    /**
     * 查询病例讨论列表
     *
     * @return
     */
    public List<CasesDiscussDto> queryCasesDiscussList(AdminQueryDiscussCaseDto con, Page page);

    /**
     * 查询病例讨论详情
     *
     * @param con
     * @return
     */
    public AdminDiscussCaseDetailDto queryCasesDiscussDetail(DiscussCaseDmo con);

    /**
     * 查询病例讨论的回复列表
     *
     * @param con
     * @return
     */
    public List<CasesDiscussReplyDto> queryCasesDiscussReplyList(DiscussCaseDmo con, Page page);

    /**
     * 删除病例讨论评论信息
     *
     * @param con
     * @return
     */
    public Result deleteDiscussComment(CaseCommentDmo con);

    /**
     * 删除病例讨论回复信息
     * @param con
     * @return
     */
    public Result deleteDiscussReply(CaseCommentDmo con);
    /**
     * 删除病例
     *
     * @param con
     * @return
     */
    public Result deleteCases(DiscussCaseDmo con);

    /**
     * 减少评论量
     *
     * @param recordId
     * @return
     */
    public Result decreaseCommentCount(Long recordId, Long num);

}
