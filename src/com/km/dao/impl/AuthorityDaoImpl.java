package com.km.dao.impl;

import org.springframework.stereotype.Repository;

import com.km.bean.Authority;
import com.km.common.dao.impl.TreeDaoImpl;
import com.km.dao.IAuthorityDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:29:25
 */
@Repository
public class AuthorityDaoImpl extends TreeDaoImpl<Long, Authority> implements IAuthorityDao
{

}
