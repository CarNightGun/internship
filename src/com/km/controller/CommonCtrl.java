package controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月12日上午9:16:20
 */
@Controller
@RequestMapping(value = "/href")
public class CommonCtrl
{

	//重置密码
	@RequestMapping(value = "/resetPassword")
	public String resetPassword()
	{
		return "resetPassword";
	}
	
	//登录
	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}
	
	
	//主页
	@RequestMapping(value = "/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value = "/changeContent/{fileName}")
	@ResponseBody
	public void changeContent(@PathVariable(value="fileName") String fileName,Model map){
		String ts = "<jsp:include page=\"/WEB-INF/view/"+fileName+".jsp\"/>";
		map.addAttribute("content", ts);
		
	}
	
}
