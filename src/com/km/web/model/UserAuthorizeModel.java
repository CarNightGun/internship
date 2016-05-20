package com.km.web.model;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午10:12:03
 */
public class UserAuthorizeModel
{
	private String name;
	private String username;
	private Integer roleId;
	private Integer organizationId;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public Integer getRoleId()
	{
		return roleId;
	}
	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}
	public Integer getOrganizationId()
	{
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId)
	{
		this.organizationId = organizationId;
	}
	
}
