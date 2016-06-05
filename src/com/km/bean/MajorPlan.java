package com.km.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

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
	private List<CostListDetail> costListDetail;

	//总的专业实习费用
	private Double totalAmount = 0d;
	
	//专业实习费用
	private Double professionAmount = 0d;
	
	//专业实习人数
	private Long professionNumber=0L;
	
	//毕业实习费用
	private Double graduateAmount = 0d;
	
	//毕业实习人数
	private Long graduateNumber=0L;
	
	//总的专业实习费用
	private Double proportion  = 0d;
	
	private Double connectionFee = 0d;
	
	private Double collegeStuFee = 0d;
	
	//所属年份
	private Long planYear;

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

 
	public List<CostListDetail> getCostListDetail()
	{
		return costListDetail;
	}


	public void setCostListDetail(List<CostListDetail> costListDetail)
	{
		this.costListDetail = costListDetail;
	}


	public Double getTotalAmount()
	{
		return  CommonUtil.formatDouble(totalAmount);
	}


	public void setTotalAmount(Double totalAmount)
	{
		this.totalAmount =  CommonUtil.formatDouble(totalAmount);
	}


	public Long getPlanYear()
	{
		return planYear;
	}


	public void setPlanYear(Long planYear)
	{
		this.planYear = planYear;
	}


	public Double getProfessionAmount()
	{
		return  CommonUtil.formatDouble(professionAmount);
	}


	public void setProfessionAmount(Double professionAmount)
	{
		this.professionAmount =  CommonUtil.formatDouble(professionAmount);
	}


	public Long getProfessionNumber()
	{
		return professionNumber;
	}


	public void setProfessionNumber(Long professionNumber)
	{
		this.professionNumber = professionNumber;
	}


	public Double getGraduateAmount()
	{
		return  CommonUtil.formatDouble(graduateAmount);
	}


	public void setGraduateAmount(Double graduateAmount)
	{
		this.graduateAmount =  CommonUtil.formatDouble(graduateAmount);
	}


	public Long getGraduateNumber()
	{
		
		return graduateNumber;
	}


	public void setGraduateNumber(Long graduateNumber)
	{
		
		this.graduateNumber = graduateNumber;
	}


	public Double getProportion()
	{
		this.setProportion(null);
		return CommonUtil.formatDouble(proportion);
	}


	public void setProportion(Double proportion)
	{
		if(proportion == null){
			if(this.costPlan.getTotalCost() != 0){
				proportion = CommonUtil.formatDouble(this.totalAmount / this.costPlan.getTotalCost());
			}else{
				proportion = 0d;
			}
		} 
		this.proportion = CommonUtil.formatDouble(proportion);
	}


	public Double getConnectionFee()
	{
		return CommonUtil.formatDouble(connectionFee);
	}


	public void setConnectionFee(Double connectionFee)
	{
		this.connectionFee = CommonUtil.formatDouble(connectionFee);
	}


	public Double getCollegeStuFee()
	{
		return CommonUtil.formatDouble(collegeStuFee);
	}


	public void setCollegeStuFee(Double collegeStuFee)
	{
		this.collegeStuFee = CommonUtil.formatDouble(collegeStuFee);
	}


	
	
}
