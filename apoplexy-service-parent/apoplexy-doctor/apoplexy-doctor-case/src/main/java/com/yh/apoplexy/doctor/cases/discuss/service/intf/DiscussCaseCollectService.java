package com.yh.apoplexy.doctor.cases.discuss.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCollectionDmo;
import com.yjh.framework.lang.Result;

/**
 * 讨论病例收藏服务接口类
 * Created by wunder on 16/9/7 16:35.
 */
public interface DiscussCaseCollectService {

    /**
     * 新增讨论病例收藏信息
     * @param caseCollectionDmo
     * @return
     */
    public Result insert(CaseCollectionDmo caseCollectionDmo);

    /**
     * 更新讨论病例收藏信息
     * @param caseCollectionDmo
     * @return
     */
    public Result update(CaseCollectionDmo caseCollectionDmo);

    /**
     * 删除讨论病例收藏信息
     * @param caseCollectionDmo
     * @return
     */
    public Result delete(CaseCollectionDmo caseCollectionDmo);

    /**
     * 查找讨论病例收藏信息
     * @param con
     * @return
     */
    public CaseCollectionDmo find(CaseCollectionDmo con);

    /**
     * 收藏讨论病例
     * @param caseCollectionDmo
     * @return
     */
    public Result submitCollect(CaseCollectionDmo caseCollectionDmo);

    /**
     * 取消收藏
     * @param caseCollectionDmo
     * @return
     */
    public Result cancelCollect(CaseCollectionDmo caseCollectionDmo);

    /**
     * 是否已收藏
     * @param caseCollectionDmo
     * @return
     */
    public Result hasCollect(CaseCollectionDmo caseCollectionDmo);
}
