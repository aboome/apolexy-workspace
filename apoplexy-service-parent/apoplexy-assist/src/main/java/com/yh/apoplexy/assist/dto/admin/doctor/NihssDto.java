package com.yh.apoplexy.assist.dto.admin.doctor;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;
/**
 * 
 * nihss的信息
 * @author zhangbiao
 *
 */
public class NihssDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8777288089593404870L;

	private Long id;
	
    private String type;
    
    private Long userId;
    
    private Long recordId;
    
    private String result;
    
    private Date createTime;
    
    private String question;
    
    private String answerOne;
    
    private String answerTwo;
    
    private String answerThree;
    
    private String answerFour;
    
    private String answerFive;
    
    private String answerSix;
    
    private String answerSeven;
    
    private String answerEight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswerOne() {
		return answerOne;
	}

	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}

	public String getAnswerTwo() {
		return answerTwo;
	}

	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}

	public String getAnswerThree() {
		return answerThree;
	}

	public void setAnswerThree(String answerThree) {
		this.answerThree = answerThree;
	}

	public String getAnswerFour() {
		return answerFour;
	}

	public void setAnswerFour(String answerFour) {
		this.answerFour = answerFour;
	}

	public String getAnswerFive() {
		return answerFive;
	}

	public void setAnswerFive(String answerFive) {
		this.answerFive = answerFive;
	}

	public String getAnswerSix() {
		return answerSix;
	}

	public void setAnswerSix(String answerSix) {
		this.answerSix = answerSix;
	}

	public String getAnswerSeven() {
		return answerSeven;
	}

	public void setAnswerSeven(String answerSeven) {
		this.answerSeven = answerSeven;
	}

	public String getAnswerEight() {
		return answerEight;
	}

	public void setAnswerEight(String answerEight) {
		this.answerEight = answerEight;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
