package com.yh.apoplexy.admin.doctor.cases.discuss.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 讨论病例资源服务接口类
 * Created by wunder on 16/9/7 15:11.
 */
public interface AdminCaseResourceService {

    /**
     * 新增讨论病例资源信息
     * @param caseResourceDmo
     * @return
     */
    public Result insert(CaseResourceDmo caseResourceDmo);

    /**
     * 查找讨论病例资源信息
     * @param con
     * @return
     */
    public CaseResourceDmo find(CaseResourceDmo con);

    /**
     * 查询讨论病例资源列表
     * @param con
     * @return
     */
    public List<CaseResourceDmo> selectList(CaseResourceDmo con);
}
