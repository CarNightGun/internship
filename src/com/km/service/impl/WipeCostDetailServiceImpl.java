package com.km.service.impl;

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

import com.km.bean.CostCategory;
import com.km.bean.WipeCost;
import com.km.bean.WipeCostDetail;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IWipeCostDetailDao;
import com.km.service.ICostCategoryService;
import com.km.service.IWipeCostDetailService;
import com.km.service.IWipeCostService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月6日上午10:33:11
 */

@Service
@org.springframework.transaction.annotation.Transactional
public class WipeCostDetailServiceImpl extends BaseServiceImpl<Long, WipeCostDetail, IWipeCostDetailDao>
		implements IWipeCostDetailService
{
	
	@Resource(name = "wipeCostServiceImpl")
	 protected IWipeCostService wipeCostService;
	
	@Resource(name="costCategoryServiceImpl")
	private ICostCategoryService costCategoryService;

	@Autowired
	public WipeCostDetailServiceImpl(@Qualifier("wipeCostDetailDaoImpl")IWipeCostDetailDao baseDao)
	{
		super(baseDao); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<WipeCostDetail> listPage(WipeCostDetail search, int pageNo, int pageSize)
	{
		 
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();

		if (search != null)
		{
			if (search.getWipeCost() !=null && search.getWipeCost().getPkuid() != null  )
			{
				countCriteria.createCriteria("wipeCost").add(Restrictions.eq("pkuid", search.getWipeCost().getPkuid()));
				listCriteria.createCriteria("wipeCost").add(Restrictions.eq("pkuid", search.getWipeCost().getPkuid()));
			}
			
			if (search.getAccounting() !=null && search.getAccounting().getPkuid() != null  )
			{
				countCriteria.createCriteria("accounting").add(Restrictions.eq("pkuid", search.getAccounting().getPkuid()));
				listCriteria.createCriteria("accounting").add(Restrictions.eq("pkuid", search.getAccounting().getPkuid()));
			}

		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<WipeCostDetail> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(countCriteria.uniqueResult().toString());

		return PageUtil.getPageList(pageSize, pageNo, count, items);
	}

	@Override
	public Long saveAndUpdateData(WipeCostDetail wipeCostDetail)
	{
		Long wipeCostId = wipeCostDetail.getWipeCost().getPkuid();
		
		 WipeCost wipeCost = wipeCostService.get(wipeCostId);
		 
		 Long costClassId = wipeCostDetail.getCostClass().getPkuid();
		 
		 CostCategory costClass = costCategoryService.get(costClassId);
		 
		 CostCategory accounting = costClass.getParent();
		 wipeCostDetail.setAccounting(accounting);
		 wipeCostDetail.setCostClass(costClass);
		 
		 wipeCostDetail.setAccountingCode(accounting.getCategoryCode());
		 wipeCostDetail.setAccountingName(accounting.getCategoryName());
		 wipeCostDetail.setWipeCost(wipeCost);
		 if(wipeCostDetail.getPkuid() != null && wipeCostDetail.getPkuid()> 0){
			 baseDao.update(wipeCostDetail);
			 return wipeCostDetail.getPkuid();
		 }else{
			 return baseDao.save(wipeCostDetail);
		 }
		
	}

	@Override
	public Long[] updateWipeCostDetails(String pkuids, Long wipeCostPkuid)
	{
		if (pkuids == null || pkuids.isEmpty())
		{
			return null ;
		}

		Pattern p = Pattern.compile("^([0-9]+,?)*[0-9]+");
		Matcher m = p.matcher(pkuids);
		if (!m.find())
		{
			return null ;
		}
		String[] pkuidArr = pkuids.split(",");
		Long[] wipeCostIds = new Long[pkuidArr.length];
		int i =0;
		for (String pkuid : pkuidArr)
		{
			WipeCostDetail wcd =baseDao.get(Long.parseLong(pkuid));
			wipeCostIds[i++] = wcd.getPkuid();
			if(wipeCostPkuid != null){
				wcd.setWipeCost(wipeCostService.get(wipeCostPkuid));
			}else{
				wcd.setWipeCost(null);
			}
//			baseDao.update(wcd);
			baseDao.delete(wcd);
		}
		return wipeCostIds;
		
	}

	

	 
}
