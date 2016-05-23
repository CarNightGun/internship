package com.km.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:17:00
 */
@Entity
@Table
public class MajorPlan extends AbstractBaseEntity<Long>
{

	private String majorPlanName;
	
	
	private String planRemarks;
	
	@ManyToOne
	@JoinColumn
	private CostPlan costPlan;
	
	@ManyToOne
	@JoinColumn
	private Unit unit;
	
	@ManyToOne
	@JoinColumn
	private Organization major;
	
	@OneToMany(mappedBy="majorPlan")
	private List<Student> students;


	public String getMajorPlanName()
	{
		return majorPlanName;
	}


	public void setMajorPlanName(String majorPlanName)
	{
		this.majorPlanName = majorPlanName;
	}


	public String getPlanRemarks()
	{
		return planRemarks;
	}


	public void setPlanRemarks(String planRemarks)
	{
		this.planRemarks = planRemarks;
	}


	public CostPlan getCostPlan()
	{
		return costPlan;
	}


	public void setCostPlan(CostPlan costPlan)
	{
		this.costPlan = costPlan;
	}


	public Unit getUnit()
	{
		return unit;
	}


	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}


	public Organization getMajor()
	{
		return major;
	}


	public void setMajor(Organization major)
	{
		this.major = major;
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
