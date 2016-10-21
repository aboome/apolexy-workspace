package com.yh.apoplexy.doctor.cases.ast.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 既往用药服务接口类
 * Created by wunder on 16/9/10 16:46.
 */
public interface AstMedicationService {

    /**
     * 新增既往用药信息
     * @param astMedicationDmo
     * @return
     */
    public Result insert(AstMedicationDmo astMedicationDmo);

    /**
     * 批量插入既往用药信息
     * @param astCaseDetailDto
     * @return
     */
    public Result batchInsert(AstCaseDetailDto astCaseDetailDto);

    /**
     * 查找既往用药信息
     * @param con
     * @return
     */
    public AstMedicationDmo find(AstMedicationDmo con);

    /**
     * 查询既往用药列表
     * @param con
     * @return
     */
    public List<AstMedicationDmo> selectList(AstMedicationDmo con);

}
