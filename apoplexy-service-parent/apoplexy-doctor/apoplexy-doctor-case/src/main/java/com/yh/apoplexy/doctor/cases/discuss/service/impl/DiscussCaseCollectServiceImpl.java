package com.yh.apoplexy.doctor.cases.discuss.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCollectionDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseCollectService;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 讨论病例收藏服务实现类
 * Created by wunder on 16/9/7 16:42.
 */
@Service("discussCaseCollectService")
@ServiceTrace
public class DiscussCaseCollectServiceImpl implements DiscussCaseCollectService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DiscussCaseService discussCaseService;

    @Override
    public Result insert(CaseCollectionDmo caseCollectionDmo) {

        int i = commonDao.insert(caseCollectionDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(CaseCollectionDmo caseCollectionDmo) {

        int i = commonDao.update(caseCollectionDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(CaseCollectionDmo caseCollectionDmo) {

        int i = commonDao.delete(caseCollectionDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public CaseCollectionDmo find(CaseCollectionDmo con) {

        return (CaseCollectionDmo) commonDao.selectOne(con);

    }

    @Override
    @Transactional
    public Result submitCollect(CaseCollectionDmo caseCollectionDmo) {

        Result result = new Result();

        //检查收藏医生是否拥有该病例的访问权限
        result = discussCaseService.checkDoctorCasePermission(caseCollectionDmo.getRecordId(), caseCollectionDmo.getUserId());

        if (!result.isSuccess()) {

            result.fail();
            return result;

        }

        CaseCollectionDmo collectionCon = new CaseCollectionDmo();

        collectionCon.setUserId(caseCollectionDmo.getUserId());
        collectionCon.setRecordId(caseCollectionDmo.getRecordId());
        //查询收藏关系信息
        CaseCollectionDmo existCaseCollectDmo = find(collectionCon);
        //收藏关系信息已存在
        if (null != existCaseCollectDmo) {
            //收藏关系信息为正常状态，已经收藏不能再次收藏
            if (DoctorConstants.CaseCollectionStatus.NORMAL.equals(existCaseCollectDmo.getStatus())) {

                result.fail();
                return result;

            }
            //收藏关系信息为删除状态，更新收藏关系信息为正常
            if (DoctorConstants.CaseCollectionStatus.DELETE.equals(existCaseCollectDmo.getStatus())) {

                existCaseCollectDmo.setStatus(DoctorConstants.CaseCollectionStatus.NORMAL);
                existCaseCollectDmo.setCreateTime(DateUtil.getDate());

                result = update(existCaseCollectDmo);
                //更新失败，事务回滚
                if (!result.isSuccess()) {

                    throw new AppException(result);

                }

            }

        } else {
            //收藏关系信息不存在,新增收藏关系
            caseCollectionDmo.setStatus(DoctorConstants.CaseCollectionStatus.NORMAL);
            caseCollectionDmo.setCreateTime(DateUtil.getDate());

            result = insert(caseCollectionDmo);
            //新增失败，事务回滚
            if (!result.isSuccess()) {

                throw new AppException(result);

            }
        }
        //增加病例信息的收藏数量
        result = discussCaseService.increaseCollectCount(caseCollectionDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;
    }

    @Override
    @Transactional
    public Result cancelCollect(CaseCollectionDmo caseCollectionDmo) {

        Result result = new Result();

        //检查取消收藏医生是否拥有该病例的访问权限
        result = discussCaseService.checkDoctorCasePermission(caseCollectionDmo.getRecordId(), caseCollectionDmo.getUserId());

        if (!result.isSuccess()) {

            result.fail();
            return result;

        }

        CaseCollectionDmo collectionCon = new CaseCollectionDmo();

        collectionCon.setUserId(caseCollectionDmo.getUserId());
        collectionCon.setRecordId(caseCollectionDmo.getRecordId());
        //查询收藏关系信息
        CaseCollectionDmo existCaseCollectDmo = find(collectionCon);
        //收藏关系信息不存在或者状态为已删除
        if (null == existCaseCollectDmo || DoctorConstants.CaseCollectionStatus.DELETE.equals(existCaseCollectDmo.getStatus())) {

            result.fail("CDC-0000", "收藏关系不存在");
            return result;

        }

        existCaseCollectDmo.setStatus(DoctorConstants.CaseCollectionStatus.DELETE);
        existCaseCollectDmo.setCreateTime(DateUtil.getDate());

        result = update(existCaseCollectDmo);
        //更新失败，事务回滚
        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        //减少病例信息的收藏数量
        result = discussCaseService.decreaseCollectCount(caseCollectionDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;

    }

    @Override
    public Result hasCollect(CaseCollectionDmo caseCollectionDmo) {

        Result result = new Result();

        caseCollectionDmo.setStatus(DoctorConstants.CaseCollectionStatus.NORMAL);

        CaseCollectionDmo existCaseCollect = find(caseCollectionDmo);

        if (null == existCaseCollect){

            result.setSuccess(false);
            return result;
        }

        result.setSuccess(true);
        return result;
    }
}
