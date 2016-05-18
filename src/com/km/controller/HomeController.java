package com.km.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日上午12:05:19
 */

@Controller
@RequestMapping(value = "/home")
public class HomeController
{
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() { 	

        return "home/index";
    }  

}
