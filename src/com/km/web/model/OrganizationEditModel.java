package com.km.web.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午10:17:40
 */
public class OrganizationEditModel
{
	private Long id;
	@NotEmpty(message="{name.not.empty}")
	private String name;	

	private String sorting;
	
	private String orgCode;
	
	private Long parentId;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSorting()
	{
		return sorting;
	}
	public void setSorting(String sorting)
	{
		this.sorting = sorting;
	}
	public Long getParentId()
	{
		return parentId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
	public String getOrgCode()
	{
		return orgCode;
	}
	public void setOrgCode(String orgCode)
	{
		this.orgCode = orgCode;
	}
	
	
}
