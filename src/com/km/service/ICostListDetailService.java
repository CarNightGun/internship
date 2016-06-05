package com.km.service;

import java.util.Map;
import java.util.Set;

import com.km.bean.CostListDetail;
import com.km.common.service.IBaseService;
import com.km.dao.ICostListDetailDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午10:11:35
 */
public interface ICostListDetailService extends IBaseService<Long, CostListDetail, ICostListDetailDao>
{

	public IPageList<CostListDetail> listPage(CostListDetail search, int pageNo, int pageSize);

	public Set<Long> deleteAndReturnMajorPlanIds(String pkuids);
 
	public void upateData(Long pkuid);
	
	
	public Map<String, Object> getCostPlanUsedInfo(Double inputValue,Long majorPlanId,Long costCategoryId);
}
