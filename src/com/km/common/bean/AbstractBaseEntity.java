package com.km.common.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * base实体
 * 
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月16日下午11:17:22
 */
@MappedSuperclass
public abstract class AbstractBaseEntity<PKUID extends Number>
{

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pkuid",nullable=false)
	private PKUID pkuid;

	/**
	 * 标记实体是否审核 0false否 1true是
	 */
	@Column(name="audit",nullable = false)
	private boolean aduit = false;

	/**
	 * 名称
	 */
	@Column(name="name")
	private String name;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createtime",updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updatetime")
	@org.hibernate.annotations.UpdateTimestamp
	private Date updateTime;

	public PKUID getPkuid()
	{
		return pkuid;
	}

	public void setPkuid(PKUID pkuid)
	{
		this.pkuid = pkuid;
	}

	public boolean isAduit()
	{
		return aduit;
	}

	public void setAduit(boolean aduit)
	{
		this.aduit = aduit;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	

}
