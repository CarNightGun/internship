package com.km.web.extra;

import com.km.bean.Role;
import com.km.web.model.RoleEditModel;
import com.km.web.model.RoleSearchModel;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午11:38:18
 */
public class RoleModelExtra
{
//	public static RoleSearch toRoleSearch(RoleSearchModel searchModel){
//		RoleSearch ret=new RoleSearch();
//		ret.setName(searchModel.getName());
//		
//		return ret;
//	}
	
	public static Role toRole(RoleEditModel editModel){
		Role role=new Role();
		role.setPkuid(editModel.getId());
		role.setName(editModel.getName());
		return role;
	}
}
