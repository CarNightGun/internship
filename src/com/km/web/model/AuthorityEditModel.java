package com.km.web.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午10:15:27
 */
public class AuthorityEditModel
{
	private Long id;
 
	private String name;
 
	private String sorting;
 
	private String url;
 
	private String matchUrl;
	private String itemIcon;
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
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getMatchUrl()
	{
		return matchUrl;
	}
	public void setMatchUrl(String matchUrl)
	{
		this.matchUrl = matchUrl;
	}
	public String getItemIcon()
	{
		return itemIcon;
	}
	public void setItemIcon(String itemIcon)
	{
		this.itemIcon = itemIcon;
	}
	public Long getParentId()
	{
		return parentId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
	
	
}
