package com.km.service;

import com.km.bean.User;
import com.km.common.service.IBaseService;
import com.km.dao.IUserDao;



/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月12日下午2:23:31
 */
public interface IUserService extends IBaseService<Long, User , IUserDao>
{

	public User login(String username, String password);

	
}
