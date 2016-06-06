package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:41:34
 */
@Entity
@Table
public class WipeCostDetail extends AbstractBaseEntity<Long>
{
	//报销凭证
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private WipeCost wipeCost;
	
	//费用名称
	private String costName;
	
	//费用类别名称level1
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private CostCategory accounting;
	
	//费用类别名称level2
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private CostCategory costClass;
	
	//会计编码
	private String accountingCode;
	
	//会计科目名称
	private String accountingName;
	
	
	//单据编号
	private String wipedDocNumber;

	//金额
	private Double amount;
	
	//剩余可报销金额
	private Double leftPlanAmount;
	
	//说明
	private String remark;
	
	//实习类别 0,专业实习,1毕业实习
	private Integer internClass =0;

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
		this.setCostName(null);
		return costName;
	}

	public void setCostName(String costName)
	{
		if(costName == null){
			costName = this.costClass.getCategoryName();
		}
		this.costName = costName;
	}

	public String getAccountingCode()
	{
		this.setAccountingCode(null);
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode)
	{
		if(accountingCode == null){
			accountingCode = this.accounting.getCategoryCode();
		}
		this.accountingCode = accountingCode;
	}

	public String getAccountingName()
	{
		this.setAccountingName(null);
		return accountingName;
	}

	public void setAccountingName(String accountingName)
	{
		if(accountingName == null){
			accountingName = this.accounting.getCategoryName();
		}
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
		return CommonUtil.formatDouble(amount);
	}

	public void setAmount(Double amount)
	{
		this.amount = CommonUtil.formatDouble(amount);
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public CostCategory getAccounting()
	{
		return accounting;
	}

	public void setAccounting(CostCategory accounting)
	{
		this.accounting = accounting;
	}

	public CostCategory getCostClass()
	{
		return costClass;
	}

	public void setCostClass(CostCategory costClass)
	{
		this.costClass = costClass;
	}

	public Integer getInternClass()
	{
		return internClass;
	}

	public void setInternClass(Integer internClass)
	{
		this.internClass = internClass;
	}

	public Double getLeftPlanAmount()
	{
		return leftPlanAmount;
	}

	public void setLeftPlanAmount(Double leftPlanAmount)
	{
		this.leftPlanAmount = leftPlanAmount;
	}
	
	
}	
