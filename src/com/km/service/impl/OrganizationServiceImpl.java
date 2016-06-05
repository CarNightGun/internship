package com.km.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.Organization;
import com.km.common.service.impl.TreeServiceImpl;
import com.km.dao.IOrganizationDao;
import com.km.service.IOrganizationService;
 

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日上午8:24:13
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class OrganizationServiceImpl extends TreeServiceImpl<Long, Organization, IOrganizationDao>
		implements IOrganizationService
{
	@Autowired
	public OrganizationServiceImpl(@Qualifier("organizationDaoImpl") IOrganizationDao baseDao)
	{
		super(baseDao);
	}

	@Override
	public void updateOrg(Organization org)
	{
//		Organization _org = this.get(org.getPkuid());
		
		this.merge(org);
		
		
	}
	
	@Override
	public Map<Long, String> getSelectMajor(){	
		Map<Long, String> map=new HashMap<Long, String>();
		List<Organization> entities=baseDao.listAll();
		for(Organization entity : entities){			
			String leveCode = entity.getLevelCode();
			if(leveCode != null && leveCode.split(",").length == 2){
				map.put(entity.getPkuid(), entity.getName());
			}
		}
		return map;
		
	}

	@Override
	public Map<Long, String> getSelectStuClass()
	{
		Map<Long, String> map=new HashMap<Long, String>();
		List<Organization> entities=baseDao.listAll();
		for(Organization entity : entities){			
			String leveCode = entity.getLevelCode();
			if(leveCode != null && leveCode.split(",").length == 3){
				map.put(entity.getPkuid(), entity.getName());
			}
		}
		return map;
	}

}
