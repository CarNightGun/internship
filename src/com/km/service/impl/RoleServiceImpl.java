package com.km.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.km.bean.Role;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IRoleDao;
import com.km.service.IRoleService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:11:17
 */

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Long, Role, IRoleDao> implements IRoleService
{

	public RoleServiceImpl(@Qualifier("IRoleDao")IRoleDao baseDao)
	{
		super(baseDao);
	}

}
