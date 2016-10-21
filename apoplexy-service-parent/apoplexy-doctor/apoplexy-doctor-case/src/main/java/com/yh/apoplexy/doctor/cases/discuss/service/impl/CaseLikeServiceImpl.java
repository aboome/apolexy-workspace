package com.yh.apoplexy.doctor.cases.discuss.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseLikeDmo;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.CaseLikeService;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 讨论病例点赞服务实现类
 * Created by wunder on 16/9/7 20:18.
 */
@Service("caseLikeService")
@ServiceTrace
public class CaseLikeServiceImpl implements CaseLikeService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DiscussCaseService discussCaseService;

    @Override
    public Result insert(CaseLikeDmo caseLikeDmo) {

        int i = commonDao.insert(caseLikeDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(CaseLikeDmo caseLikeDmo) {

        int i = commonDao.update(caseLikeDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(CaseLikeDmo caseLikeDmo) {

        int i = commonDao.delete(caseLikeDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public CaseLikeDmo find(CaseLikeDmo con) {
        return (CaseLikeDmo) commonDao.selectOne(con);
    }

    @Override
    @Transactional
    public Result submitLike(CaseLikeDmo caseLikeDmo) {

        Result result = new Result();

        //检查点赞医生是否拥有该病例的访问权限
        result = discussCaseService.checkDoctorCasePermission(caseLikeDmo.getRecordId(), caseLikeDmo.getUserId());

        if (!result.isSuccess()) {

            result.fail();
            return result;

        }

        CaseLikeDmo likeCon = new CaseLikeDmo();

        likeCon.setUserId(caseLikeDmo.getUserId());
        likeCon.setRecordId(caseLikeDmo.getRecordId());
        //查询点赞关系信息
        CaseLikeDmo existCaseLikeDmo = find(likeCon);
        //点赞关系信息已存在
        if (null != existCaseLikeDmo) {
            //点赞关系信息为正常状态，已经收藏不能再次点赞
            if (DoctorConstants.CaseLikeStatus.NORMAL.equals(existCaseLikeDmo.getStatus())) {

                result.fail();
                return result;

            }
            //点赞关系信息为删除状态，更新点赞关系信息为正常
            if (DoctorConstants.CaseLikeStatus.DELETE.equals(existCaseLikeDmo.getStatus())) {

                existCaseLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.NORMAL);
                existCaseLikeDmo.setCreateTime(DateUtil.getDate());

                result = update(existCaseLikeDmo);
                //更新失败，事务回滚
                if (!result.isSuccess()) {

                    throw new AppException(result);

                }

            }

        } else {
            //点赞关系信息不存在,新增点赞关系
            caseLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.NORMAL);
            caseLikeDmo.setCreateTime(DateUtil.getDate());

            result = insert(caseLikeDmo);
            //新增失败，事务回滚
            if (!result.isSuccess()) {

                throw new AppException(result);

            }
        }
        //增加病例信息的点赞数量
        result = discussCaseService.increaseLikeCount(caseLikeDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;

    }

    @Override
    @Transactional
    public Result cancelLike(CaseLikeDmo caseLikeDmo) {

        Result result = new Result();

        //检查取消点赞医生是否拥有该病例的访问权限
        result = discussCaseService.checkDoctorCasePermission(caseLikeDmo.getRecordId(), caseLikeDmo.getUserId());

        if (!result.isSuccess()) {

            result.fail();
            return result;

        }

        CaseLikeDmo likeCon = new CaseLikeDmo();

        likeCon.setUserId(caseLikeDmo.getUserId());
        likeCon.setRecordId(caseLikeDmo.getRecordId());
        //查询点赞关系信息
        CaseLikeDmo existCaseLikeDmo = find(likeCon);
        //点赞关系信息不存在
        if (null == existCaseLikeDmo || DoctorConstants.CaseLikeStatus.DELETE.equals(existCaseLikeDmo.getStatus())) {

            result.fail("CDL-0000", "点赞关系不存在");
            return result;

        }
        existCaseLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.DELETE);
        existCaseLikeDmo.setCreateTime(DateUtil.getDate());

        result = update(existCaseLikeDmo);
        //更新失败，事务回滚
        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        //减少病例信息的点赞数量
        result = discussCaseService.decreaseLikeCount(caseLikeDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;
    }

    @Override
    public Result hasLike(CaseLikeDmo caseLikeDmo) {

        Result result = new Result();

        caseLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.NORMAL);

        CaseLikeDmo existCaseLike = find(caseLikeDmo);

        if (null == existCaseLike){
            result.setSuccess(false);
            return result;
        }

        result.setSuccess(true);
        return result;

    }
}
