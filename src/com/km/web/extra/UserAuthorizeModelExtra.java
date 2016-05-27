package com.km.web.extra;


import com.km.bean.User;
import com.km.web.model.UserAuthorizeModel;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月26日下午11:20:50
 */
public class UserAuthorizeModelExtra
{

	public static UserAuthorizeModel toUserBindModel(User user){
		UserAuthorizeModel result=new UserAuthorizeModel();
		result.setName(user.getName());
		result.setUsername(user.getAccountName());
		if(user.getRole()!=null)
			result.setRoleId(user.getRole().getPkuid());
		if(user.getOrganization()!=null)
			result.setOrganizationId(user.getOrganization().getPkuid());
		
		return result;
	}

}
