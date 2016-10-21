package com.yh.apoplexy.admin.portal.common.controller.dto;

/**
 * 重置密码表单
 * @author eyelake
 *
 */
public class AdminUserResetPasswordForm {
	private String userId;
	private String ordlerPassWord;
	private String newPassWord;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrdlerPassWord() {
		return ordlerPassWord;
	}

	public void setOrdlerPassWord(String ordlerPassWord) {
		this.ordlerPassWord = ordlerPassWord;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

}
