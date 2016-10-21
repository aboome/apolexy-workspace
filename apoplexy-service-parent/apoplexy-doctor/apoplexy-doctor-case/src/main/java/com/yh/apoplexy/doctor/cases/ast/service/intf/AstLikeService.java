package com.yh.apoplexy.doctor.cases.ast.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstLikeDmo;
import com.yjh.framework.lang.Result;

/**
 * AST病例点赞服务接口类
 * Created by wunder on 16/9/10 16:23.
 */
public interface AstLikeService {

    /**
     * 新增病例点赞信息
     * @param astLikeDmo
     * @return
     */
    public Result insert(AstLikeDmo astLikeDmo);

    /**
     * 更新病例点赞信息
     * @param astLikeDmo
     * @return
     */
    public Result update(AstLikeDmo astLikeDmo);

    /**
     * 删除病例点赞信息
     * @param astLikeDmo
     * @return
     */
    public Result delete(AstLikeDmo astLikeDmo);

    /**
     * 查找病例点赞信息
     * @param con
     * @return
     */
    public AstLikeDmo find(AstLikeDmo con);

    /**
     * 点赞讨论病例
     * @param astLikeDmo
     * @return
     */
    public Result submitLike(AstLikeDmo astLikeDmo);

    /**
     * 取消点赞
     * @param astLikeDmo
     * @return
     */
    public Result cancelLike(AstLikeDmo astLikeDmo);

    /**
     * 是否已点赞
     * @param astLikeDmo
     * @return
     */
    public Result hasLike(AstLikeDmo astLikeDmo);

}
