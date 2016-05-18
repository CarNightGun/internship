package com.km.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.km.common.bean.AbstractTreeEntity;

/**
 * 权限配置实体
 * 
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月17日上午12:40:54
 */
@Entity
@Table
public class Authority extends AbstractTreeEntity<Long, Authority>
{

	@Column
	private String url;

	@Column
	private String matchUrl;

	@Column
	private String itemIcon;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "role_authority", joinColumns =
	{ @JoinColumn(name = "authority_pkuid", referencedColumnName = "pkuid") }, inverseJoinColumns =
	{ @JoinColumn(name = "role_pkuid", referencedColumnName = "pkuid") })
	private List<Role> roles;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getMatchUrl()
	{
		return matchUrl;
	}

	public void setMatchUrl(String matchUrl)
	{
		this.matchUrl = matchUrl;
	}

	public String getItemIcon()
	{
		return itemIcon;
	}

	public void setItemIcon(String itemIcon)
	{
		this.itemIcon = itemIcon;
	}

	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

}
