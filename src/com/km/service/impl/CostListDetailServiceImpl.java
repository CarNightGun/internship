package com.km.service.impl;
 

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.CostListDetail;
import com.km.bean.CostPlan;
import com.km.bean.MajorPlan;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.ICostListDetailDao;
import com.km.service.ICostCategoryService;
import com.km.service.ICostListDetailService;
import com.km.service.ICostPlanService;
import com.km.service.IMajorPlanService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午10:12:33
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class CostListDetailServiceImpl extends BaseServiceImpl<Long, CostListDetail, ICostListDetailDao> implements ICostListDetailService
{

	@Resource(name = "majorPlanServiceImpl")
	private IMajorPlanService majorPlanService;
	
	@Resource(name = "costPlanServiceImpl")
	private ICostPlanService costPlanService;
	
	@Resource(name = "costCategoryServiceImpl")
	private ICostCategoryService costCategoryService;
	
	
	@Autowired
	public CostListDetailServiceImpl(@Qualifier("costListDetailDaoImpl")ICostListDetailDao baseDao)
	{
		super(baseDao);
 
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<CostListDetail> listPage(CostListDetail search, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();

		if (search != null)
		{
			if (search.getMajorPlan() !=null && search.getMajorPlan().getPkuid() != null  )
			{
				countCriteria.createCriteria("majorPlan").add(Restrictions.eq("pkuid", search.getMajorPlan().getPkuid()));
				listCriteria.createCriteria("majorPlan").add(Restrictions.eq("pkuid", search.getMajorPlan().getPkuid()));
			}
			
			if (search.getCostCategory() !=null && search.getCostCategory().getPkuid() != null  )
			{
				countCriteria.createCriteria("costCategory").add(Restrictions.eq("pkuid", search.getCostCategory().getPkuid()));
				listCriteria.createCriteria("costCategory").add(Restrictions.eq("pkuid", search.getCostCategory().getPkuid()));
			}
		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<CostListDetail> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(countCriteria.uniqueResult().toString());

		return PageUtil.getPageList(pageSize, pageNo, count, items);
	}

	@Override
	public Set<Long> deleteAndReturnMajorPlanIds(String pkuids)
	{
		if (pkuids == null || pkuids.isEmpty())
		{
			return null;
		}

		Pattern p = Pattern.compile("^([0-9]+,?)*[0-9]+");
		Matcher m = p.matcher(pkuids);
		if (!m.find())
		{
			return null;
		}
		String[] pkuidArr = pkuids.split(",");
		Set<Long> ids = new HashSet<Long>();
		for (String pkuid : pkuidArr)
		{

			CostListDetail _costListDetail = this.get(Long.parseLong(pkuid));
			if (_costListDetail != null && _costListDetail.getMajorPlan() != null)
			{
				ids.add(_costListDetail.getMajorPlan().getPkuid());
			}
			baseDao.deleteById(Long.parseLong(pkuid));
		}

		return ids;
		
	}

	@Override
	public void upateData(Long pkuid)
	{
		CostListDetail _costListDetail = baseDao.get(pkuid);		
		majorPlanService.updateMajorPlanData(_costListDetail.getMajorPlan().getPkuid());
	}

	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> getCostPlanUsedInfo(Double inputValue, Long majorPlanId,
			Long costCategoryId)
	{
		Double usableTotalAmount = 0d;
		Double residueTotalAmount = 0d;
		Double _tempAmount = 0d;
		// 是否是实习联系费
		Boolean isConnectionFee = null;
		try
		{
			MajorPlan majorPlan = majorPlanService.get(majorPlanId);
			Long costPlanId = majorPlan.getCostPlan().getPkuid();
			CostPlan costPlan = costPlanService.get(costPlanId);

			if (costCategoryId == 2 || costCategoryId == 4)
			{
				isConnectionFee = true;
			}else{
				isConnectionFee = false;
			}

			List<MajorPlan> majorPlans = costPlan.getMajorPlans();
			// 总可用费用
			if (isConnectionFee)
			{
				usableTotalAmount = costPlan.getConnectionFee();
				for (MajorPlan mp : majorPlans)
				{
					_tempAmount = _tempAmount + mp.getConnectionFee();
				}

			} else
			{
				usableTotalAmount = costPlan.getCollegeStuFee();
				for (MajorPlan mp : majorPlans)
				{
					_tempAmount = _tempAmount + mp.getCollegeStuFee();
				}
			}
			residueTotalAmount = usableTotalAmount - _tempAmount - inputValue;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usableTotalAmount", usableTotalAmount);
			map.put("isConnectionFee", isConnectionFee);
			map.put("residueTotalAmount", residueTotalAmount);
			return map;
		}
	}

}
