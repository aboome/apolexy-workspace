package com.yh.apoplexy.doctor.cases.ast.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCollectionDmo;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseCollectService;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AST病例收藏服务实现类
 * Created by wunder on 16/9/10 16:09.
 */
@Service("astCaseCollectService")
@ServiceTrace
public class AstCaseCollectServiceImpl implements AstCaseCollectService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AstCaseService astCaseService;

    @Override
    public Result insert(AstCollectionDmo astCollectionDmo) {

        int i = commonDao.insert(astCollectionDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(AstCollectionDmo astCollectionDmo) {

        int i = commonDao.update(astCollectionDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(AstCollectionDmo astCollectionDmo) {

        int i = commonDao.delete(astCollectionDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public AstCollectionDmo find(AstCollectionDmo con) {

        return (AstCollectionDmo) commonDao.selectOne(con);

    }

    @Override
    @Transactional
    public Result submitCollect(AstCollectionDmo astCollectionDmo) {

        Result result = new Result();

        AstCasesDmo astCasesCon = new AstCasesDmo();

        astCasesCon.setId(astCollectionDmo.getRecordId());

        AstCasesDmo astCasesDmo = astCaseService.find(astCasesCon);
        //AST病例信息不存在
        if (null == astCasesDmo||!DoctorConstants.CaseStatus.NORMAL.equals(astCasesDmo.getStatus())){

            result.fail("SCC-0000","提交评论失败");
            return result;

        }

        AstCollectionDmo collectionCon = new AstCollectionDmo();

        collectionCon.setUserId(astCollectionDmo.getUserId());
        collectionCon.setRecordId(astCollectionDmo.getRecordId());
        //查询收藏关系信息
        AstCollectionDmo existAstCollectDmo = find(collectionCon);
        //收藏关系信息已存在
        if (null != existAstCollectDmo) {
            //收藏关系信息为正常状态，已经收藏不能再次收藏
            if (DoctorConstants.CaseCollectionStatus.NORMAL.equals(existAstCollectDmo.getStatus())) {

                result.fail("SAC-0000", "收藏关系已存在，不能再次收藏");
                return result;

            }
            //收藏关系信息为删除状态，更新收藏关系信息为正常
            if (DoctorConstants.CaseCollectionStatus.DELETE.equals(existAstCollectDmo.getStatus())) {

                existAstCollectDmo.setStatus(DoctorConstants.CaseCollectionStatus.NORMAL);
                existAstCollectDmo.setCreateTime(DateUtil.getDate());

                result = update(existAstCollectDmo);
                //更新失败，事务回滚
                if (!result.isSuccess()) {

                    throw new AppException(result);

                }

            }

        } else {
            //收藏关系信息不存在,新增收藏关系
            astCollectionDmo.setStatus(DoctorConstants.CaseCollectionStatus.NORMAL);
            astCollectionDmo.setCreateTime(DateUtil.getDate());

            result = insert(astCollectionDmo);
            //新增失败，事务回滚
            if (!result.isSuccess()) {

                throw new AppException(result);

            }
        }
        //增加病例信息的收藏数量
        result = astCaseService.increaseCollectCount(astCollectionDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;
    }

    @Override
    @Transactional
    public Result cancelCollect(AstCollectionDmo astCollectionDmo) {

        Result result = new Result();

        AstCasesDmo astCasesCon = new AstCasesDmo();

        astCasesCon.setId(astCollectionDmo.getRecordId());

        AstCasesDmo astCasesDmo = astCaseService.find(astCasesCon);
        //AST病例信息不存在
        if (null == astCasesDmo||!DoctorConstants.CaseStatus.NORMAL.equals(astCasesDmo.getStatus())){

            result.fail("SCC-0000","提交评论失败");
            return result;

        }

        AstCollectionDmo collectionCon = new AstCollectionDmo();

        collectionCon.setUserId(astCollectionDmo.getUserId());
        collectionCon.setRecordId(astCollectionDmo.getRecordId());
        //查询收藏关系信息
        AstCollectionDmo existAstCollectDmo = find(collectionCon);
        //收藏关系信息不存在或者状态为删除状态
        if (null == existAstCollectDmo || DoctorConstants.CaseCollectionStatus.DELETE.equals(existAstCollectDmo.getStatus())) {

            result.fail("CAC-0000", "收藏关系不存在");
            return result;

        }

        existAstCollectDmo.setStatus(DoctorConstants.CaseCollectionStatus.DELETE);
        existAstCollectDmo.setCreateTime(DateUtil.getDate());

        result = update(existAstCollectDmo);
        //更新失败，事务回滚
        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        //减少病例信息的收藏数量
        result = astCaseService.decreaseCollectCount(astCollectionDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;

    }

    @Override
    public Result hasCollect(AstCollectionDmo astCollectionDmo) {

        Result result = new Result();

        astCollectionDmo.setStatus(DoctorConstants.CaseCollectionStatus.NORMAL);

        AstCollectionDmo existAstCollect = find(astCollectionDmo);

        if (null == existAstCollect){

            result.setSuccess(false);
            return result;
        }

        result.setSuccess(true);
        return result;
    }
}
