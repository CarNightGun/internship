package com.km.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:33:21
 */
@Entity
@Table
public class WipeCost extends AbstractBaseEntity<Long>
{
	@ManyToOne
	@JoinColumn
	private MajorPlan majorPlan;
	
	@ManyToOne
	@JoinColumn
	private Unit unit;
	
	@ManyToMany
	@JoinTable(name = "student_wipecost", joinColumns =
	{ @JoinColumn(name = "wipecost_pkuid", referencedColumnName = "pkuid") }, inverseJoinColumns =
	{ @JoinColumn(name = "student_pkuid", referencedColumnName = "pkuid") })
	private List<Student> students;
	
	@ManyToOne
	@JoinColumn
	private Organization major;
	
	
	private Double willWipedCost;
	
	private Double noWipedCost;
	
	private Double hasWipedCost;
	
	@OneToMany(mappedBy="wipeCost")
	private List<WipeCostDetail> wipeCostDetails;
	
	@OneToMany(mappedBy="wipeCost")
	private List<AuditHistory> auditHistorys;

	public MajorPlan getMajorPlan()
	{
		return majorPlan;
	}

	public void setMajorPlan(MajorPlan majorPlan)
	{
		this.majorPlan = majorPlan;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public List<Student> getStudents()
	{
		return students;
	}

	public void setStudents(List<Student> students)
	{
		this.students = students;
	}

	public Organization getMajor()
	{
		return major;
	}

	public void setMajor(Organization major)
	{
		this.major = major;
	}

	public Double getWillWipedCost()
	{
		return willWipedCost;
	}

	public void setWillWipedCost(Double willWipedCost)
	{
		this.willWipedCost = willWipedCost;
	}

	public Double getNoWipedCost()
	{
		return noWipedCost;
	}

	public void setNoWipedCost(Double noWipedCost)
	{
		this.noWipedCost = noWipedCost;
	}

	public Double getHasWipedCost()
	{
		return hasWipedCost;
	}

	public void setHasWipedCost(Double hasWipedCost)
	{
		this.hasWipedCost = hasWipedCost;
	}

	public List<WipeCostDetail> getWipeCostDetails()
	{
		return wipeCostDetails;
	}

	public void setWipeCostDetails(List<WipeCostDetail> wipeCostDetails)
	{
		this.wipeCostDetails = wipeCostDetails;
	}

	public List<AuditHistory> getAuditHistorys()
	{
		return auditHistorys;
	}

	public void setAuditHistorys(List<AuditHistory> auditHistorys)
	{
		this.auditHistorys = auditHistorys;
	}
	
	

}
