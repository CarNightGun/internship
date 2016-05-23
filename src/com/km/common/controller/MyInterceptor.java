package com.km.common.controller;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.km.util.KmException;
import com.km.util.auth.AuthUtil;
import com.km.web.auth.AuthRight;
import com.km.web.auth.PermissionMenu;
import com.km.web.auth.UserAuth;

/**
 * 拦截器
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月12日下午1:16:22
 */
public class MyInterceptor extends HandlerInterceptorAdapter
{
	private static Logger log = Logger.getLogger(MyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception
	{
	
		log.debug("uri:"+request.getRequestURI()+"\ncurl:"+request.getContextPath()+"\nsurl:"+request.getServletPath());
		System.out.println("uri:"+request.getRequestURI()+"\ncurl:"+request.getContextPath()+"\nsurl:"+request.getServletPath());

		if (handler.getClass().isAssignableFrom(HandlerMethod.class))
		{
			AuthRight authRight = ((HandlerMethod) handler).getMethodAnnotation(AuthRight.class);

			// 没有说权限验证的就放行
			if (authRight == null || authRight.validate() == false)
			{
				return true;
			} else
			{
				UserAuth userAuth = AuthUtil.getSessionUserAuth(request);

				if (userAuth != null)
				{
					boolean hasPermission = false;
					String requestServletPath = request.getServletPath();

					for (PermissionMenu permissionMenu : userAuth.getUserRole()
							.getPermissionMenus())
					{

						Pattern pattern = Pattern.compile(permissionMenu.getPermission(),
								Pattern.CASE_INSENSITIVE);
						Matcher matcher = pattern.matcher(requestServletPath);
						if (matcher.find())
						{
							hasPermission = true;
							AuthUtil.setRequestPermissionMenu(request, permissionMenu);
						}
					}
					if (hasPermission)
					{
						return true;

					} else
					{
						throw new KmException("no permission！");
					}
				} else
				{
					StringBuilder curl = new StringBuilder(request.getContextPath());
					curl.append("/user/login");
					if (request.getServletPath() != null && !request.getServletPath().isEmpty())
					{
						curl.append("?returnUrl=");

						StringBuilder surl = new StringBuilder(request.getServletPath());
						if (request.getQueryString() != null && !request.getQueryString().isEmpty())
						{
							surl.append("?");
							surl.append(request.getQueryString());
						}

						curl.append(URLEncoder.encode(surl.toString(), "UTF-8"));
					}

					response.sendRedirect(curl.toString());
					return false;
				}
			}
		} else
		{
			return true;
		}
	}
}
