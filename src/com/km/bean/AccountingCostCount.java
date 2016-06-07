package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午9:16:33
 */
@Entity
@Table
public class AccountingCostCount extends AbstractBaseEntity<Long>
{
	
	//专业
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Organization major;
	
	//会计科目
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private CostCategory accounting;
	
	//实习费用类型
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private PractieCostCount practieCostCount;
	
	//计划金额
	private Double planPrice;
	
	//报销金额
	private Double wipedPrice;

	public Organization getMajor()
	{
		return major;
	}

	public void setMajor(Organization major)
	{
		this.major = major;
	}

	public CostCategory getAccounting()
	{
		return accounting;
	}

	public void setAccounting(CostCategory accounting)
	{
		this.accounting = accounting;
	}

	public Double getPlanPrice()
	{
		return CommonUtil.formatDouble(planPrice);
	}

	public void setPlanPrice(Double planPrice)
	{
		this.planPrice = CommonUtil.formatDouble(planPrice);
	}

	public Double getWipedPrice()
	{
		return CommonUtil.formatDouble(wipedPrice);
	}

	public void setWipedPrice(Double wipedPrice)
	{
		this.wipedPrice = CommonUtil.formatDouble(wipedPrice);
	}

	public PractieCostCount getPractieCostCount()
	{
		return practieCostCount;
	}

	public void setPractieCostCount(PractieCostCount practieCostCount)
	{
		this.practieCostCount = practieCostCount;
	}
	
	
	
}
