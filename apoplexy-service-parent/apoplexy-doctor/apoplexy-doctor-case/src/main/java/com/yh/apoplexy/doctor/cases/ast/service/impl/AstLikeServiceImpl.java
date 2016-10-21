package com.yh.apoplexy.doctor.cases.ast.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstLikeDmo;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstLikeService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AST病例点赞服务实现类
 * Created by wunder on 16/9/10 16:25.
 */
@Service("astLikeService")
@ServiceTrace
public class AstLikeServiceImpl implements AstLikeService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AstCaseService astCaseService;

    @Override
    public Result insert(AstLikeDmo astLikeDmo) {

        int i = commonDao.insert(astLikeDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(AstLikeDmo astLikeDmo) {

        int i = commonDao.update(astLikeDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(AstLikeDmo astLikeDmo) {

        int i = commonDao.delete(astLikeDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public AstLikeDmo find(AstLikeDmo con) {
        return (AstLikeDmo)commonDao.selectOne(con);
    }

    @Override
    @Transactional
    public Result submitLike(AstLikeDmo astLikeDmo) {

        Result result = new Result();

        AstCasesDmo astCasesCon = new AstCasesDmo();

        astCasesCon.setId(astLikeDmo.getRecordId());

        AstCasesDmo astCasesDmo = astCaseService.find(astCasesCon);
        //AST病例信息不存在
        if (null == astCasesDmo||!DoctorConstants.CaseStatus.NORMAL.equals(astCasesDmo.getStatus())){

            result.fail("SCC-0000","提交评论失败");
            return result;

        }

        AstLikeDmo likeCon = new AstLikeDmo();

        likeCon.setUserId(astLikeDmo.getUserId());
        likeCon.setRecordId(astLikeDmo.getRecordId());
        //查询点赞关系信息
        AstLikeDmo existAstLikeDmo = find(likeCon);
        //点赞关系信息已存在
        if (null != existAstLikeDmo){
            //点赞关系信息为正常状态，已经收藏不能再次点赞
            if (DoctorConstants.CaseLikeStatus.NORMAL.equals(existAstLikeDmo.getStatus())){

                result.fail("SAL-0000","点赞信息已存在，不能再次点赞");
                return result;

            }
            //点赞关系信息为删除状态，更新点赞关系信息为正常
            if (DoctorConstants.CaseLikeStatus.DELETE.equals(existAstLikeDmo.getStatus())){

                existAstLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.NORMAL);
                existAstLikeDmo.setCreateTime(DateUtil.getDate());

                result = update(existAstLikeDmo);
                //更新失败，事务回滚
                if (!result.isSuccess()){

                    throw new AppException(result);

                }

            }

        }else {
            //点赞关系信息不存在,新增点赞关系
            astLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.NORMAL);
            astLikeDmo.setCreateTime(DateUtil.getDate());

            result = insert(astLikeDmo);
            //新增失败，事务回滚
            if (!result.isSuccess()){

                throw new AppException(result);

            }
        }
        //增加病例信息的点赞数量
        result = astCaseService.increaseLikeCount(astLikeDmo.getRecordId());

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        return result;

    }

    @Override
    @Transactional
    public Result cancelLike(AstLikeDmo astLikeDmo) {

        Result result = new Result();

        AstCasesDmo astCasesCon = new AstCasesDmo();

        astCasesCon.setId(astLikeDmo.getRecordId());

        AstCasesDmo astCasesDmo = astCaseService.find(astCasesCon);
        //AST病例信息不存在
        if (null == astCasesDmo||!DoctorConstants.CaseStatus.NORMAL.equals(astCasesDmo.getStatus())){

            result.fail("SCC-0000","提交评论失败");
            return result;

        }

        AstLikeDmo likeCon = new AstLikeDmo();

        likeCon.setUserId(astLikeDmo.getUserId());
        likeCon.setRecordId(astLikeDmo.getRecordId());
        //查询点赞关系信息
        AstLikeDmo existLikeDmo = find(likeCon);
        //点赞关系信息不存在
        if (null == existLikeDmo || DoctorConstants.CaseLikeStatus.DELETE.equals(existLikeDmo.getStatus())) {

            result.fail("CDL-0000", "点赞关系不存在");
            return result;

        }
        existLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.DELETE);
        existLikeDmo.setCreateTime(DateUtil.getDate());

        result = update(existLikeDmo);
        //更新失败，事务回滚
        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        //减少病例信息的点赞数量
        result = astCaseService.decreaseLikeCount(astLikeDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;
    }

    @Override
    public Result hasLike(AstLikeDmo astLikeDmo) {

        Result result = new Result();

        astLikeDmo.setStatus(DoctorConstants.CaseLikeStatus.NORMAL);

        AstLikeDmo existAstLike = find(astLikeDmo);

        if (null == existAstLike){
            result.setSuccess(false);
            return result;
        }

        result.setSuccess(true);
        return result;

    }
}
