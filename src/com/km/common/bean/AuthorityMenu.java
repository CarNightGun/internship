package com.km.common.bean;

import java.util.List;



/**
 * 权限菜单
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日下午5:08:24
 */
public class AuthorityMenu
{
	private Long pkuid;
	private String name;
	private String itemIcon;
	private String url;
	private List<AuthorityMenu> childrens;
	
	public AuthorityMenu(Long pkuid, String name, String itemIcon, String url)
	{
		this.pkuid = pkuid;
		this.name = name;
		this.itemIcon = itemIcon;
		this.url = url;
	}

	public Long getPkuid()
	{
		return pkuid;
	}

	public void setPkuid(Long pkuid)
	{
		this.pkuid = pkuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getItemIcon()
	{
		return itemIcon;
	}

	public void setItemIcon(String itemIcon)
	{
		this.itemIcon = itemIcon;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public List<AuthorityMenu> getChildrens()
	{
		return childrens;
	}

	public void setChildrens(List<AuthorityMenu> childrens)
	{
		this.childrens = childrens;
	}
	
	
	
}
