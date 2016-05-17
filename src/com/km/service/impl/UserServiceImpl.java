package com.km.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.km.bean.User;
import com.km.dao.IUserDao;
import com.km.service.IUserService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月12日下午2:24:42
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService
{
	
	@Resource(name="userDaoImpl")
	private IUserDao<User> userDaoImpl;

	@Override
	public User loginIn(String username, String password)
	{
		User user = this.userDaoImpl.getUserByNameAndPassword(username, password);
		if(user == null){
			return null;
		}
		return user;
	}


}
