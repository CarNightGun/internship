package com.km.web.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日下午6:09:55
 */
public class UserLoginModel
{
	@NotEmpty(message="{username.not.empty}")
	private String userName;
	@NotEmpty(message="{password.not.empty}")
	private String password;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	

}
