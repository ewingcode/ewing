package com.admin.model;

// Generated 2015-10-10 22:43:59 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * WebResourceSpec generated by hbm2java
 */
public class WebResourceSpec implements java.io.Serializable {

	private Integer id;
	private int resourceId;
	private String spec;
	private Integer rank;
	private String iseff;
	private Date createTime;
	private Date lastUpdate;

	public WebResourceSpec() {
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getIseff() {
		return this.iseff;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
