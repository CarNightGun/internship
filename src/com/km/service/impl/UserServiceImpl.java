package com.km.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.Organization;
import com.km.bean.Role;
import com.km.bean.User;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IUserDao;
import com.km.service.IOrganizationService;
import com.km.service.IRoleService;
import com.km.service.IUserService;
import com.km.util.LazyUtil;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月12日下午2:24:42
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class UserServiceImpl extends BaseServiceImpl<Long, User, IUserDao> implements IUserService
{
	
	
	@Resource(name = "roleServiceImpl")
	protected IRoleService roleService;

	@Resource(name = "organizationServiceImpl")
	protected IOrganizationService organizationService;
	

	@Autowired
	public UserServiceImpl(@Qualifier("userDaoImpl") IUserDao baseDao)
	{
		super(baseDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User login(String username, String password)
	{
		Criteria criteria = baseDao.getCriteria();
		criteria.add(Restrictions.eq("accountName", username));
		criteria.add(Restrictions.eq("password", password));
		List<User> users = criteria.list();

		User user = null;
		if (users != null && !users.isEmpty())
		{
			user = users.get(0);
		}

		// 懒加载 提前实例化 Failed to lazily initialize a collection
		if(user != null){
			Hibernate.initialize(user.getRole().getAuthorities());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<User> listPage(User entity, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();
		String name = entity.getName();
		String accountName = entity.getAccountName();

		if (name != null && !name.isEmpty())
		{
			countCriteria.add(Restrictions.eq("name", name));
			listCriteria.add(Restrictions.eq("name", name));
		}
		if (accountName != null && !accountName.isEmpty())
		{
			countCriteria.add(Restrictions.eq("accountName", accountName));
			listCriteria.add(Restrictions.eq("accountName", accountName));
		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<User> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());

		Integer itemsCount = Integer.parseInt(countCriteria.uniqueResult().toString());
		return PageUtil.getPageList(pageSize, pageNo, itemsCount, items);
	}

	@Override
	public void changeAuditState(String pkuids)
	{
		if (pkuids == null || pkuids.isEmpty())
		{
			return;
		}

		Pattern p = Pattern.compile("^([0-9]+,?)*[0-9]+");
		Matcher m = p.matcher(pkuids);
		if (!m.find())
		{
			return;
		}

		String[] pkuidArr = pkuids.split(",");
		for (String pkuid : pkuidArr)
		{
			User u = baseDao.get(Long.parseLong(pkuid));
			if (u.isAudit())
			{
				baseDao.unAudit(u);
			} else
			{
				baseDao.audit(u);
			}
		}

	}

	@Override
	public void delete(String userids)
	{
		if (userids == null || userids.isEmpty())
		{
			return;
		}

		Pattern p = Pattern.compile("^([0-9]+,?)*[0-9]+");
		Matcher m = p.matcher(userids);
		if (!m.find())
		{
			return;
		}
		String[] pkuidArr = userids.split(",");
		for (String pkuid : pkuidArr)
		{
			baseDao.deleteById(Long.parseLong(pkuid));
		}
	}

	@Override
	public void updateRoleOrg(Long id, Long roleId, Long organizationId)
	{
		User user=super.get(id);
		if(roleId!=null && roleId>0){
			Role dbRole=roleService.get(roleId);
			user.setRole(dbRole);
		}
		else
			user.setRole(null);
		if(organizationId!=null && organizationId>0){
			Organization dbOrganization=organizationService.get(organizationId);
			user.setOrganization(dbOrganization);
		}
		else
			user.setOrganization(null);
		super.update(user);
		
	}

	@Override
	public List<User> listAllAndInitOrgAndRole()
	{
		List<User> us = baseDao.listAll();
		for (User user : us)
		{			
			LazyUtil.initializeEntity(user.getOrganization());
			LazyUtil.initializeEntity(user.getRole());
		}
		return us;
	}
}
