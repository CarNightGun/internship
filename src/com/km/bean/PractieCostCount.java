package com.km.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午9:10:42
 */
@Entity
@Table
public class PractieCostCount extends AbstractBaseEntity<Long>
{

	//预算明细
	@OneToMany(mappedBy="practieCostCount")
	private List<AccountingCostCount> budgetAccountings;
	
	//实习报销明细
	@OneToMany(mappedBy="practieCostCount")
	private List<AccountingCostCount>  realityAccountings;
	
	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	private ZCostCount zCostCount;
	
	// 已报销金额
	private Double practieWipedAmount;

	// 计划金额
	private Double practiePlanAmount;

	public List<AccountingCostCount> getBudgetAccountings()
	{
		return budgetAccountings;
	}

	public void setBudgetAccountings(List<AccountingCostCount> budgetAccountings)
	{
		this.budgetAccountings = budgetAccountings;
	}

	public List<AccountingCostCount> getRealityAccountings()
	{
		return realityAccountings;
	}

	public void setRealityAccountings(List<AccountingCostCount> realityAccountings)
	{
		this.realityAccountings = realityAccountings;
	}

	public Double getPractieWipedAmount()
	{
		return CommonUtil.formatDouble(practieWipedAmount);
	}

	public void setPractieWipedAmount(Double practieWipedAmount)
	{
		this.practieWipedAmount = CommonUtil.formatDouble(practieWipedAmount);
	}

	public Double getPractiePlanAmount()
	{
		return CommonUtil.formatDouble(practiePlanAmount);
	}

	public void setPractiePlanAmount(Double practiePlanAmount)
	{
		this.practiePlanAmount = CommonUtil.formatDouble(practiePlanAmount);
	}

	public ZCostCount getzCostCount()
	{
		return zCostCount;
	}

	public void setzCostCount(ZCostCount zCostCount)
	{
		this.zCostCount = zCostCount;
	}
	
	
}
