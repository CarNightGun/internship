package com.km.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.CostCategory;
import com.km.bean.CostListDetail;
import com.km.bean.MajorPlan;
import com.km.bean.WipeCost;
import com.km.bean.WipeCostDetail;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IWipeCostDao;
import com.km.service.IWipeCostDetailService;
import com.km.service.IWipeCostService;
import com.km.util.CommonUtil;
import com.km.util.LazyUtil;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月6日上午12:49:24
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class WipeCostServiceImpl extends BaseServiceImpl<Long, WipeCost, IWipeCostDao> implements
		IWipeCostService
{
	@Resource(name = "wipeCostDetailServiceImpl")
	 protected IWipeCostDetailService wipeCostDetailService;
	
	@Autowired
	public WipeCostServiceImpl(@Qualifier("wipeCostDaoImpl")IWipeCostDao baseDao)
	{
		super(baseDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<WipeCost> listPage(WipeCost entity, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();
		if(entity != null){
		
			if (entity.getMajor() !=null && entity.getMajor().getPkuid() != null  )
			{
				countCriteria.createCriteria("major").add(Restrictions.eq("pkuid", entity.getMajor().getPkuid()));
				listCriteria.createCriteria("major").add(Restrictions.eq("pkuid", entity.getMajor().getPkuid()));
			}
			
			if (entity.getMajorPlan() !=null && entity.getMajorPlan().getPkuid() != null  )
			{
				countCriteria.createCriteria("majorPlan").add(Restrictions.eq("pkuid", entity.getMajorPlan().getPkuid()));
				listCriteria.createCriteria("majorPlan").add(Restrictions.eq("pkuid", entity.getMajorPlan().getPkuid()));
			}
			
			if (entity.getAuditState() !=null)
			{
				countCriteria.add(Restrictions.eq("auditState",entity.getAuditState()));
				listCriteria.add(Restrictions.eq("auditState", entity.getAuditState()));
			}
			
			if(entity.getHeadDepartment() != null && entity.getHeadDepartment().getPkuid() != null){
				
				countCriteria.createCriteria("headDepartment").add(Restrictions.eq("pkuid", entity.getHeadDepartment().getPkuid()));
				listCriteria.createCriteria("headDepartment").add(Restrictions.eq("pkuid", entity.getHeadDepartment().getPkuid()));
				
			}
			
			if(entity.getTeachingSecretary() != null && entity.getTeachingSecretary().getPkuid() != null){
				
				countCriteria.createCriteria("teachingSecretary").add(Restrictions.eq("pkuid", entity.getTeachingSecretary().getPkuid()));
				listCriteria.createCriteria("teachingSecretary").add(Restrictions.eq("pkuid", entity.getTeachingSecretary().getPkuid()));
 
				countCriteria.add(Restrictions.isNull("headDepartment"));
				listCriteria.add(Restrictions.isNull("headDepartment"));
			}
	
		}
		
		listCriteria.addOrder(Order.desc("createTime"));
		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<WipeCost> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());

		Integer itemsCount = Integer.parseInt(countCriteria.uniqueResult().toString());
		return PageUtil.getPageList(pageSize, pageNo, itemsCount, items);
	} 
	
	@Override
	public Map<Long, String> getSelectWipeCost()
	{
		Map<Long, String> map=new HashMap<Long, String>();
		List<WipeCost> entities=baseDao.listAll("createTime", false);
		for(WipeCost entity : entities){						 
			map.put(entity.getPkuid(), entity.getWipeCode()+"-"+entity.getWipeCostName());		 
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, String> getSelectUnAuditWipeCost()
	{
		Map<Long, String> map=new HashMap<Long, String>();
		Criteria listCriteria=baseDao.getCriteria();
		listCriteria.add(Restrictions.or(
					Restrictions.eq("auditState","草稿"),
					Restrictions.eq("auditState","未通过")
										)
						);
			
		List<WipeCost> entities = listCriteria.list();
		for(WipeCost entity : entities){						 
			map.put(entity.getPkuid(), entity.getWipeCode()+"-"+entity.getWipeCostName());		 
		}
		return map;
	}

	@Override
	public void updateWipeCostByDetailId(Long wipeCostDetailId)
	{
		 WipeCostDetail wipeCostDetail = wipeCostDetailService.get(wipeCostDetailId);
		 
		 this.updateWipeCostData(baseDao.get(wipeCostDetail.getWipeCost().getPkuid()));		 
	}

	@Override
	public Long updateWipeCostData(WipeCost wipeCost)
	{
		if(wipeCost.getPkuid() != null && wipeCost.getPkuid() >0){
			WipeCost __wipeCost = baseDao.get(wipeCost.getPkuid());
			List<WipeCostDetail> wipeCostDetails = __wipeCost.getWipeCostDetails();
			
			Double totalAmount = 0d;
			
			for (WipeCostDetail wcd : wipeCostDetails)
			{
				totalAmount = totalAmount + wcd.getAmount();
			}
			
			wipeCost.setWillWipedCost(totalAmount);
			
			baseDao.merge(wipeCost);
			return wipeCost.getPkuid();
		}else{
			return baseDao.save(wipeCost);
		}
 
	}

	@Override
	public WipeCost getEidtWipeCost(Long pkuid)
	{
		WipeCost wc = this.get(pkuid);
		LazyUtil.initializeEntity(wc.getWipeCostDetails());
		LazyUtil.initializeEntity(wc.getAuditHistorys());
		return wc;
	}

	@Override
	public WipeCost getAuditTipWipeCost(Long pkuid)
	{
		WipeCost _wc = this.get(pkuid);
		LazyUtil.initializeEntity(_wc.getAuditHistorys());
		
		MajorPlan _mp = _wc.getMajorPlan();
		List<WipeCost> wipeCosts = _mp.getWipeCosts();

		List<WipeCostDetail> _wcds = null;
		Map<String, Double> _wcdMap = new HashMap<String, Double>();
		String _wcdkey = "";
		for (WipeCost wc : wipeCosts)
		{
			if("已通过".equals(wc.getAuditState()) || "已确认".equals(wc.getAuditState())){
				_wcds = wc.getWipeCostDetails();
				for (WipeCostDetail _wcd : _wcds)
				{
					Double amount = _wcd.getAmount();
					_wcdkey =  _wcd.getInternClass()+"-"+_wcd.getAccounting().getPkuid();
					if(_wcdMap.containsKey(_wcdkey)){
						_wcdMap.put(_wcdkey, _wcdMap.get(_wcdkey)+amount);
					}else{				
						_wcdMap.put(_wcdkey, amount);
					}
				}				
			}
			
		}
		
		List<CostListDetail> clds = _mp.getCostListDetail();
		Map<String, Double> cldMap = new HashMap<String, Double>();
		String cldkey = "";
		for (CostListDetail cld : clds)
		{
			Double amount = cld.getPrice();			
			cldkey = cld.getInternClass()+"-"+cld.getCostCategory().getPkuid();
			if(cldMap.containsKey(cldkey)){
				cldMap.put(cldkey, cldMap.get(cldkey)+amount);
			}else{				
				cldMap.put(cldkey, amount);
			}
		}		
		
		List<WipeCostDetail> wcds = _wc.getWipeCostDetails();
		String wcdkey = "";
		for (WipeCostDetail wcd : wcds)
		{
			wcdkey =  wcd.getInternClass()+"-"+wcd.getAccounting().getPkuid();
			Double leftPlanAmount = CommonUtil.formatDouble(cldMap.get(wcdkey)) - CommonUtil.formatDouble(_wcdMap.get(wcdkey));
			wcd.setLeftPlanAmount(leftPlanAmount);
		}
		return _wc;
	}
}
