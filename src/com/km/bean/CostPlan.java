package com.km.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:17:27
 */
@Entity
@Table
public class CostPlan extends AbstractBaseEntity<Long>
{

	//实习总费用
	private Double totalCost = 0d;
	
	//专业实习人数
	private Long professionalNumber = 0L;
	
	//毕业实习人数
	private Long graduateNumner = 0L;
	
	//专业实习平均费用
	private Double professionalAvgCost = 0d;
	
	//毕业实习平均费用
	private Double graduateAvgCost = 0d;
	
	//本专科生实习费用
	private Double collegeStuFee = 0d;
	
	//实习联系费
	private Double connectionFee = 0d;
	
	//实习机动经费
	private Double activeFee = 0d;
	
	//剩余实习经费
	private Double residualFee = 0d;

	//实习经费计划名称
	private String costPlanName;
	
	//实习经费计划说明
	private String planRemark;
	
	
	//实习计划所属年份
	private Long planYear;

	@OneToMany(mappedBy="costPlan")
	private List<MajorPlan> majorPlans;
	
	public Double getTotalCost()
	{
		return CommonUtil.formatDouble(totalCost);
	}


	public void setTotalCost(Double totalCost)
	{
		this.totalCost = CommonUtil.formatDouble(totalCost);
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
		return CommonUtil.formatDouble(professionalAvgCost);
	}


	public void setProfessionalAvgCost(Double professionalAvgCost)
	{
		this.professionalAvgCost = CommonUtil.formatDouble(professionalAvgCost);
	}


	public Double getGraduateAvgCost()
	{
		return CommonUtil.formatDouble(graduateAvgCost);
	}


	public void setGraduateAvgCost(Double graduateAvgCost)
	{
		this.graduateAvgCost = CommonUtil.formatDouble(graduateAvgCost);
	}


	public Double getCollegeStuFee()
	{
		return CommonUtil.formatDouble(collegeStuFee);
	}


	public void setCollegeStuFee(Double collegeStuFee)
	{
		this.collegeStuFee = CommonUtil.formatDouble(collegeStuFee);
	}


	public Double getConnectionFee()
	{
		return CommonUtil.formatDouble(connectionFee);
	}


	public void setConnectionFee(Double connectionFee)
	{
		this.connectionFee = CommonUtil.formatDouble(connectionFee);
	}


	public Double getActiveFee()
	{
		return CommonUtil.formatDouble(activeFee);
	}


	public void setActiveFee(Double activeFee)
	{
		this.activeFee = CommonUtil.formatDouble(activeFee);
	}


	public Double getResidualFee()
	{
		return CommonUtil.formatDouble(residualFee);
	}


	public void setResidualFee(Double residualFee)
	{
		this.residualFee = CommonUtil.formatDouble(residualFee);
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


	public Long getPlanYear()
	{
		return planYear;
	}


	public void setPlanYear(Long planYear)
	{
		this.planYear = planYear;
	}


	public List<MajorPlan> getMajorPlans()
	{
		return majorPlans;
	}


	public void setMajorPlans(List<MajorPlan> majorPlans)
	{
		this.majorPlans = majorPlans;
	}
	
	
	
}
