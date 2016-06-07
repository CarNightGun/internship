package com.km.service;

import java.util.List;
import java.util.Map;

import com.km.bean.MajorPlan;
import com.km.common.service.IBaseService;
import com.km.dao.IMajorPlanDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月31日下午5:43:19
 */
public interface IMajorPlanService extends IBaseService<Long, MajorPlan , IMajorPlanDao>
{

	public IPageList<MajorPlan> listPage(MajorPlan searchMajorPlan, int pageNo, int pageSize);
	
	public Map<Long, String> getSelectMajorPlan();
	
	
	public void updateMajorPlanData(MajorPlan majorPlan);

	public void updateMajorPlanData(Long pkuid);
	
	 
	public List<MajorPlan> getMajorPlansByYear(Long year);

}
