package com.yh.apoplexy.admin.base.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.BannerInfoDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

public interface AdminBannerInfoService {

	/**
	 * 查询主页推荐位列表
	 * @param bannerInfoDmo
	 * @return
	 */
	public List<BannerInfoDmo> selectList(BannerInfoDmo bannerInfoDmo);
	
	/**
	 * 新增主页推荐位
	 * @param bannerInfoDmo
	 * @return
	 */
	public Result addBanner(BannerInfoDmo bannerInfoDmo);
	
	/**
	 * 修改主页推荐位
	 * @param bannerInfoDmo
	 * @return
	 */
	public Result updateBanner(BannerInfoDmo bannerInfoDmo);
	
	/**
	 * 删除主页推荐位
	 * @param bannerInfoDmo
	 * @return
	 */
	public Result deleteBanner(BannerInfoDmo bannerInfoDmo);
	
}
