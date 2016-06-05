package com.km.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.km.bean.Student;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.ICostPlanDao;
import com.km.dao.IMajorPlanDao;
import com.km.service.IMajorPlanService;
import com.km.service.IStudentService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月31日下午5:48:06
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class MajorPlanServiceImpl extends BaseServiceImpl<Long, MajorPlan, IMajorPlanDao> implements IMajorPlanService
{

	@Resource(name="studentServiceImpl")
	private IStudentService studentService;
	
	@Resource(name="costPlanDaoImpl")
	private ICostPlanDao costPlanDao;
	
	@Autowired
	public MajorPlanServiceImpl(@Qualifier("majorPlanDaoImpl")IMajorPlanDao baseDao)
	{
		super(baseDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<MajorPlan> listPage(MajorPlan entity, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();
		
		if(entity.getMajor() != null){
			String majorName = entity.getMajor().getName();
	
			if (majorName != null )
			{
				countCriteria.createCriteria("major").add(Restrictions.eq("name", majorName));
				listCriteria.createCriteria("major").add(Restrictions.eq("name", majorName));
			}
		}
		 

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<MajorPlan> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());

		Integer itemsCount = Integer.parseInt(countCriteria.uniqueResult().toString());
		return PageUtil.getPageList(pageSize, pageNo, itemsCount, items);
	}

	@Override
	public Map<Long, String> getSelectMajorPlan()
	{
		Map<Long, String> map=new HashMap<Long, String>();
		List<MajorPlan> entities=baseDao.listAll();
		for(MajorPlan entity : entities){			
			map.put(entity.getPkuid(), entity.getMajorPlanName());
		}
		return map;
	}

	@Override
	public void updateMajorPlanData(MajorPlan majorPlan)
	{
		 
		 MajorPlan _majorPlan = baseDao.get(majorPlan.getPkuid());
		 List<CostListDetail> _details = _majorPlan.getCostListDetail();
		 
		 //计划名称
		 _majorPlan.setMajorPlanName(majorPlan.getMajorPlanName());		 
		 //计划说明
		 _majorPlan.setPlanRemarks(majorPlan.getPlanRemarks());		 
		 //专业
		 _majorPlan.setMajor(majorPlan.getMajor());		 
		 //实习经费计划
		 CostPlan costPlan = costPlanDao.get(majorPlan.getCostPlan().getPkuid());
		 _majorPlan.setCostPlan(costPlan);		 
		 	 
		 //所属年份
		 _majorPlan.setPlanYear(costPlan.getPlanYear());
		 
		
		 Double connFee = 0d;//实习联系费用
		 Double collegeFee = 0d;//本专科实习费用
		 
		 Double pStuAmount = 0d;//专业实习费用
		 Double gStuAmount = 0d;//毕业实习费用
		 Double totalStuAmount = 0d;//总费用

		 for (CostListDetail costListDetail : _details)
		{
			 Long cPkuidLong = costListDetail.getCostCategory().getPkuid();
			 Double tmpPrice = costListDetail.getPrice();
			 if( 2 == cPkuidLong || 4 == cPkuidLong){
				 connFee = connFee + tmpPrice;
			 }else{
				 collegeFee = collegeFee + tmpPrice;
			 }
			 
			 
			if(0 == costListDetail.getInternClass()){
				pStuAmount = pStuAmount + tmpPrice;
			}
			
			if(1 == costListDetail.getInternClass()){
				gStuAmount = gStuAmount + tmpPrice;
			}
			totalStuAmount = totalStuAmount + tmpPrice;
		}
		 

		 
		 _majorPlan.setConnectionFee(connFee);
		 _majorPlan.setCollegeStuFee(collegeFee);
		 
		 _majorPlan.setProfessionAmount(pStuAmount);
		 _majorPlan.setGraduateAmount(gStuAmount);
		//实习费用
		 _majorPlan.setTotalAmount(totalStuAmount);	
		 		 
		 //比例
		 _majorPlan.setProportion(null);
		 
		 Long pStuNum = 0L;
		 Long gStuNum = 0L;
		//专业实习人数,毕业实习人数
		 Long majorid = _majorPlan.getMajor().getPkuid();		 
		 Long gYear = _majorPlan.getPlanYear()-4;		 
		 List<Student> sgs = studentService.getStudentsByMajorAndStuYear(majorid, gYear);		 
		 gStuNum = (long) sgs.size();
		 List<Student> sps = studentService.getStudentsByMajorAndStuYear(majorid, gYear+1);		 
		 gStuNum = (long) sps.size();
		 _majorPlan.setGraduateNumber(gStuNum);
		 _majorPlan.setProfessionNumber(pStuNum);
		 
		 baseDao.update(_majorPlan);		 
	}
	
	@Override
	public void updateMajorPlanData(Long pkuid){
		
		MajorPlan majorPlan = this.get(pkuid);
		if(majorPlan != null){
			this.updateMajorPlanData(majorPlan);			
		}	
	}

}
