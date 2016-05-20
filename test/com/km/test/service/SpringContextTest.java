package com.km.test.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.km.bean.Role;
import com.km.util.ArraysUtil;
import com.km.util.SpringUtil;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日下午1:19:43
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/km/config/spring-common.xml")
public class SpringContextTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	public void testSpringUtil()
	{
		Role role = (Role) SpringUtil.getObject(Role.class);
		if (role == null)
		{
			fail("Not yet implemented");
		}

		System.out.println(role);
	}

	public void testReflect()
	{

		List<Role> list = new ArrayList<Role>();
		for (int i = 0; i < 14; i++)
		{
			Role role = (Role) SpringUtil.getObject(Role.class);
			role.setPkuid(i + 1L);
			list.add(role);
		}
		Long[] obs = (Long[]) ArraysUtil.listToArrayByField(list, "pkuid");
		for (int i = 0; i < obs.length; i++)
		{
			System.out.println(obs[i]);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testReflect2()
	{List<Role> list = new ArrayList<Role>();		
	for (int i = 0; i < 14; i++)
	{
		Role role = (Role) SpringUtil.getObject(Role.class);
		role.setName(i+1L+"  333");
		list.add(role);
	}
	
	  List<String> ddList = (List<String>) ArraysUtil.filterListByColumn(list, "name");
		System.out.println(ddList);
	}
}
