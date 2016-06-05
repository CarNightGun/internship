package com.km.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.CostPlan;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.ICostPlanDao;
import com.km.service.ICostPlanService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月29日下午9:04:56
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class CostPlanServiceImpl extends BaseServiceImpl<Long, CostPlan, ICostPlanDao> implements ICostPlanService
{

	@Autowired
	public CostPlanServiceImpl(@Qualifier("costPlanDaoImpl")ICostPlanDao baseDao)
	{
		super(baseDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<CostPlan> listPage(CostPlan entity, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();
		
		Long planYear = entity.getPlanYear();

		if (planYear != null )
		{
			countCriteria.add(Restrictions.eq("planYear", planYear));
			listCriteria.add(Restrictions.eq("planYear", planYear));
		}
		 

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<CostPlan> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());

		Integer itemsCount = Integer.parseInt(countCriteria.uniqueResult().toString());
		return PageUtil.getPageList(pageSize, pageNo, itemsCount, items);
	}
	
	@Override
	public Map<Long, String> getSelectSource(){	
		Map<Long, String> map=new HashMap<Long, String>();
		List<CostPlan> entities=baseDao.listAll();
		for(CostPlan entity : entities){						 
				map.put(entity.getPkuid(), entity.getCostPlanName());
		}
		return map;
		
	}

}
