package com.km.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:09:50
 */
@Entity
@Table
public class Unit extends AbstractBaseEntity<Long>
{

	//单位名称
	private String unitName;
	
	//地址
	private String address;
	
	//负责人
	private String chargeName;
	
	//通讯电话
	private String telphone;
	
	
	@OneToMany(mappedBy="unit")
	private List<Student> students;

	public String getUnitName()
	{
		return unitName;
	}

	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getChargeName()
	{
		return chargeName;
	}

	public void setChargeName(String chargeName)
	{
		this.chargeName = chargeName;
	}

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public List<Student> getStudents()
	{
		return students;
	}

	public void setStudents(List<Student> students)
	{
		this.students = students;
	}
	
	
	
	
}
