package com.yh.apoplexy.assist.dto.patient.health;

import com.yjh.framework.core.entity.Entity;
/**
 * 查询中风急救详情
 * @author zhangbiao
 *
 */
public class patientZfDto extends Entity {
 
	private static final long serialVersionUID = -4643916175239968590L;
	
	private String question;
	
	private String result;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
   	
   
}
