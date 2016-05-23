package com.km.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:17:27
 */
@Entity
@Table
public class CostPlan extends AbstractBaseEntity<Long>
{

	
	private Double totalCost;
	
	//专业实习人数
	private Long professionalNumber;
	
	
	//毕业实习人数
	private Long graduateNumner;
	
	private Double professionalAvgCost;
	
	private Double graduateAvgCost;
	
	
	private Double collegeStuFee;
	
	private Double connectionFee;
	
	
	private Double activeFee;
	
	
	private Double residualFee;

	private String costPlanName;
	
	private String planRemark;
	

	public Double getTotalCost()
	{
		return totalCost;
	}


	public void setTotalCost(Double totalCost)
	{
		this.totalCost = totalCost;
	}


	public Long getProfessionalNumber()
	{
		return professionalNumber;
	}


	public void setProfessionalNumber(Long professionalNumber)
	{
		this.professionalNumber = professionalNumber;
	}


	public Long getGraduateNumner()
	{
		return graduateNumner;
	}


	public void setGraduateNumner(Long graduateNumner)
	{
		this.graduateNumner = graduateNumner;
	}


	public Double getProfessionalAvgCost()
	{
		return professionalAvgCost;
	}


	public void setProfessionalAvgCost(Double professionalAvgCost)
	{
		this.professionalAvgCost = professionalAvgCost;
	}


	public Double getGraduateAvgCost()
	{
		return graduateAvgCost;
	}


	public void setGraduateAvgCost(Double graduateAvgCost)
	{
		this.graduateAvgCost = graduateAvgCost;
	}


	public Double getCollegeStuFee()
	{
		return collegeStuFee;
	}


	public void setCollegeStuFee(Double collegeStuFee)
	{
		this.collegeStuFee = collegeStuFee;
	}


	public Double getConnectionFee()
	{
		return connectionFee;
	}


	public void setConnectionFee(Double connectionFee)
	{
		this.connectionFee = connectionFee;
	}


	public Double getActiveFee()
	{
		return activeFee;
	}


	public void setActiveFee(Double activeFee)
	{
		this.activeFee = activeFee;
	}


	public Double getResidualFee()
	{
		return residualFee;
	}


	public void setResidualFee(Double residualFee)
	{
		this.residualFee = residualFee;
	}


	public String getCostPlanName()
	{
		return costPlanName;
	}


	public void setCostPlanName(String costPlanName)
	{
		this.costPlanName = costPlanName;
	}


	public String getPlanRemark()
	{
		return planRemark;
	}


	public void setPlanRemark(String planRemark)
	{
		this.planRemark = planRemark;
	}
	
	
	
}
