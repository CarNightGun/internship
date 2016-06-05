package com.km.service;

import com.km.bean.CostPlan;
import com.km.bean.User;
import com.km.common.service.IBaseService;
import com.km.dao.ICostPlanDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月29日下午9:00:12
 */
public interface ICostPlanService extends IBaseService<Long, CostPlan , ICostPlanDao>
{

	public IPageList<CostPlan> listPage(CostPlan entity, int pageNo, int pageSize);
}
