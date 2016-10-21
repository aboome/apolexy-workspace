package com.yh.apoplexy.doctor.cases.discuss.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseLikeDmo;
import com.yjh.framework.lang.Result;

/**
 * 讨论病例点赞服务接口类
 * Created by wunder on 16/9/7 20:05.
 */
public interface CaseLikeService {

    /**
     * 新增病例点赞信息
     * @param caseLikeDmo
     * @return
     */
    public Result insert(CaseLikeDmo caseLikeDmo);

    /**
     * 更新病例点赞信息
     * @param caseLikeDmo
     * @return
     */
    public Result update(CaseLikeDmo caseLikeDmo);

    /**
     * 删除病例点赞信息
     * @param caseLikeDmo
     * @return
     */
    public Result delete(CaseLikeDmo caseLikeDmo);

    /**
     * 查找病例点赞信息
     * @param con
     * @return
     */
    public CaseLikeDmo find(CaseLikeDmo con);

    /**
     * 点赞讨论病例
     * @param caseLikeDmo
     * @return
     */
    public Result submitLike(CaseLikeDmo caseLikeDmo);

    /**
     * 取消点赞
     * @param caseLikeDmo
     * @return
     */
    public Result cancelLike(CaseLikeDmo caseLikeDmo);

    /**
     * 是否已点赞
     * @param caseLikeDmo
     * @return
     */
    public Result hasLike(CaseLikeDmo caseLikeDmo);

}
