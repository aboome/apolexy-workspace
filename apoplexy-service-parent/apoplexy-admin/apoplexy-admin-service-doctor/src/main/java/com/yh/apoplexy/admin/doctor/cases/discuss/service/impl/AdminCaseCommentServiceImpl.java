package com.yh.apoplexy.admin.doctor.cases.discuss.service.impl;

import com.yh.apoplexy.admin.doctor.cases.discuss.service.intf.AdminCaseCommentService;
import com.yh.apoplexy.admin.doctor.cases.discuss.service.intf.AdminDoctorCasesService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminChildDiscussCommentDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss.AdminDiscussCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.CaseCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.ChildCaseCommentDto;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 讨论病例评论服务实现类
 * Created by wunder on 16/9/7 18:29.
 */
@Service("adminCaseCommentService")
@ServiceTrace
public class AdminCaseCommentServiceImpl implements AdminCaseCommentService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AdminDoctorCasesService adminDoctorCasesService;

    @Override
    public Result insert(CaseCommentDmo caseCommentDmo) {

        int i = commonDao.insert(caseCommentDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(CaseCommentDmo caseCommentDmo) {

        int i = commonDao.update(caseCommentDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(CaseCommentDmo caseCommentDmo) {

        int i = commonDao.delete(caseCommentDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public CaseCommentDmo find(CaseCommentDmo con) {
        return (CaseCommentDmo)commonDao.selectOne(con);
    }

    @Override
    public List<AdminDiscussCommentDto> queryCommentList(CaseCommentDmo con) {

        con.setType(DoctorConstants.CommentType.PARENT_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        List<AdminDiscussCommentDto> caseCommentDtoList = commonDao.selectList("AdminDiscussCommentMapper.queryComment",con);

        if (CollectionUtils.isEmpty(caseCommentDtoList)){

            return caseCommentDtoList;

        }

        for (AdminDiscussCommentDto caseCommentDto: caseCommentDtoList){

            CaseCommentDmo childCon = new CaseCommentDmo();

            childCon.setParentId(caseCommentDto.getDiscussId());
            childCon.setStatus(DoctorConstants.CommentStatus.NORMAL);

            List<AdminChildDiscussCommentDto> childCaseCommentDtoList = queryChildCommentList(childCon);

            caseCommentDto.setChildDiscussList(childCaseCommentDtoList);

        }

        return caseCommentDtoList;

    }

    @Override
    public List<AdminChildDiscussCommentDto> queryChildCommentList(CaseCommentDmo con) {

        con.setType(DoctorConstants.CommentType.CHILD_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        return commonDao.selectList("AdminDiscussCommentMapper.queryChildComment",con);
    }

    @Override
    public Long countChildComment(CaseCommentDmo con) {
        return commonDao.selectCount("AdminDiscussCommentMapper.countChildComment",con);
    }

}
