/**
 * 
 */
package com.yh.apoplexy.common.constants;

/**
 * APP业务Key枚举
 * 
 * @author CC
 * 
 */
public enum AppServiceKeyEnum {

	/**
	 * 发送验证码
	 */
	SEND_VALIDATE_CODE("common-0001"),

	/**
	 * 验证验证码
	 */
	VERIFY_VALIDATE_CODE("common-0002"),

	/**
	 * 图片上传
	 */
	IMAGE_UPLOAD("common-0003"),

	/**
	 * 查看图片
	 */
	IMAGE_VIEW("common-0004"),

	/**
	 * 视频上传
	 */
	VIDEO_UPLOAD("common-0005"),

	/**
	 * 查看视频
	 */
	VIDEO_VIEW("common-0006"),

	/**
	 * 查询开机动画
	 */
	QUERY_START_PICTURE("common-0007"),

	/**
	 * 查询首页推荐位
	 */
	QUERY_INDEX_BANNER("common-0008"),

	/**
	 * 绑定客户端ID
	 */
	BIND_CLIENT_ID("common-0009"),

	/**
	 * 版本升级
	 */
	UPGRADE_VERSION("common-0010"),

	/**
	 * 患者注册
	 */
	PATIENT_REGISTER("pat-0001"),

	/**
	 * 患者登录
	 */
	PATIENT_LOGIN("pat-0002"),

	/**
	 * 患者找回密码
	 */
	PATIENT_FIND_PWD("pat-0003"),

	/**
	 * 患者重置密码
	 */
	PATIENT_RESET_PWD("pat-0004"),

	/**
	 * 提交高危筛查
	 */
	PATIENT_HEALTH_TEST("pat-0005"),

	/**
	 * 查询附近的筛查点
	 */
	QUERY_HEALTH_TEST_HOSPITAL("pat-0006"),

	/**
	 * 查询筛查点详情
	 */
	QUERY_HOSPITAL_DETAIL("pat-0007"),

	/**
	 * 提交体征数据
	 */
	PATIENT_HEALTH_DATA("pat-0008"),

	/**
	 * 查询健康数据趋势图
	 */
	PATIENT_QUERY_HEALTH_STATICS("pat-0009"),

	/**
	 * 查询宣教列表
	 */
	PATIENT_QUERY_NEWS("pat-0010"),

	/**
	 * 查询健康宣教详情
	 */
	PATIENT_QUERY_NEWS_DETAIL("pat-0011"),

	/**
	 * FAST自测表单
	 */
	PATIENT_FAST_TEST("pat-0012"),

	/**
	 * 查询附近的医院列表
	 */
	PATIENT_QUERY_HOSPITAL_LIST("pat-0013"),

	/**
	 * 查询医院详情
	 */
	PATIENT_QUERY_HOSPITAL_DETAIL("pat-0014"),

	/**
	 * 个人基本资料
	 */
	PATIENT_QUERY_MEMBER_INFO("pat-0015"),

	/**
	 * 修改个人基本资料
	 */
	PATIENT_MODIFY_MEMBER_INFO("pat-0016"),

	/**
	 * 查询个人积分信息
	 */
	PATIENT_QUERY_MEMBER_SCORE("pat-0017"),

	/**
	 * 查询个人积分明细
	 */
	PATIENT_QUERY_SCORE_DETAIL_LIST("pat-0018"),
	
	/**
	 * 修改个人密码
	 */
	PATIENT_MODIFY_PWD("pat-0019"),
	
	
	
	/**
	 * 医生注册
	 */
	DOCTOR_REGISTER("doc-0001"),

	/**
	 * 医生登录
	 */
	DOCTOR_LOGIN("doc-0002"),

	/**
	 * 医生找回密码
	 */
	DOCTOR_FIND_PWD("doc-0003"),

	/**
	 * 医生重置密码
	 */
	DOCTOR_RESET_PWD("doc-0004"),

	/**
	 * 查看病例讨论列表
	 */
	DOCTOR_QUERY_CASE_LIST("doc-0005"),

	/**
	 * 新增病例讨论
	 */
	DOCTOR_ADD_CASE("doc-0006"),

	/**
	 * 查看病例讨论详情
	 */
	DOCTOR_QUERY_CASE_DETAIL("doc-0007"),

	/**
	 * 病例讨论-收藏与取消收藏
	 */
	DOCTOR_CASE_COLLECTION_OR_CANCEL("doc-0008"),

	/**
	 * 病例讨论评论
	 */
	DOCTOR_CASE_COMMENT("doc-0009"),

	/**
	 * 病例讨论回复评论
	 */
	DOCTOR_CASE_REPLY_COMMENT("doc-0010"),

	/**
	 * 病例讨论-点赞与取消点赞
	 */
	DOCTOR_CASE_LIKE_OR_CANCEL("doc-0011"),

	/**
	 * 我的-我发布的列表
	 */
	DOCTOR_CASE_MY_LIST("doc-0012"),

	/**
	 * 我的-我发布的病例详情
	 */
	DOCTOR_CASE_MY_DETAIL("doc-0013"),

	/**
	 * 我的-删除病例讨论
	 */
	DOCTOR_DELETE_MY_CASE("doc-0014"),

	/**
	 * 我的-我参与的病例讨论列表
	 */
	DOCTOR_QUERY_MY_JOIN_LIST("doc-0015"),

	/**
	 * 我的-我参与的病例讨论详情
	 */
	DOCTOR_QUERY_MY_JOIN_DETAIL("doc-0016"),

	/**
	 * 我的-我收藏的病例讨论列表
	 */
	DOCTOR_QUERY_MY_LIKE_LIST("doc-0017"),

	/**
	 * 我的-我收藏的病例讨论详情
	 */
	DOCTOR_QUERY_MY_LIKE_DETAIL("doc-0018"),
	
	
	
	
	
	
	
	
	/**
	 * 查询转诊病例列表
	 */
	DOCTOR_QUERY_REFERRAL_CASE("doc-0019"),
	
	/**
	 * 新增转诊病例
	 */
	DOCTOR_ADD_REFERRAL_CASE("doc-0020"),
	
	/**
	 * 查询转诊病例详情
	 */
	DOCTOR_QUERY_REFERRAL_DETAIL("doc-0021"),
	
	/**
	 * 接诊
	 */
	DOCTOR_RECV_REFERRAL_CASE("doc-0022"),
	
	/**
	 * 我的-我的申请列表
	 */
	DOCTOR_QUERY_MY_APPLY_LIST("doc-0023"),
	
	/**
	 * 我的-我的申请详情
	 */
	DOCTOR_QUERY_MY_APPLY_DETAIL("doc-0024"),
	
	/**
	 * 我的-查询意向转诊医生列表
	 */
	DOCTOR_QUERY_RECV_LIST("doc-0025"),
	
	/**
	 * 我的-确认转诊
	 */
	DOCTOR_SURE_RECV_REFERRAL("doc-0026"),
	
	/**
	 * 我的-我的申请-撤销转诊
	 */
	DOCTOR_CANCEL_REFERRAL("doc-0027"),
	
	/**
	 * 我的-已被接诊列表
	 */
	DOCTOR_QUERY_REFERRALED_LIST("doc-0028"),
	
	/**
	 * 我的-已被接诊详情
	 */
	DOCTOR_QUERY_REFERRALED_DETAIL("doc-0029"),
	
	/**
	 * 我的-我的接诊列表
	 */
	DOCTOR_QUERY_MY_RECV_LIST("doc-0030"),
	
	/**
	 * 我的-我的接诊详情
	 */
	DOCTOR_QUERY_MY_RECV_DETAIL("doc-0031"),
	
	/**
	 * 我的-评分
	 */
	DOCTOR_SCORE_REFERRAL("doc-0032"),
	
	/**
	 * 我的-取消接诊
	 */
	DOCTOR_CANCEL_MY_RECV("doc-0033"),
	
	/**
	 * 查询AST病例列表
	 */
	DOCTOR_QUERY_AST_LIST("doc-0034"),
	
	/**
	 * 新增AST病例
	 */
	DOCTOR_ADD_AST("doc-0035"),
	
	/**
	 * 查询AST详情
	 */
	DOCTOR_QUERY_AST_DETAIL("doc-0037"),
	
	/**
	 * AST病例评论
	 */
	DOCTOR_AST_COMMENT("doc-0038"),
	
	/**
	 * AST病例回复评论
	 */
	DOCTOR_AST_REPLY_COMMENT("doc-0039"),
	
	/**
	 * AST病例点赞/取消点赞操作
	 */
	DOCTOR_AST_LIKE_OR_CANCEL("doc-0040"),
	
	/**
	 * AST病例收藏/取消收藏操作
	 */
	DOCTOR_AST_COLLECTION_OR_CANCEL("doc-0041"),
	
	/**
	 * 我的-我发布的AST病例列表
	 */
	DOCTOR_QUERY_MY_AST_LIST("doc-0042"),
	
	/**
	 * 我的-我发布的AST病例详情
	 */
	DOCTOR_QUERY_MY_AST_DETAIL("doc-0043"),
	
	/**
	 * 我的-撤销AST病例
	 */
	DOCTOR_CANCEL_MY_AST("doc-0044"),
	
	/**
	 * 我的-我参与的AST列表
	 */
	DOCTOR_QUERY_MY_AST_JOIN_LIST("doc-0045"),
	
	/**
	 * 我的-我参与的AST详情
	 */
	DOCTOR_QUERY_MY_AST_JOGIN_DETAIL("doc-0046"),
	
	/**
	 * 我的-我收藏的AST列表
	 */
	DOCTOR_QUERY_MY_AST_COLLECTION_LIST("doc-0047"),
	
	/**
	 * 我的-我收藏的AST详情
	 */
	DOCTOR_QUERY_MY_AST_COLLECTION_DETAIL("doc-0048"),
	
	/**
	 * 查询学术更新列表
	 */
	DOCTOR_QUERY_KNOWLEDGE_LIST("doc-0049"),
	
	/**
	 * 查询学术更新详情
	 */
	DOCTOR_QUERY_KNOWLEDGE_DETAIL("doc-0050"),
	
	/**
	 * 查询最新资讯列表
	 */
	DOCTOR_QUERY_NEWS_LIST("doc-0051"),
	
	/**
	 * 查询最新资讯详情
	 */
	DOCTOR_QUERY_NEWS_DETAIL("doc-0052"),
	
	/**
	 * 查询个人资料
	 */
	DOCTOR_QUERY_MEMBER_INFO("doc-0053"),
	
	/**
	 * 查询工作岗位
	 */
	DOCTOR_QUERY_MEMBER_JOB("doc-0054"),
	
	/**
	 * 修改工作岗位
	 */
	DOCTOR_MODIFY_MEMBER_JOB("doc-0055"),
	
	/**
	 * 查询积分详情
	 */
	DOCTOR_QUERY_MEMBER_SCORE("doc-0056"),
	
	/**
	 * 查询积分明细列表
	 */
	DOCTOR_QUERY_SCORE_LIST("doc-0057"),
	
	/**
	 * 修改登录密码
	 */
	DOCTOR_MODIFY_PWD("doc-0058"),
	
	/**
	 * 查询通知消息列表
	 */
	DOCTOR_QUERY_MESSAGE_LIST("doc-0059"),
	
	/**
	 * 查询医生详情
	 */
	DOCTOR_QUERY_DOCTOR_DETAIL("doc-0061");
	
	/**
	 * 业务Key
	 */
	private String serviceKey;

	/**
	 * 枚举类构造函数
	 * @param serviceKey
	 */
	private AppServiceKeyEnum(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	
	
}
