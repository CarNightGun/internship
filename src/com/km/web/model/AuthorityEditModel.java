package com.km.web.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午10:15:27
 */
public class AuthorityEditModel
{
	private Integer id;
	@NotEmpty(message="{name.not.empty}")
	private String name;
	@NotNull(message="{position.not.null}")
	private int position;
	private String theValue;
	@NotEmpty(message="{url.not.empty}")
	private String url;
	@NotEmpty(message="{matchUrl.not.empty}")
	private String matchUrl;
	private String itemIcon;
	private Integer parentId;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
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
	public int getPosition()
	{
		return position;
	}
	public void setPosition(int position)
	{
		this.position = position;
	}
	public String getTheValue()
	{
		return theValue;
	}
	public void setTheValue(String theValue)
	{
		this.theValue = theValue;
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
	public Integer getParentId()
	{
		return parentId;
	}
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}
	
	
}
