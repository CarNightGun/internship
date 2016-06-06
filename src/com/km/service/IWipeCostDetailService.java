package com.km.service;

import com.km.bean.WipeCostDetail;
import com.km.common.service.IBaseService;
import com.km.dao.IWipeCostDetailDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月6日上午10:31:56
 */
public interface IWipeCostDetailService extends IBaseService<Long, WipeCostDetail, IWipeCostDetailDao>
{
	
	public IPageList<WipeCostDetail> listPage(WipeCostDetail search, int pageNo, int pageSize);
 
	public Long saveAndUpdateData(WipeCostDetail wipeCostDetail);
	
	public Long[] updateWipeCostDetails(String pkuids,Long wipeCostPkuid);
}
