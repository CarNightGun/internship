package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.ISVar;

/**
 * 用户
 * 
 * @author tcn
 */

@Entity
@Table
public class User extends AbstractBaseEntity<Long>
{

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Role role;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Organization organization;

	// 用户名
	@Column
	private String accountName;

	// 密码
	@Column
	private String password;

	// 等级
	@Column
	private String level;

	// 头像地址
	@Column
	private String photourl;

	// 性别
	@Column(nullable = false)
	private int sex = ISVar.SEX_UNKNOWN;

	// 真实姓名
	@Column
	private String realName;

	// 电话
	@Column
	private String phone;

	// 电子邮件
	@Column
	private String email;

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public Organization getOrganization()
	{
		return organization;
	}

	public void setOrganization(Organization organization)
	{
		this.organization = organization;
	}

	public String getAccountName()
	{
		return accountName;
	}

	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getLevel()
	{
		return level;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}

	public String getPhotourl()
	{
		return photourl;
	}

	public void setPhotourl(String photourl)
	{
		this.photourl = photourl;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(int sex)
	{
		this.sex = sex;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	
}
