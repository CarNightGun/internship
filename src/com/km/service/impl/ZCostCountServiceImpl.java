package com.km.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.CostListDetail;
import com.km.bean.MajorPlan;
import com.km.bean.WipeCost;
import com.km.bean.WipeCostDetail;
import com.km.bean.ZCostCount;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IZCostCountDao;
import com.km.service.ICostCategoryService;
import com.km.service.ICostListDetailService;
import com.km.service.IMajorPlanService;
import com.km.service.IOrganizationService;
import com.km.service.IWipeCostDetailService;
import com.km.service.IWipeCostService;
import com.km.service.IZCostCountService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午10:14:22
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class ZCostCountServiceImpl  extends BaseServiceImpl<Long, ZCostCount, IZCostCountDao> implements IZCostCountService
{

	@Autowired
	public ZCostCountServiceImpl(@Qualifier("zCostCountDaoImpl")IZCostCountDao baseDao)
	{
		super(baseDao);
	}

	 

//	@Resource(name="wipeCostDetailServiceImpl")
//	private IWipeCostDetailService wipeCostDetailService;
//	
//	@Resource(name="wipeCostServiceImpl")
//	private IWipeCostService wipeCostService;
//	
//	@Resource(name="costListDetailServiceImpl")
//	private ICostListDetailService costListDetailService;	
//	
//	@Resource(name="majorPlanServiceImpl")
//	private IMajorPlanService majorPlanService;
//	
//	@Resource(name = "organizationServiceImpl")
//	private IOrganizationService organizationService;
//	
//	 @Resource(name="costCategoryServiceImpl")
//	private ICostCategoryService costCategoryService;
	
	 

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<ZCostCount> listPage(ZCostCount search, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();

		if (search != null)
		{
//			if (search.getGraPractie().get!=null && search.getMajor().getPkuid() != null  )
//			{
//				countCriteria.createCriteria("major").add(Restrictions.eq("pkuid", search.getMajor().getPkuid()));
//				listCriteria.createCriteria("major").add(Restrictions.eq("pkuid", search.getMajor().getPkuid()));
//			}
//			
//			if ( search.getStuClass() !=null && search.getStuClass().getPkuid() != null  )
//			{
//				countCriteria.createCriteria("stuClass").add(Restrictions.eq("pkuid", search.getStuClass().getPkuid()));
//				listCriteria.createCriteria("stuClass").add(Restrictions.eq("pkuid", search.getStuClass().getPkuid()));
//			}
//			
//			if (search.getStuYear() != null && !search.getStuYear().isEmpty())
//			{
//				countCriteria.add(Restrictions.eq("stuYear", search.getStuYear()));
//				listCriteria.add(Restrictions.eq("stuYear", search.getStuYear()));
//			}
//			
//			if (search.getUnit() != null)
//			{
//				if(search.getUnit().getPkuid() != null){
//					countCriteria.createCriteria("unit").add(Restrictions.eq("pkuid", search.getUnit().getPkuid()));
//					listCriteria.createCriteria("unit").add(Restrictions.eq("pkuid", search.getUnit().getPkuid()));
//				}else{
//					countCriteria.add(Restrictions.isNull("unit"));
//					listCriteria.add(Restrictions.isNull("unit"));
//				}
//			}
		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<ZCostCount> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(countCriteria.uniqueResult().toString());

		return PageUtil.getPageList(pageSize, pageNo, count, items);
	}

	
	
	@Override
	public ZCostCount Maincout(Integer year)
	{
//		ZCostCount zCostCount = new ZCostCount();
		
//		//获得计划明细
//		List<CostListDetail> clds = this.getAllCostListDetails((long)year);	
//		Map<String, Double> _cldMap = new HashMap<String, Double>();
//		String _cldkey = "";
//		for (CostListDetail cld : clds)
//		{
//			_cldkey = cld.getMajorPlan().getMajor().getPkuid()+"-"+cld.getInternClass()+"-"+cld.getCostCategory().getPkuid();		
//			if(_cldMap.containsKey(_cldkey)){
//				_cldMap.put(_cldkey, _cldMap.get(_cldkey)+cld.getPrice());
//			}else{
//				_cldMap.put(_cldkey, cld.getPrice());
//			}
//		}
//		
//		//获得已报销明细
//		List<WipeCostDetail>  wcds = this.getAllWipedCostDetails((long)year);
//		Map<String, Double> _wcdMap = new HashMap<String, Double>();
//		String _wcdKey = "";
//		for (WipeCostDetail wcd : wcds)
//		{
//			_wcdKey = wcd.getWipeCost().getMajor().getPkuid()+"-"+wcd.getInternClass()+"-"+wcd.getAccounting().getPkuid();
//			if(_wcdMap.containsKey(_wcdKey)){
//				_wcdMap.put(_wcdKey, _wcdMap.get(_wcdKey)+wcd.getAmount());
//			}else{
//				_wcdMap.put(_wcdKey, wcd.getAmount());
//			}
//		}
//		
//		//设置统计明细map
//		Map<String, CostCountModel> ccmMap = new HashMap<String, CostCountModel>();		
//		Set<String> _cldKeys = _cldMap.keySet();
//		for (String cldkey : _cldKeys)
//		{
//			if(ccmMap.containsKey(cldkey)){
//				CostCountModel _ccm = ccmMap.get(cldkey);
//				_ccm.setPlanAmount(_ccm.getPlanAmount()+_cldMap.get(cldkey));
//				ccmMap.put(cldkey, _ccm);
//			}else{
//				CostCountModel _ccm = SpringUtil.getObject(CostCountModel.class);
//				_ccm.setPlanAmount(_cldMap.get(cldkey));
//				String[] cldkeyInfos = cldkey.split("-");
//				_ccm.setCountYear((long)year);
//				_ccm.setMajor(organizationService.get(Long.valueOf(cldkeyInfos[0])));
//				_ccm.setInternClass(Integer.valueOf(cldkeyInfos[1]));
//				_ccm.setAccounting(costCategoryService.get(Long.valueOf(cldkeyInfos[2])));
//				ccmMap.put(cldkey, _ccm);
//			}
//		}
//		
//		Set<String> _wcdKeys = _wcdMap.keySet();
//		for (String wcdKey : _wcdKeys)
//		{
//			if(ccmMap.containsKey(wcdKey)){
//				CostCountModel _ccm = ccmMap.get(wcdKey);
//				_ccm.setWipedAmount(_ccm.getPlanAmount()+_wcdMap.get(wcdKey));
//				ccmMap.put(wcdKey, _ccm);
//			}else{
//				CostCountModel _ccm = SpringUtil.getObject(CostCountModel.class);
//				_ccm.setWipedAmount(_wcdMap.get(wcdKey));
//				String[] cldkeyInfos = wcdKey.split("-");
//				_ccm.setCountYear((long)year);
//				_ccm.setMajor(organizationService.get(Long.valueOf(cldkeyInfos[0])));
//				_ccm.setInternClass(Integer.valueOf(cldkeyInfos[1]));
//				_ccm.setAccounting(costCategoryService.get(Long.valueOf(cldkeyInfos[2])));
//				ccmMap.put(wcdKey, _ccm);
//			}
//		}
//		
//		
//		Iterator<Map.Entry<String, CostCountModel>> _ccmIterator = ccmMap.entrySet().iterator();
//		while (_ccmIterator.hasNext())
//		{
//			Map.Entry<String, CostCountModel> _ccmMEntry = _ccmIterator.next();
//			
//		}
		
		
		
		
		return null;
	}

//	private List<CostListDetail> getAllCostListDetails(Long year){
//		
//		List<MajorPlan> _mps = majorPlanService.getMajorPlansByYear(year);
//		List<CostListDetail> clds = null;
//		for (MajorPlan majorPlan : _mps)
//		{
//			if(clds == null){
//				clds = majorPlan.getCostListDetail();
//			}else{
//				clds.addAll(majorPlan.getCostListDetail());
//			}
//		}
//		  
//		return clds;
//	}
	
//	private List<WipeCostDetail> getAllWipedCostDetails(Long year){
//		
//		List<WipeCost> _wcs = wipeCostService.getWipeCostsByYear(year);
//		List<WipeCostDetail>  wcds = null;
//		for (WipeCost wc : _wcs)
//		{
//			if("已通过".equals(wc.getAuditState()) || "已确认".equals(wc.getAuditState())){
//				if(wcds == null){
//					wcds = wc.getWipeCostDetails();
//				}else{
//					wcds.addAll(wc.getWipeCostDetails());
//				}
//			}
//		}	  
//		return wcds;
//	}
	

}
