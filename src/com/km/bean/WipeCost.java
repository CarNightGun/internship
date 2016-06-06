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
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:33:21
 */
@Entity
@Table
public class WipeCost extends AbstractBaseEntity<Long>
{
	//专业计划
	@ManyToOne
	@JoinColumn
	private MajorPlan majorPlan;
	
	//申请人
	@ManyToOne
	@JoinColumn
	private User applicant;
	
	//系主任
	@ManyToOne
	@JoinColumn
	private User headDepartment;
		
		
	//学院教学秘书
	@ManyToOne
	@JoinColumn
	private User teachingSecretary;
	
	//审核状态
	private String auditState;
	
	//名称
	private String wipeCostName;
	
	private String wipeCode;
	
	//备注
	private String wipeRemark;
		
	//实习单位
	@ManyToOne
	@JoinColumn
	private Unit unit;
	
	@ManyToMany
	@JoinTable(name = "student_wipecost", joinColumns =
	{ @JoinColumn(name = "wipecost_pkuid", referencedColumnName = "pkuid") }, inverseJoinColumns =
	{ @JoinColumn(name = "student_pkuid", referencedColumnName = "pkuid") })
	private List<Student> students;
	
	//专业
	@ManyToOne
	@JoinColumn
	private Organization major;
	
	//计划报销金额
	private Double willWipedCost;
	
	//未报销金额
	private Double noWipedCost;
	
	//已报销金额
	private Double hasWipedCost;
	
	@OneToMany(mappedBy="wipeCost")
	private List<WipeCostDetail> wipeCostDetails;
	
	@OneToMany(mappedBy="wipeCost")
	private List<AuditHistory> auditHistorys;
	
	
	private String tpauditResult;
	private String tpexplain;
	private String tpoperatorName;

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
		return CommonUtil.formatDouble(willWipedCost);
	}

	public void setWillWipedCost(Double willWipedCost)
	{
		this.willWipedCost = CommonUtil.formatDouble(willWipedCost);
	}

	public Double getNoWipedCost()
	{
		return CommonUtil.formatDouble(noWipedCost);
	}

	public void setNoWipedCost(Double noWipedCost)
	{
		this.noWipedCost = CommonUtil.formatDouble(noWipedCost);
	}

	public Double getHasWipedCost()
	{
		return CommonUtil.formatDouble(hasWipedCost);
	}

	public void setHasWipedCost(Double hasWipedCost)
	{
		this.hasWipedCost = CommonUtil.formatDouble(hasWipedCost);
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

	public User getApplicant()
	{
		return applicant;
	}

	public void setApplicant(User applicant)
	{
		this.applicant = applicant;
	}

	public String getAuditState()
	{
		return auditState;
	}

	public void setAuditState(String auditState)
	{
		this.auditState = auditState;
	}
	
	public String getWipeCostName()
	{
		return wipeCostName;
	}

	public void setWipeCostName(String wipeCostName)
	{
		this.wipeCostName = wipeCostName;
	}

	public String getWipeRemark()
	{
		return wipeRemark;
	}

	public void setWipeRemark(String wipeRemark)
	{
		this.wipeRemark = wipeRemark;
	}

	public String getWipeCode()
	{
		this.setWipeCode(null);
		return wipeCode;
	}

	public void setWipeCode(String wipeCode)
	{
		
		if(wipeCode == null){			
			wipeCode = this.createTime.getTime()+""+ this.pkuid;
		}
		this.wipeCode = wipeCode;
	}

	public User getHeadDepartment()
	{
		return headDepartment;
	}

	public void setHeadDepartment(User headDepartment)
	{
		this.headDepartment = headDepartment;
	}

	public User getTeachingSecretary()
	{
		return teachingSecretary;
	}

	public void setTeachingSecretary(User teachingSecretary)
	{
		this.teachingSecretary = teachingSecretary;
	}

	public String getTpauditResult()
	{
		return tpauditResult;
	}

	public void setTpauditResult(String tpauditResult)
	{
		this.tpauditResult = tpauditResult;
	}

	public String getTpexplain()
	{
		return tpexplain;
	}

	public void setTpexplain(String tpexplain)
	{
		this.tpexplain = tpexplain;
	}

	public String getTpoperatorName()
	{
		return tpoperatorName;
	}

	public void setTpoperatorName(String tpoperatorName)
	{
		this.tpoperatorName = tpoperatorName;
	}

	
	
}
