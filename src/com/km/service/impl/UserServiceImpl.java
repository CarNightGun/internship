package com.km.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.km.bean.User;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IUserDao;
import com.km.service.IUserService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月12日下午2:24:42
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<Long, User, IUserDao> implements IUserService
{

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
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<User> listPage(User entity, int pageNo, int pageSize)
	{
//		 Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();
//		String name = entity.getName();
//		String accountName = entity.getAccountName();

		
		
		
		
//		if (name != null && !name.isEmpty())
//		{
////			 countCriteria.add(Restrictions.eq("name", name));
//			listCriteria.add(Restrictions.eq("name", name));
//		}
//		if (accountName != null && !accountName.isEmpty())
//		{
////			 countCriteria.add(Restrictions.eq("accountName", accountName));
//			listCriteria.add(Restrictions.eq("accountName", accountName));
//		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<User> items = listCriteria.list();
//		 countCriteria.setProjection(Projections.rowCount());
		listCriteria.setProjection(Projections.rowCount());
		Integer itemsCount = Integer.parseInt(listCriteria.uniqueResult().toString());
		return PageUtil.getPageList(pageSize, pageNo, itemsCount, items);
	}
}
