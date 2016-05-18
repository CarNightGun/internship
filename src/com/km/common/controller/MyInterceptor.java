package com.km.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月12日下午1:16:22
 */
public class MyInterceptor extends HandlerInterceptorAdapter
{
	private static Logger log = Logger.getLogger(MyInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);
		if (uri.endsWith("/loginIn") || uri.endsWith("/loginOut"))
		{
			return true;
		}

		if (uri.endsWith("/login") || uri.endsWith("/internship"))
		{
			if (request.getSession() != null && request.getSession().getAttribute("loginUser") != null)
			{
				response.sendRedirect(request.getContextPath() + "/index");
			} else
			{
				return true;
			}
		}

		if (request.getSession() != null && request.getSession().getAttribute("loginUser") != null)
		{
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}
