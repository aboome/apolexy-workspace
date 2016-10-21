package com.yh.apoplexy.doctor.cases.ast.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstResourcesDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * AST病例资源服务接口类
 * Created by wunder on 16/9/10 16:35.
 */
public interface AstResourceService {

    /**
     * 新增病例资源信息
     * @param astResourcesDmo
     * @return
     */
    public Result insert(AstResourcesDmo astResourcesDmo);

    /**
     * 批量插入病例资源信息
     * @param astCaseDetailDto
     * @return
     */
    public Result batchInsert(AstCaseDetailDto astCaseDetailDto);

    /**
     * 查找病例资源信息
     * @param con
     * @return
     */
    public AstResourcesDmo find(AstResourcesDmo con);

    /**
     * 查询病例资源列表
     * @param con
     * @return
     */
    public List<AstResourcesDmo> selectList(AstResourcesDmo con);
}
