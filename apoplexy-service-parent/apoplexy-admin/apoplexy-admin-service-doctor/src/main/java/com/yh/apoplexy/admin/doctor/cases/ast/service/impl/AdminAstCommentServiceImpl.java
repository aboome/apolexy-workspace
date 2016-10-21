package com.yh.apoplexy.admin.doctor.cases.ast.service.impl;

import com.yh.apoplexy.admin.doctor.cases.ast.service.intf.AdminAstCommentService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminAstCommentDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.ast.AdminChildAstCommentDto;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wunder on 16/9/27 14:28.
 */
@Service("adminAstCommentService")
@ServiceTrace
public class AdminAstCommentServiceImpl implements AdminAstCommentService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(AstCasesCommentDmo astCasesCommentDmo) {

        int i = commonDao.insert(astCasesCommentDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(AstCasesCommentDmo astCasesCommentDmo) {

        int i = commonDao.update(astCasesCommentDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(AstCasesCommentDmo astCasesCommentDmo) {

        int i = commonDao.delete(astCasesCommentDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public AstCasesCommentDmo find(AstCasesCommentDmo con) {
        return (AstCasesCommentDmo)commonDao.selectOne(con);
    }

    @Override
    public List<AdminAstCommentDto> queryCommentList(AstCasesCommentDmo con) {

        con.setType(DoctorConstants.CommentType.PARENT_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        List<AdminAstCommentDto> astCommentDtoList = commonDao.selectList("AdminAstCommentMapper.queryComment",con);

        if (CollectionUtils.isEmpty(astCommentDtoList)){

            return astCommentDtoList;

        }

        for (AdminAstCommentDto astCommentDto: astCommentDtoList){

            AstCasesCommentDmo childCon = new AstCasesCommentDmo();

            childCon.setParentId(astCommentDto.getDiscussId());
            childCon.setStatus(DoctorConstants.CommentStatus.NORMAL);

            List<AdminChildAstCommentDto> childAstCommentDtoList = queryChildCommentList(childCon);

            astCommentDto.setChildDiscussList(childAstCommentDtoList);

        }

        return astCommentDtoList;
    }

    @Override
    public List<AdminChildAstCommentDto> queryChildCommentList(AstCasesCommentDmo con) {

        con.setType(DoctorConstants.CommentType.CHILD_COMMENT);
        con.setStatus(DoctorConstants.CommentStatus.NORMAL);

        return commonDao.selectList("AdminAstCommentMapper.queryChildComment",con);
    }

    @Override
    public Long countChildComment(AstCasesCommentDmo con) {
        return commonDao.selectCount("AdminAstCommentMapper.countChildComment",con);

    }
}
