package com.km.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月23日下午8:38:44
 */
@Entity
@Table
public class AuditHistory extends AbstractBaseEntity<Long>
{
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private WipeCost wipeCost;
	
	private String operatorName;
	
	private String explain;
	
	private String auditResult;

	public WipeCost getWipeCost()
	{
		return wipeCost;
	}

	public void setWipeCost(WipeCost wipeCost)
	{
		this.wipeCost = wipeCost;
	}

	public String getOperatorName()
	{
		return operatorName;
	}

	public void setOperatorName(String operatorName)
	{
		this.operatorName = operatorName;
	}

	public String getExplain()
	{
		return explain;
	}

	public void setExplain(String explain)
	{
		this.explain = explain;
	}

	public String getAuditResult()
	{
		return auditResult;
	}

	public void setAuditResult(String auditResult)
	{
		this.auditResult = auditResult;
	}
	
	
}
