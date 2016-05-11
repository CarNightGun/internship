package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月11日下午10:43:52
 */
@Controller
@RequestMapping("/user")
public class UserCtrl
{
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password)
	{
		System.out.println("/user/login"+username+":"+password);
		return "404";
	}
}
