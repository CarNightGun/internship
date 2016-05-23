package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:41:34
 */
@Entity
@Table
public class WipeCostDetail extends AbstractBaseEntity<Long>
{
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private WipeCost wipeCost;
	
	private String costName;
	
	private String accountingCode;
	
	private String accountingName;
	
	private String wipedDocNumber;

	
	private Double amount;
	
	private String remark;

	public WipeCost getWipeCost()
	{
		return wipeCost;
	}

	public void setWipeCost(WipeCost wipeCost)
	{
		this.wipeCost = wipeCost;
	}

	public String getCostName()
	{
		return costName;
	}

	public void setCostName(String costName)
	{
		this.costName = costName;
	}

	public String getAccountingCode()
	{
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode)
	{
		this.accountingCode = accountingCode;
	}

	public String getAccountingName()
	{
		return accountingName;
	}

	public void setAccountingName(String accountingName)
	{
		this.accountingName = accountingName;
	}

	public String getWipedDocNumber()
	{
		return wipedDocNumber;
	}

	public void setWipedDocNumber(String wipedDocNumber)
	{
		this.wipedDocNumber = wipedDocNumber;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	
	
}	
