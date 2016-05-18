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
		
		return true;
	}
}
