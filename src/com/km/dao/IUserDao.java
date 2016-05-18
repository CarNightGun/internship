package com.km.dao;

import com.km.bean.User;
import com.km.common.dao.IBaseDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月12日下午2:52:46
 * @param <T>
 */
public interface IUserDao extends IBaseDao<Long, User>
{

	public User getUserByNameAndPassword(String name,String password);
}
