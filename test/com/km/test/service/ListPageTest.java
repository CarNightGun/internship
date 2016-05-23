package com.km.test.service;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.km.bean.User;
import com.km.service.IUserService;
import com.km.util.page.IPageList;
import com.km.util.page.PageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月21日上午12:31:39
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/km/config/spring-common.xml")
public class ListPageTest
{
	 @Resource(name = "userServiceImpl")
	  protected IUserService userService;
	
	@Test
	public void listPageTest(){
		
	  PageList<User> pageList =	(PageList<User>) userService.listPage(new User(), 1, 10);
	 
	 System.out.println(pageList);
		
	}

}
