package com.km.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.km.service.IUserService;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月11日下午10:43:52
 */
@Controller
@RequestMapping("/user")
public class UserCtrl
{
	@Resource(name="userServiceImpl")
	private IUserService userServiceImpl;
	
	@RequestMapping(value = "/loginIn", method = RequestMethod.POST)
	public String loginIn(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password ,HttpSession session,ModelMap model)
	{
		if("" == password.trim() || "" == username.trim()){
			model.addAttribute("errorInfo", "用户名或者密码格式不正确");
			return "login";
		}
		
		if(this.userServiceImpl.loginIn(username, password) == null){
			model.addAttribute("errorInfo", "用户名或密码错误");
			return "login";
		}
		return "resetPassword";
	}

	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	public String loginOut()
	{
		
		return "login";
	}
}
