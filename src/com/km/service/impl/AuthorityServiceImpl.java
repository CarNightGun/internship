package com.km.service.impl;

import org.springframework.stereotype.Service;

import com.km.bean.Authority;
import com.km.common.service.impl.TreeServiceImpl;
import com.km.dao.IAuthorityDao;
import com.km.service.IAuthorityService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:34:29
 */
@Service
public class AuthorityServiceImpl extends TreeServiceImpl<Long, Authority, IAuthorityDao> implements IAuthorityService
{

	public AuthorityServiceImpl(IAuthorityDao baseDao)
	{
		super(baseDao);
	}

}