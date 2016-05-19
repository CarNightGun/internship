package com.km.util.auth;

import javax.servlet.http.HttpServletRequest;

import com.km.common.bean.PermissionMenu;
import com.km.common.bean.UserAuth;



/**
 * 验证工具类
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日下午4:59:04
 */
public class AuthUtil
{
	
	public static void setSessionUserAuth(HttpServletRequest request, UserAuth userAuth){
		request.getSession().setAttribute("userAuth", userAuth);
	}
	
	public static UserAuth getSessionUserAuth(HttpServletRequest request){
		return (UserAuth)request.getSession().getAttribute("userAuth");
	}
	
	public static void setRequestPermissionMenu(HttpServletRequest request, PermissionMenu permissionMenu){
		request.setAttribute("permissionMenu", permissionMenu);
	}
	
	public static PermissionMenu getRequestPermissionMenu(HttpServletRequest request){
		return (PermissionMenu)request.getAttribute("permissionMenu");
	}

}
