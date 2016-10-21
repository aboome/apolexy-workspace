package com.yh.apoplexy.assist.dmo.patient.knowledge;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 患者资讯信息表
 * @author cc
 */
@Table(name = "t_patient_news")
public class PatientNewsDmo extends Entity {

	private static final long serialVersionUID = -5822526841861616272L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
    
	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * 新闻类型
	 */
	@Column(name = "type")
	private String type;
	
	/**
	 *新闻格式 
	 */
	@Column(name = "sub_title")
	private String subTitle;
	
	/**
	 * 缩略图
	 */
	@Column(name = "small_logo")
	private String smallLogo;
	
   /**
   * 信息来源
   */
	@Column(name = "source")
	private String source;
	
	/**
	 * 内容
	 */
	@Column(name = "content")
	private String content;
	
     /**
      * 开始时间
      */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 最后一次的更新时间
	 */
	@Column(name = "last_update_time")
	private Date lastUpdateTime;
	
	/**
	 * 状态
	 */
	@Column(name = "status")
	private String status;

    /**
     * 内容类型
     */
    @Column(name = "content_type")
    private String contentType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSmallLogo() {
		return smallLogo;
	}

	public void setSmallLogo(String smallLogo) {
		this.smallLogo = smallLogo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
