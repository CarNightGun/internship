package com.km.web.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午10:21:09
 */
public class RoleEditModel
{
	private Long id;
	@NotEmpty(message="{name.not.empty}")
	private String name;
	
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
	
}
