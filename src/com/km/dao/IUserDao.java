package com.km.dao;

import java.io.Serializable;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月12日下午2:52:46
 * @param <T>
 */
public interface IUserDao<User> extends IBaseDao<User, Serializable>
{

	public User getUserByNameAndPassword(String name,String password);
}
