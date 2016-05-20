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
	protected final String requestUrl="requestUrl";
	protected final String requestQuery="requestQuery";
	
    @Resource(name = "roleServiceImpl")
    protected IRoleService roleService;
	
    @Resource(name = "userServiceImpl")
    protected IUserService userService;
	
    @Resource(name = "authorityServiceImpl")
	protected IAuthorityService authorityService;
	
    @Resource(name = "organizationServiceImpl")
	protected IOrganizationService organizationService;
}
