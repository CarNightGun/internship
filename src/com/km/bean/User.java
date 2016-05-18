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
	@Column
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

	// 银行账户
	@Column
	private String bankAccount;

	// 身份证号
	@Column
	private String IDCardNumber;

	// 银行名称
	@Column
	private String bankName;

	// 单位
	@Column
	private String units;

	// 二级单位
	@Column
	private String secondUnits;

	// 住址
	@Column
	private String address;

	// 学号
	@Column
	private String studentID;

	// 行政班级ID
	@Column
	private String classID;

	// 专业编号
	@Column
	private String majorID;

	// 专业名称
	@Column
	private String majorName;

	// 年级
	@Column
	private String grade;

	// 学制
	@Column
	private int lengthSchooling;

	// 工号
	@Column
	private String jobNumber;

	// 职称
	@Column
	private String professionalTitle;

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

	public String getBankAccount()
	{
		return bankAccount;
	}

	public void setBankAccount(String bankAccount)
	{
		this.bankAccount = bankAccount;
	}

	public String getIDCardNumber()
	{
		return IDCardNumber;
	}

	public void setIDCardNumber(String iDCardNumber)
	{
		IDCardNumber = iDCardNumber;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public String getUnits()
	{
		return units;
	}

	public void setUnits(String units)
	{
		this.units = units;
	}

	public String getSecondUnits()
	{
		return secondUnits;
	}

	public void setSecondUnits(String secondUnits)
	{
		this.secondUnits = secondUnits;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getStudentID()
	{
		return studentID;
	}

	public void setStudentID(String studentID)
	{
		this.studentID = studentID;
	}

	public String getClassID()
	{
		return classID;
	}

	public void setClassID(String classID)
	{
		this.classID = classID;
	}

	public String getMajorID()
	{
		return majorID;
	}

	public void setMajorID(String majorID)
	{
		this.majorID = majorID;
	}

	public String getMajorName()
	{
		return majorName;
	}

	public void setMajorName(String majorName)
	{
		this.majorName = majorName;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public int getLengthSchooling()
	{
		return lengthSchooling;
	}

	public void setLengthSchooling(int lengthSchooling)
	{
		this.lengthSchooling = lengthSchooling;
	}

	public String getJobNumber()
	{
		return jobNumber;
	}

	public void setJobNumber(String jobNumber)
	{
		this.jobNumber = jobNumber;
	}

	public String getProfessionalTitle()
	{
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle)
	{
		this.professionalTitle = professionalTitle;
	}

}
