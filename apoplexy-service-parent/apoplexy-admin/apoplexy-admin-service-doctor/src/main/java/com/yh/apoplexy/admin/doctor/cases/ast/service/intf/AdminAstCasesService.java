package com.yh.apoplexy.admin.doctor.cases.ast.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.NihssDmo;


import com.yh.apoplexy.assist.dto.admin.doctor.NihssDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminAstCaseDetailDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminQueryAstCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AstCasesReplyDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AstHistoryDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.AstCasesDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.AstMedicationDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * Ast病例管理
 *
 * @author zhangbiao
 */
public interface AdminAstCasesService {

    /**
     * 查找病例信息
     *
     * @param con
     * @return
     */
    public AstCasesDmo find(AstCasesDmo con);

    /**
     * 查询Ast病例列表
     *
     * @param con
     * @param page
     * @return
     */
    public List<AstCasesDto> queryAstCasesList(AdminQueryAstCaseDto con, Page page);

    /**
     * 查询讨论病例详情
     * @param con
     * @return
     */
    public AdminAstCaseDetailDto findCaseDetail(AstCasesDmo con);


    /**
     * 查询Ast病例的回复列表
     *
     * @param con
     * @return
     */
    public List<AstCasesReplyDto> queryAstCasesReplyList(AstCasesDmo con, Page page);

    /**
     * 根据评论的id号来删
     *
     * @param con
     * @return
     */
    public Result deleteAstCasesReply(AstCasesCommentDmo con);

    /**
     * 删除Ast病例讨论
     *
     * @param con
     * @return
     */
    public Result deleteAstCases(AstCasesDmo con);

    /**
     * 查询Nihss
     *
     * @param con
     * @return
     */
    public List<NihssDto> queryHihss(NihssDmo con);

    /**
     * 查询AST既往史
     *
     * @param con
     * @return
     */
    public List<AstHistoryDto> queryAstHistory(AstHistoryDmo con);

    /**
     * 查询ASt既往用药史
     *
     * @param con
     * @return
     */
    public List<AstMedicationDto> queryAstMedication(AstMedicationDmo con);

    /**
     * 删除AST病例评论信息
     *
     * @param con
     * @return
     */
    public Result deleteAstComment(AstCasesCommentDmo con);

    /**
     * 删除AST病例回复信息
     * @param con
     * @return
     */
    public Result deleteAstReply(AstCasesCommentDmo con);

    /**
     * 减少评论量
     *
     * @param recordId
     * @return
     */
    public Result decreaseCommentCount(Long recordId, Long num);

}
