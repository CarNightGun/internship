package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;
import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月31日下午4:39:46
 */
@Entity
@Table
public class CostListDetail extends AbstractBaseEntity<Long>
{
	
	//费用金额
	private Double price = 0d;
	
	//费用类别
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private CostCategory costCategory;
	
	//费用说明
	private String remark; 
	
	//专业计划
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private MajorPlan majorPlan;
	
	//实习类别 0,专业实习,1毕业实习
	private Integer internClass =0;

	public Double getPrice()
	{
		return CommonUtil.formatDouble(price);
	}

	public void setPrice(Double price)
	{
		this.price = CommonUtil.formatDouble(price);
	}

	public CostCategory getCostCategory()
	{
		return costCategory;
	}

	public void setCostCategory(CostCategory costCategory)
	{
		this.costCategory = costCategory;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public MajorPlan getMajorPlan()
	{
		return majorPlan;
	}

	public void setMajorPlan(MajorPlan majorPlan)
	{
		this.majorPlan = majorPlan;
	}

	public Integer getInternClass()
	{
		return internClass;
	}

	public void setInternClass(Integer internClass)
	{
		this.internClass = internClass;
	}
	
	
	

}
