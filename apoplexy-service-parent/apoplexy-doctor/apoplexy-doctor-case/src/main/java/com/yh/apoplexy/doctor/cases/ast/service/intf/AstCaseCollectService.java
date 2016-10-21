package com.yh.apoplexy.doctor.cases.ast.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCollectionDmo;
import com.yjh.framework.lang.Result;

/**
 * AST病例收藏服务接口类
 * Created by wunder on 16/9/10 16:07.
 */
public interface AstCaseCollectService {

    /**
     * 新增病例收藏信息
     * @param astCollectionDmo
     * @return
     */
    public Result insert(AstCollectionDmo astCollectionDmo);

    /**
     * 更新病例收藏信息
     * @param astCollectionDmo
     * @return
     */
    public Result update(AstCollectionDmo astCollectionDmo);

    /**
     * 删除病例收藏信息
     * @param astCollectionDmo
     * @return
     */
    public Result delete(AstCollectionDmo astCollectionDmo);

    /**
     * 查找病例收藏信息
     * @param con
     * @return
     */
    public AstCollectionDmo find(AstCollectionDmo con);

    /**
     * 收藏病例
     * @param astCollectionDmo
     * @return
     */
    public Result submitCollect(AstCollectionDmo astCollectionDmo);

    /**
     * 取消收藏
     * @param astCollectionDmo
     * @return
     */
    public Result cancelCollect(AstCollectionDmo astCollectionDmo);

    /**
     * 是否已收藏
     * @param astCollectionDmo
     * @return
     */
    public Result hasCollect(AstCollectionDmo astCollectionDmo);
}
