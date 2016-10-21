package com.yh.apoplexy.doctor.cases.ast.service.impl;


import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstResourcesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseInfoDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.QueryAstCaseDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstHistoryService;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstMedicationService;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstResourceService;
import com.yh.apoplexy.doctor.cases.service.intf.NihssDetailService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * AST服务实现类
 * Created by wunder on 16/9/10 15:57.
 */
@Service("astCaseService")
@ServiceTrace
public class AstCaseServiceImpl implements AstCaseService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AstResourceService astResourceService;

    @Autowired
    private NihssDetailService nihssDetailService;

    @Autowired
    private AstHistoryService astHistoryService;

    @Autowired
    private AstMedicationService astMedicationService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Override
    public Result insert(AstCasesDmo astCasesDmo) {

        int i = commonDao.insert(astCasesDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(AstCasesDmo astCasesDmo) {

        int i = commonDao.update(astCasesDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(AstCasesDmo astCasesDmo) {

        int i = commonDao.delete(astCasesDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public AstCasesDmo find(AstCasesDmo con) {

        return (AstCasesDmo)commonDao.selectOne(con);

    }

    @Override
    public List<AstCaseInfoDto> selectListByPage(QueryAstCaseDto con, Page page) {

        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setCtType(DoctorConstants.AstResourcesType.CT);
        con.setCtaType(DoctorConstants.AstResourcesType.CTA);
        con.setCtpType(DoctorConstants.AstResourcesType.CTP);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        return commonDao.selectListByPage("AstCaseMapper.countCase","AstCaseMapper.queryCase",con,page);

    }

    @Override
    public List<AstCaseInfoDto> queryMyPostListByPage(QueryAstCaseDto con, Page page) {

        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setCtType(DoctorConstants.AstResourcesType.CT);
        con.setCtaType(DoctorConstants.AstResourcesType.CTA);
        con.setCtpType(DoctorConstants.AstResourcesType.CTP);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        return commonDao.selectListByPage("AstCaseMapper.countMyPostCase","AstCaseMapper.queryMyPostCase",con,page);

    }

    @Override
    public List<AstCaseInfoDto> queryMyCollectListByPage(QueryAstCaseDto con, Page page) {

        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setCtType(DoctorConstants.AstResourcesType.CT);
        con.setCtaType(DoctorConstants.AstResourcesType.CTA);
        con.setCtpType(DoctorConstants.AstResourcesType.CTP);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);
        con.setCollectionStatus(DoctorConstants.CaseCollectionStatus.NORMAL);

        return commonDao.selectListByPage("AstCaseMapper.countMyCollectCase","AstCaseMapper.queryMyCollectCase",con,page);

    }

    @Override
    public List<AstCaseInfoDto> queryMyPartakeListByPage(QueryAstCaseDto con, Page page) {

        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setCtType(DoctorConstants.AstResourcesType.CT);
        con.setCtaType(DoctorConstants.AstResourcesType.CTA);
        con.setCtpType(DoctorConstants.AstResourcesType.CTP);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);
        con.setCommentStatus(DoctorConstants.CommentStatus.NORMAL);

        return commonDao.selectListByPage("AstCaseMapper.countMyPartakeCase","AstCaseMapper.queryMyPartakeCase",con,page);

    }

    @Override
    @Transactional
    public Result addCase(AstCaseDetailDto astCaseDetailDto) {

        Result result = new Result();

        AstCasesDmo astCasesDmo = astCaseDetailDto.getAstCasesDmo();

        astCasesDmo.setCreateTime(DateUtil.getDate());
        astCasesDmo.setStatus(DoctorConstants.CaseStatus.NORMAL);
        astCasesDmo.setLikeCount(0L);
        astCasesDmo.setReadCount(0L);
        astCasesDmo.setCollectionCount(0L);
        astCasesDmo.setCommentCount(0L);

        result = insert(astCasesDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        AstCasesDmo latestAstCaseDmo = find(astCasesDmo);

        astCaseDetailDto.setAstCasesDmo(latestAstCaseDmo);

        result = astResourceService.batchInsert(astCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        result = nihssDetailService.batchInsertAst(astCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        result = astHistoryService.batchInsert(astCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        result = astMedicationService.batchInsert(astCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        //增加积分
        if(checkFirstCaseToday(latestAstCaseDmo.getUserId())){

            DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

            doctorScoreDetailDmo.setUserId(latestAstCaseDmo.getUserId());
            doctorScoreDetailDmo.setUserName(astCaseDetailDto.getDoctorMemberDmo().getDoctorName());
            doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.POST_AST);
            doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_AST_CASE));
            doctorScoreDetailDmo.setScore(10L);
            doctorScoreDetailDmo.setTime(DateUtil.getDate());

            result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

        }

        return result;

    }

    @Override
    public AstCaseDetailDto findCaseDetail(AstCasesDmo con) {

        AstCaseDetailDto astCaseDetailDto = new AstCaseDetailDto();

        if (null == con.getId()){
            return null;
        }

        //查询病例详情
        AstCasesDmo astCasesDmo = find(con);

        if (null == astCasesDmo) {
            return null;
        }

        astCaseDetailDto.setAstCasesDmo(astCasesDmo);

        DoctorMemberDmo memberCon = new DoctorMemberDmo();

        memberCon.setId(astCasesDmo.getUserId());
        memberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询发布病例详情的医生信息
        DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(memberCon);

        astCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);

        NihssDetailDmo nihssDetailCon = new NihssDetailDmo();

        nihssDetailCon.setRecordId(con.getId());
        nihssDetailCon.setType(DoctorConstants.NihssTestSrcType.AST);

        //查询NIHSS信息列表
        List<NihssDetailDmo> nihssDetailDmoList = nihssDetailService.selectList(nihssDetailCon);

        astCaseDetailDto.setNihssList(nihssDetailDmoList);

        AstHistoryDmo astHistoryCon = new AstHistoryDmo();

        astHistoryCon.setRecordId(con.getId());
        //查询既往史
        List<AstHistoryDmo> astHistoryDmoList = astHistoryService.selectList(astHistoryCon);

        astCaseDetailDto.setHisIllness(astHistoryDmoList);

        AstMedicationDmo astMedicationCon = new AstMedicationDmo();

        astMedicationCon.setRecordId(con.getId());
        //查询既往用药史
        List<AstMedicationDmo> astMedicationDmoList = astMedicationService.selectList(astMedicationCon);

        astCaseDetailDto.setHisMedicaitionList(astMedicationDmoList);

        AstResourcesDmo ctCon = new AstResourcesDmo();

        ctCon.setRecordId(con.getId());
        ctCon.setType(DoctorConstants.AstResourcesType.CT);

        //查询病例的CT图片列表
        List<AstResourcesDmo> ctList = astResourceService.selectList(ctCon);

        astCaseDetailDto.setCtList(ctList);

        AstResourcesDmo ctaCon = new AstResourcesDmo();

        ctaCon.setRecordId(con.getId());
        ctaCon.setType(DoctorConstants.AstResourcesType.CTA);

        //查询病例的CTA图片列表
        List<AstResourcesDmo> ctaList = astResourceService.selectList(ctaCon);

        astCaseDetailDto.setCtaList(ctaList);

        AstResourcesDmo ctpCon = new AstResourcesDmo();

        ctpCon.setRecordId(con.getId());
        ctpCon.setType(DoctorConstants.AstResourcesType.CTP);

        //查询病例的CTP图片列表
        List<AstResourcesDmo> ctpList = astResourceService.selectList(ctpCon);

        astCaseDetailDto.setCtpList(ctpList);

        //增加阅读量
        increaseReadCount(astCasesDmo.getId());

        return astCaseDetailDto;

    }

    @Override
    public Result increaseReadCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("AstCaseMapper.increaseReadCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result increaseCollectCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("AstCaseMapper.increaseCollectCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result increaseLikeCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("AstCaseMapper.increaseLikeCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result increaseCommentCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("AstCaseMapper.increaseCommentCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result decreaseCollectCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("AstCaseMapper.decreaseCollectCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result decreaseLikeCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("AstCaseMapper.decreaseLikeCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result decreaseCommentCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("AstCaseMapper.decreaseCommentCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result deleteCase(Long recordId, Long userId) {

        Result result = new Result();

        AstCasesDmo astCaseCon = new AstCasesDmo();

        astCaseCon.setId(recordId);
        astCaseCon.setStatus(DoctorConstants.CaseStatus.NORMAL);
        //查询病例信息
        AstCasesDmo astCasesDmo = find(astCaseCon);
        //病例信息不存在
        if (null == astCasesDmo){

            result.fail("DLC-0000","病例信息不存在");
            return result;

        }
        //病例删除用户不是病例发布用户
        if (!astCasesDmo.getUserId().equals(userId)){

            result.fail("DLC-0001","删除用户不是病例发布用户");
            return result;

        }

        astCasesDmo.setStatus(DoctorConstants.CaseStatus.DELETE);

        result = update(astCasesDmo);

        if (!result.isSuccess()){

            result.fail("DLC-0002","删除失败");
            return result;
        }

        return result;
    }

    /**
     * 检查是否今日首次发布
     * @param doctorId
     * @return
     */
    private boolean checkFirstCaseToday(Long doctorId){

        QueryAstCaseDto con = new QueryAstCaseDto();

        con.setUserId(doctorId);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        Long count = commonDao.selectCount("AstCaseMapper.countCaseToday",con);

        return count.equals(1L);

    }

}
