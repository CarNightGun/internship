package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午10:58:01
 */
@Entity
@Table
public class CostCountModel  extends AbstractBaseEntity<Long>
{
	//年份
	private Long countYear;
	
	//专业
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Organization major;	
	
	//实习类别 0,专业实习,1毕业实习
	private Integer internClass = 0;
	
	//会计科目
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private CostCategory accounting;
	
	//计划金额
	private Double planAmount;
	
	//实际报销金额
	private Double wipedAmount;

	public Long getCountYear()
	{
		return countYear;
	}

	public void setCountYear(Long countYear)
	{
		this.countYear = countYear;
	}

	public Organization getMajor()
	{
		return major;
	}

	public void setMajor(Organization major)
	{
		this.major = major;
	}

	public Integer getInternClass()
	{
		return internClass;
	}

	public void setInternClass(Integer internClass)
	{
		this.internClass = internClass;
	}

	public CostCategory getAccounting()
	{
		return accounting;
	}

	public void setAccounting(CostCategory accounting)
	{
		this.accounting = accounting;
	}

	public Double getPlanAmount()
	{
		return  CommonUtil.formatDouble(planAmount);
	}

	public void setPlanAmount(Double planAmount)
	{
		this.planAmount =  CommonUtil.formatDouble(planAmount);
	}

	public Double getWipedAmount()
	{
		return  CommonUtil.formatDouble(wipedAmount);
	}

	public void setWipedAmount(Double wipedAmount)
	{
		this.wipedAmount = CommonUtil.formatDouble(wipedAmount);
	}
	
	
	
}
