package com.km.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.Authority;
import com.km.common.service.impl.TreeServiceImpl;
import com.km.dao.IAuthorityDao;
import com.km.service.IAuthorityService;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日上午8:34:29
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class AuthorityServiceImpl extends TreeServiceImpl<Long, Authority, IAuthorityDao> implements
		IAuthorityService
{
	@Autowired
	public AuthorityServiceImpl(@Qualifier("authorityDaoImpl") IAuthorityDao baseDao)
	{
		super(baseDao);
	}

}
