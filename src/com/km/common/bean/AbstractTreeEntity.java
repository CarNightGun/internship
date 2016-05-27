package com.km.common.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 树形结果的实体
 * 
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月17日下午12:14:08
 */
@MappedSuperclass
public abstract class AbstractTreeEntity<PKUID extends Number, EntityType extends ITree> extends
		AbstractBaseEntity<PKUID> implements ITree
{

	/**
	 * 级次代码
	 */
	@Column
	protected String levelCode;

	/**
	 * 所在层次排序编号
	 */
	@Column
	protected String sorting;

	/**
	 * 父结点
	 */

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "parent_pkuid")
	protected EntityType parent;

	/**
	 * 子节点
	 */
	@Column
	@OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER, mappedBy = "parent")
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<EntityType> children;

	public String getLevelCode()
	{
		return levelCode;
	}

	public void setLevelCode(String levelCode)
	{
		this.levelCode = levelCode;
	}

	public String getSorting()
	{
		return sorting;
	}

	public void setSorting(String sorting)
	{
		this.sorting = sorting;
	}

	public EntityType getParent()
	{
		return parent;
	}

	public void setParent(EntityType parent)
	{
		this.parent = parent;
	}

	public List<EntityType> getChildren()
	{
		return children;
	}

	public void setChildren(List<EntityType> children)
	{
		this.children = children;
	}
	
}
