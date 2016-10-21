package com.yh.apoplexy.doctor.cases.ast.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 既往史服务接口类
 * Created by wunder on 16/9/10 16:46.
 */
public interface AstHistoryService {

    /**
     * 新增既往史信息
     * @param astHistoryDmo
     * @return
     */
    public Result insert(AstHistoryDmo astHistoryDmo);

    /**
     * 批量插入既往史信息
     * @param astCaseDetailDto
     * @return
     */
    public Result batchInsert(AstCaseDetailDto astCaseDetailDto);

    /**
     * 查找既往史信息
     * @param con
     * @return
     */
    public AstHistoryDmo find(AstHistoryDmo con);

    /**
     * 查询既往史列表
     * @param con
     * @return
     */
    public List<AstHistoryDmo> selectList(AstHistoryDmo con);

}
