package com.km.dao.impl;

import org.springframework.stereotype.Repository;

import com.km.bean.Organization;
import com.km.common.dao.impl.TreeDaoImpl;
import com.km.dao.IOrganizationDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:20:18
 */
@Repository
public class OrganizationDaoImpl extends TreeDaoImpl<Long, Organization> implements IOrganizationDao
{

}
