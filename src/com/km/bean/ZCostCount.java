package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年6月7日上午8:27:27
 */
@Entity
@Table
public class ZCostCount extends AbstractBaseEntity<Long>
{

	//专业实习
	@OneToOne(optional = true, cascade = CascadeType.ALL,mappedBy="zCostCount")
	private PractieCostCount proPractie;
	
	//毕业实习
	@OneToOne(optional = true, cascade = CascadeType.ALL,mappedBy="zCostCount")
	private PractieCostCount graPractie;

	// 已报销金额
	private Double totalWipedAmount;

	// 计划金额
	private Double totalPlanAmount;

	public PractieCostCount getProPractie()
	{
		return proPractie;
	}

	public void setProPractie(PractieCostCount proPractie)
	{
		this.proPractie = proPractie;
	}

	public PractieCostCount getGraPractie()
	{
		return graPractie;
	}

	public void setGraPractie(PractieCostCount graPractie)
	{
		this.graPractie = graPractie;
	}

	public Double getTotalWipedAmount()
	{
		return CommonUtil.formatDouble(totalWipedAmount);
	}

	public void setTotalWipedAmount(Double totalWipedAmount)
	{
		this.totalWipedAmount = CommonUtil.formatDouble(totalWipedAmount);
	}

	public Double getTotalPlanAmount()
	{
		return CommonUtil.formatDouble(totalPlanAmount);
	}

	public void setTotalPlanAmount(Double totalPlanAmount)
	{
		this.totalPlanAmount = CommonUtil.formatDouble(totalPlanAmount);
	}
	
	
}
