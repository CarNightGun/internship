package com.km.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.km.bean.User;
import com.km.common.dao.impl.BaseDaoImpl;
import com.km.dao.IUserDao;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月12日下午2:54:27
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<Long,User> implements IUserDao
{

	@Override
	public User getUserByNameAndPassword(String name, String password)
	{
		String hql = "from User where name = ? and password = ?";
		List<User> list =  this.getListByHql(hql, name,password);
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	
}
