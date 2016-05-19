package com.km.common.bean;

import java.util.List;


/**
 * 角色封装实体
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日下午5:03:59
 */
public class UserRole
{
	private Long pkuid;
	private String name;
	private List<AuthorityMenu> authorityMenus;
	private List<PermissionMenu> permissionMenus;
	
	public UserRole(Long pkuid, String name)
	{
		this.pkuid = pkuid;
		this.name = name;
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
	public List<AuthorityMenu> getAuthorityMenus()
	{
		return authorityMenus;
	}
	public void setAuthorityMenus(List<AuthorityMenu> authorityMenus)
	{
		this.authorityMenus = authorityMenus;
	}
	public List<PermissionMenu> getPermissionMenus()
	{
		return permissionMenus;
	}
	public void setPermissionMenus(List<PermissionMenu> permissionMenus)
	{
		this.permissionMenus = permissionMenus;
	}
	
	
}
