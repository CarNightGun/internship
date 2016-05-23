package com.km.web.model;


/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日下午6:09:55
 */
public class UserLoginModel
{
//	@NotEmpty(message="{username.not.empty}")
	private String username;
//	@NotEmpty(message="{password.not.empty}")
	private String password;

	private String errorLoginInfo;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getErrorLoginInfo()
	{
		return errorLoginInfo;
	}

	public void setErrorLoginInfo(String errorLoginInfo)
	{
		this.errorLoginInfo = errorLoginInfo;
	}
	
	

}
