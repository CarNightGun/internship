package com.km.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.CostCategory;
import com.km.common.service.impl.TreeServiceImpl;
import com.km.dao.ICostCategoryDao;
import com.km.service.ICostCategoryService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月5日下午2:20:58
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class CostCategoryServiceImpl extends TreeServiceImpl<Long, CostCategory, ICostCategoryDao>
implements ICostCategoryService
{
	@Autowired
	public CostCategoryServiceImpl(@Qualifier("costCategoryDaoImpl") ICostCategoryDao baseDao)
	{
		super(baseDao);
	}

	@Override
	public Map<Long, String> getSelectCostCategory()
	{
		Map<Long, String> map=new HashMap<Long, String>();
		List<CostCategory> entities=baseDao.listAll();
		for(CostCategory entity : entities){			
			String leveCode = entity.getLevelCode();
			if(leveCode != null && leveCode.equals("1")){
				map.put(entity.getPkuid(), entity.getCategoryName());
			}
		}
		return map;
	}

	@Override
	public Map<Long, String> getSelectCostClass()
	{
		Map<Long, String> map=new HashMap<Long, String>();
		List<CostCategory> entities=baseDao.listAll();
		for(CostCategory entity : entities){			
			String leveCode = entity.getLevelCode();
			if(leveCode != null && leveCode.equals("2")){
				map.put(entity.getPkuid(), entity.getCategoryName()+" || "+entity.getParent().getCategoryCode()+"_"+entity.getParent().getCategoryName());
			}
		}
		return map;
	}
	
}
