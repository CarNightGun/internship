package com.km.dao.impl;

import org.springframework.stereotype.Repository;

import com.km.bean.Student;
import com.km.common.dao.impl.BaseDaoImpl;
import com.km.dao.IStudentDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午10:03:39
 */
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Long, Student> implements IStudentDao
{

}
