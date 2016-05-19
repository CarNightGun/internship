package com.km.common.bean;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日下午5:02:51
 */
public class PermissionMenu
{
	//根节点id name
	public Long rootPkuid;
	public String rootName;
	public Long subPkuid;
	public String subName;
	public String curName;
	public String permission;
	
	public PermissionMenu(Long rootPkuid, String rootName, Long subPkuid, String subName,
			String curName, String permission)
	{
		this.rootPkuid = rootPkuid;
		this.rootName = rootName;
		this.subPkuid = subPkuid;
		this.subName = subName;
		this.curName = curName;
		this.permission = permission;
	}

	public Long getRootPkuid()
	{
		return rootPkuid;
	}

	public void setRootPkuid(Long rootPkuid)
	{
		this.rootPkuid = rootPkuid;
	}

	public String getRootName()
	{
		return rootName;
	}

	public void setRootName(String rootName)
	{
		this.rootName = rootName;
	}

	public Long getSubPkuid()
	{
		return subPkuid;
	}

	public void setSubPkuid(Long subPkuid)
	{
		this.subPkuid = subPkuid;
	}

	public String getSubName()
	{
		return subName;
	}

	public void setSubName(String subName)
	{
		this.subName = subName;
	}

	public String getCurName()
	{
		return curName;
	}

	public void setCurName(String curName)
	{
		this.curName = curName;
	}

	public String getPermission()
	{
		return permission;
	}

	public void setPermission(String permission)
	{
		this.permission = permission;
	}
	
	
}
