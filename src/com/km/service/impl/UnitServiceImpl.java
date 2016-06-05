package com.km.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.Unit;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IUnitDao;
import com.km.service.IUnitService;
import com.km.util.LazyUtil;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午9:59:36
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class UnitServiceImpl extends BaseServiceImpl<Long, Unit, IUnitDao> implements IUnitService
{

	@Autowired
	public UnitServiceImpl(@Qualifier("unitDaoImpl")IUnitDao baseDao)
	{
		super(baseDao);
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<Unit> listPage(Unit search, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();

		if (search != null)
		{
			if (search.getUnitName() != null && !search.getUnitName().isEmpty())
			{
				countCriteria.add(Restrictions.eq("unitName", search.getUnitName()));
				listCriteria.add(Restrictions.eq("unitName", search.getUnitName()));
			}
		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<Unit> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(countCriteria.uniqueResult().toString());

		return PageUtil.getPageList(pageSize, pageNo, count, items);
	}

	@Override
	public Unit getEditUtil(Long pkuid)
	{
		Unit u = this.get(pkuid);
		
		LazyUtil.initializeEntity(u.getStudents());
		return u;
	}

}
