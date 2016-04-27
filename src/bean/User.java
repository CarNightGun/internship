package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import common.ISVar;


/**
 * 用户
 * 
 * @author tcn
 */

@Entity
@Table
public class User {
	
	//主键
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long uid;
	
	//用户名
	private String name;
	
	//密码
	private String password;

	//等级
	private String level;	
	
	//头像地址
	private String photourl;
	
	//性别
	private int sex = ISVar.SEX_UNKNOWN;
	
	//真实姓名
	private String realName;
	
	//电话
	private String phone;
	
	//电子邮件
	private String email;
	
	//银行账户
	private String bankAccount;
	
	//身份证号
	private String IDCardNumber;
	
	//银行名称
	private String bankName;
	
	//单位
	private String units;
		
	//二级单位
	private String secondUnits;
	
	//住址
	private String address;

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
	
	
	
	
	
	
}
