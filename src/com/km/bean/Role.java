package com.km.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.km.common.bean.AbstractBaseEntity;

/**
 * 角色
 * 
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月17日下午12:09:00
 */

@Entity
@Table
public class Role extends AbstractBaseEntity<Long>
{
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "role_authority", joinColumns =
	{ @JoinColumn(name = "role_pkuid", referencedColumnName = "pkuid") }, inverseJoinColumns =
	{ @JoinColumn(name = "authority_pkuid", referencedColumnName = "pkuid") })
	private List<Authority> authorities;

	public List<Authority> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities)
	{
		this.authorities = authorities;
	}

	@Override
	public String toString()
	{
		return "Role [authorities=" + authorities + ", getAuthorities()=" + getAuthorities()
				+ ", getPkuid()=" + getPkuid() + ", isAduit()=" + isAduit() + ", getName()="
				+ getName() + ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()="
				+ getUpdateTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
}
