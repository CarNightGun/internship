package com.km.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月19日上午12:05:19
 */

@Controller
@RequestMapping(value = "/home")
public class HomeCtrl
{
	@AuthRight
	@RequestMapping(value = "/index")
    public String index() { 	

        return "home/index";
    }  

}
