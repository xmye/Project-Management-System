package com.project.model;

import java.sql.Date;

public class info {
	
	private String title;
	private String publishPson;
	private Date publishTime;
	private Date updateTime;
	private String type;
	private String content;
	private Integer InfoId;
	
	public info(){}

	public info(String title, String publishPson, Date publishTime, Date updateTime, String type, String content,
			Integer infoId) {
		super();
		this.title = title;
		this.publishPson = publishPson;
		this.publishTime = publishTime;
		this.updateTime = updateTime;
		this.type = type;
		this.content = content;
		InfoId = infoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishPson() {
		return publishPson;
	}

	public void setPublishPson(String publishPson) {
		this.publishPson = publishPson;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getInfoId() {
		return InfoId;
	}

	public void setInfoId(Integer infoId) {
		InfoId = infoId;
	}

}
