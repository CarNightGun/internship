package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:15:00
 */
@Entity
@Table
public class Student extends AbstractBaseEntity<Long>
{
	
	private String realName;
	
	
	private String telphone;
	
	private String email;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Organization major;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Organization stuClass;
	
	private String bankAccount;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private MajorPlan majorPlan;
	
	
	private String stuYear;
	
	private String stuNo;

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Organization getMajor()
	{
		return major;
	}

	public void setMajor(Organization major)
	{
		this.major = major;
	}

	public Organization getStuClass()
	{
		return stuClass;
	}

	public void setStuClass(Organization stuClass)
	{
		this.stuClass = stuClass;
	}

	public String getBankAccount()
	{
		return bankAccount;
	}

	public void setBankAccount(String bankAccount)
	{
		this.bankAccount = bankAccount;
	}

	public MajorPlan getMajorPlan()
	{
		return majorPlan;
	}

	public void setMajorPlan(MajorPlan majorPlan)
	{
		this.majorPlan = majorPlan;
	}

	public String getStuYear()
	{
		return stuYear;
	}

	public void setStuYear(String stuYear)
	{
		this.stuYear = stuYear;
	}

	public String getStuNo()
	{
		return stuNo;
	}

	public void setStuNo(String stuNo)
	{
		this.stuNo = stuNo;
	}
	
	
}
