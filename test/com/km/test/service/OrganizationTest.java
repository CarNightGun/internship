package com.km.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 






import com.km.bean.Organization;
import com.km.bean.Student;
import com.km.dao.IStudentDao;
import com.km.service.IOrganizationService;
import com.km.service.IStudentService;
import com.km.util.SpringUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日下午5:59:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/km/config/spring-common.xml")
public class OrganizationTest
{

//	@Resource(name= "organizationServiceImpl")
//	protected IOrganizationService organizationService;
	
//	@Resource(name ="studentServiceImpl")
//	protected IStudentService studentService;
	
	@Resource(name="studentDaoImpl")
	protected IStudentDao studentDao;
	
//	@Test
//	public void OrgTest(){
//		Student stu =   SpringUtil.getObject(Student.class);
//		stu.setName("123");
//		stu.setTelphone("123123213");
//		stu.setRealName("eeee");
//		stu.setEmail("1234@qq.com");
//		
//		Organization org = SpringUtil.getObject(Organization.class);
//		org.setPkuid(7L);
//		
//		stu.setStuClass(org);
//		
//		studentService.merge(stu);
//		
//		
//	}
	
	
//	@Test
//	public void testList(){
//	  Criteria	d = studentDao.getCriteria();
//	  
//	  d.add(Restrictions.eq("stuNo", "123"));
//	  
//	  List<Student> s = d.list();
//	  
//	  System.out.println(s);
//	}
	
}
