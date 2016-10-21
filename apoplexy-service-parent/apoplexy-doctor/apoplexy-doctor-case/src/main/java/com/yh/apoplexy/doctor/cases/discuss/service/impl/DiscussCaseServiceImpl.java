package com.yh.apoplexy.doctor.cases.discuss.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseInfoDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.QueryDiscussCaseDto;
import com.yh.apoplexy.doctor.cases.discuss.result.DiscussCasePermissionResult;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.CaseResourceService;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
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
 * 讨论病例服务实现类
 * Created by wunder on 16/9/6 15:28.
 */
@Service("discussCaseService")
@ServiceTrace
public class DiscussCaseServiceImpl implements DiscussCaseService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private CaseResourceService caseResourceService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Override
    public Result insert(DiscussCaseDmo discussCaseDmo) {

        int i = commonDao.insert(discussCaseDmo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    public Result update(DiscussCaseDmo discussCaseDmo) {

        int i = commonDao.update(discussCaseDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(DiscussCaseDmo discussCaseDmo) {

        int i = commonDao.delete(discussCaseDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public DiscussCaseDmo find(DiscussCaseDmo con) {

        return (DiscussCaseDmo) commonDao.selectOne(con);

    }

    @Override
    public List<DiscussCaseInfoDto> selectListByPage(QueryDiscussCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        return commonDao.selectListByPage("DiscussCaseMapper.countCase", "DiscussCaseMapper.queryCase", con, page);

    }

    @Override
    public List<DiscussCaseInfoDto> queryMyPostListByPage(QueryDiscussCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        return commonDao.selectListByPage("DiscussCaseMapper.countMyPostCase", "DiscussCaseMapper.queryMyPostCase", con, page);

    }

    @Override
    public List<DiscussCaseInfoDto> queryMyCollectListByPage(QueryDiscussCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);
        con.setCollectionStatus(DoctorConstants.CaseCollectionStatus.NORMAL);

        return commonDao.selectListByPage("DiscussCaseMapper.countMyCollectCase", "DiscussCaseMapper.queryMyCollectCase", con, page);

    }

    @Override
    public List<DiscussCaseInfoDto> queryMyPartakeListByPage(QueryDiscussCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);
        con.setCommentStatus(DoctorConstants.CommentStatus.NORMAL);

        return commonDao.selectListByPage("DiscussCaseMapper.countMyPartakeCase", "DiscussCaseMapper.queryMyPartakeCase", con, page);

    }

    @Override
    @Transactional
    public Result addDiscussCase(DiscussCaseDetailDto discussCaseDetailDto) {

        Result result = new Result();

        DiscussCaseDmo discussCaseDmo = discussCaseDetailDto.getDiscussCaseDmo();

        discussCaseDmo.setCreateTime(DateUtil.getDate());
        discussCaseDmo.setLastUpdateTime(DateUtil.getDate());
        discussCaseDmo.setStatus(DoctorConstants.CaseStatus.NORMAL);
        discussCaseDmo.setLikeCount(0L);
        discussCaseDmo.setReadCount(0L);
        discussCaseDmo.setCollectionCount(0L);
        discussCaseDmo.setCommentCount(0L);

        result = insert(discussCaseDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        DiscussCaseDmo latestDiscussCaseDmo = find(discussCaseDmo);

        discussCaseDetailDto.setDiscussCaseDmo(latestDiscussCaseDmo);

        CaseResourceDmo video = discussCaseDetailDto.getVideo();

        if (null != video) {

            video.setRecordId(latestDiscussCaseDmo.getId());

        }

        result = caseResourceService.insert(video);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        result = caseResourceService.batchInsert(discussCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        //增加积分
        if(checkFirstCaseToday(latestDiscussCaseDmo.getUserId())){

            DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

            doctorScoreDetailDmo.setUserId(latestDiscussCaseDmo.getUserId());
            doctorScoreDetailDmo.setUserName(discussCaseDetailDto.getDoctorMemberDmo().getDoctorName());
            doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.POST_DISCUSS);
            doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_DISCUSS_CASE));
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
    public DiscussCaseDetailDto findDiscussCaseDetail(DiscussCaseDmo con) {

        DiscussCaseDetailDto discussCaseDetailDto = new DiscussCaseDetailDto();

        if (null == con.getId()){
            return null;
        }

        //查询病例详情
        DiscussCaseDmo discussCaseDmo = find(con);

        if (null == discussCaseDmo) {
            return null;
        }

        discussCaseDetailDto.setDiscussCaseDmo(discussCaseDmo);

        DoctorMemberDmo memberCon = new DoctorMemberDmo();

        memberCon.setId(discussCaseDmo.getUserId());
        memberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询发布病例详情的医生信息
        DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(memberCon);

        discussCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);

        CaseResourceDmo videoCon = new CaseResourceDmo();

        videoCon.setRecordId(con.getId());
        videoCon.setType(Constants.ResourcesType.VIDEO);
        //查询病例视频信息
        CaseResourceDmo video = caseResourceService.find(videoCon);

        discussCaseDetailDto.setVideo(video);

        CaseResourceDmo imageCon = new CaseResourceDmo();

        imageCon.setRecordId(con.getId());
        imageCon.setType(Constants.ResourcesType.IMAGE);

        //查询病例的图片列表
        List<CaseResourceDmo> imageList = caseResourceService.selectList(imageCon);

        discussCaseDetailDto.setImageList(imageList);

        //增加阅读量
        increaseReadCount(discussCaseDmo.getId());

        return discussCaseDetailDto;
    }

    @Override
    public DiscussCasePermissionResult checkDoctorCasePermission(Long recordId, Long userId) {

        DiscussCasePermissionResult result = new DiscussCasePermissionResult();

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(userId);
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询访问医生信息
        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);
        //医生信息不存在
        if (null == doctorMemberDto){

            result.fail("CDC-0000","医生信息不存在");
            return result;

        }

        DiscussCaseDmo discussCaseCon = new DiscussCaseDmo();

        discussCaseCon.setId(recordId);
        discussCaseCon.setStatus(DoctorConstants.CaseStatus.NORMAL);
        //查询病例信息
        DiscussCaseDmo discussCaseDmo = find(discussCaseCon);
        //病例信息不存在
        if (null == discussCaseDmo){

            result.fail("CDC-0001","病例信息不存在");
            return result;

        }
        //病例属于"1+1+1"讨论范围，且访问医生不属于该范围
        if (Constants.Range.ONE_BY_ONE_BY_ONE.equals(discussCaseDmo.getCaseRange())){
            //医生所属医院或者所属医院的顶级医院都不属于讨论范围
            if (!doctorMemberDto.getHospitalId().equals(discussCaseDmo.getRootHospitalId())&&!doctorMemberDto.getRootHospitalId().equals(discussCaseDmo.getRootHospitalId())){

                result.fail("CDC-0002","医生没有病例访问权限");
                return result;
            }

        }

        result.setPostDoctorId(discussCaseDmo.getUserId());
        result.setDoctorName(doctorMemberDto.getDoctorName());
        result.setType(discussCaseDmo.getType());
        result.setMainDesc(discussCaseDmo.getMainDesc());

        return result;
    }

    @Override
    public Result increaseReadCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("DiscussCaseMapper.increaseReadCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result increaseCollectCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("DiscussCaseMapper.increaseCollectCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result increaseLikeCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("DiscussCaseMapper.increaseLikeCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result increaseCommentCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("DiscussCaseMapper.increaseCommentCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result decreaseCollectCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("DiscussCaseMapper.decreaseCollectCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result decreaseLikeCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("DiscussCaseMapper.decreaseLikeCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result decreaseCommentCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("DiscussCaseMapper.decreaseCommentCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result deleteCase(Long recordId, Long userId) {

        Result result = new Result();

        DiscussCaseDmo discussCaseCon = new DiscussCaseDmo();

        discussCaseCon.setId(recordId);
        discussCaseCon.setStatus(DoctorConstants.CaseStatus.NORMAL);
        //查询病例信息
        DiscussCaseDmo discussCaseDmo = find(discussCaseCon);
        //病例信息不存在
        if (null == discussCaseDmo){

            result.fail("DLC-0000","病例信息不存在");
            return result;

        }
        //病例删除用户不是病例发布用户
        if (!discussCaseDmo.getUserId().equals(userId)){

            result.fail("DLC-0001","删除用户不是病例发布用户");
            return result;

        }

        discussCaseDmo.setStatus(DoctorConstants.CaseStatus.DELETE);

        result = update(discussCaseDmo);

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

        QueryDiscussCaseDto con = new QueryDiscussCaseDto();

        con.setUserId(doctorId);
        con.setCaseStatus(DoctorConstants.CaseStatus.NORMAL);

        Long count = commonDao.selectCount("DiscussCaseMapper.countCaseToday",con);

        return count.equals(1L);

    }
}
