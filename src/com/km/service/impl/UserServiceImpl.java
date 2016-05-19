package com.km.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.km.bean.User;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IUserDao;
import com.km.service.IUserService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月12日下午2:24:42
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<Long, User, IUserDao>implements IUserService
{
	

	public UserServiceImpl(@Qualifier("IUserDao")IUserDao  baseDao)
	{
		super(baseDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User login(String username, String password)
	{
		Criteria criteria = baseDao.getCriteria();
		criteria.add(Restrictions.eq("accountName", username));
		criteria.add(Restrictions.eq("password",password));	
		List<User> users=criteria.list();
		
		User user=null;
		if(users!=null && !users.isEmpty()){
			user=users.get(0);			
		}
		return user;
	}
}
