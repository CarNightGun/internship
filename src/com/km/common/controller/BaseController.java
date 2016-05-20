package com.km.common.controller;

import javax.annotation.Resource;

import com.km.service.IAuthorityService;
import com.km.service.IOrganizationService;
import com.km.service.IRoleService;
import com.km.service.IUserService;


/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:05:20
 */
public abstract class BaseController
{
	protected final String searchModel="searchModel";
	protected final String contentModel="contentModel";
	protected final String treeDataSource="treeDataSource";
	protected final String selectDataSource="selectDataSource";
	
    @Resource(name = "RoleServiceImpl")
    protected IRoleService roleService;
	
    @Resource(name = "UserServiceImpl")
    protected IUserService userService;
	
    @Resource(name = "AuthorityServiceImpl")
	protected IAuthorityService authorityService;
	
    @Resource(name = "OrganizationServiceImpl")
	protected IOrganizationService organizationService;
}
