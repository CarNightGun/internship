package com.km.service;

import com.km.bean.Organization;
import com.km.common.service.ITreeService;
import com.km.dao.IOrganizationDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:21:58
 */
public interface IOrganizationService extends ITreeService<Long, Organization, IOrganizationDao>
{
	
	public void updateOrg(Organization org);


}
