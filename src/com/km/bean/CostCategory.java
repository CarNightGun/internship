package com.km.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.km.common.bean.AbstractTreeEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月31日下午4:29:48
 */
@Entity
@Table
public class CostCategory extends AbstractTreeEntity<Long, CostCategory>
{
	
	//费用类别名称
	private String categoryName;
	
	
	//费用类别编号
	private String categoryCode;


	public String getCategoryName()
	{
		return categoryName;
	}


	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}


	public String getCategoryCode()
	{
		return categoryCode;
	}


	public void setCategoryCode(String categoryCode)
	{
		this.categoryCode = categoryCode;
	}
	
	

}
