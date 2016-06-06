package com.km.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.Authority;
import com.km.bean.Role;
import com.km.bean.User;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IRoleDao;
import com.km.service.IAuthorityService;
import com.km.service.IRoleService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日上午8:11:17
 */

@Service
@org.springframework.transaction.annotation.Transactional
public class RoleServiceImpl extends BaseServiceImpl<Long, Role, IRoleDao> implements IRoleService
{

	@Resource(name = "authorityServiceImpl")
	protected IAuthorityService authorityService;

	@Autowired
	public RoleServiceImpl(@Qualifier("roleDaoImpl") IRoleDao baseDao)
	{
		super(baseDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<Role> listPage(Role search, int pageNo, int pageSize)
	{

		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();

		if (search != null)
		{
			if (search.getName() != null && !search.getName().isEmpty())
			{
				countCriteria.add(Restrictions.eq("name", search.getName()));
				listCriteria.add(Restrictions.eq("name", search.getName()));
			}
		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<Role> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(countCriteria.uniqueResult().toString());

		return PageUtil.getPageList(pageSize, pageNo, count, items);
	}

	@Override
	public void saveAuthorize(Long roleId, Long[] authorityIds)
	{
		Role role = super.get(roleId);
		List<Authority> authorities = new ArrayList<Authority>();
		if (authorityIds.length > 0)
		{
			for (Long authorityId : authorityIds)
			{
				authorities.add(authorityService.get(authorityId));
			}
		}
		role.setAuthorities(authorities);
		super.update(role);

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
			Role u = baseDao.get(Long.parseLong(pkuid));
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
	public void delete(String roleids)
	{
		if (roleids == null || roleids.isEmpty())
		{
			return;
		}

		Pattern p = Pattern.compile("^([0-9]+,?)*[0-9]+");
		Matcher m = p.matcher(roleids);
		if (!m.find())
		{
			return;
		}
		String[] pkuidArr = roleids.split(",");
		for (String pkuid : pkuidArr)
		{
			baseDao.deleteById(Long.parseLong(pkuid));
		}
		
	}

}
