package com.km.service;

import java.util.Map;

import com.km.bean.WipeCost;
import com.km.common.service.IBaseService;
import com.km.dao.IWipeCostDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月6日上午12:48:10
 */
public interface IWipeCostService extends IBaseService<Long, WipeCost, IWipeCostDao>
{

	
	public IPageList<WipeCost> listPage(WipeCost entity, int pageNo, int pageSize);
	
	public Map<Long, String> getSelectWipeCost();
	
	public Map<Long, String> getSelectUnAuditWipeCost();
	
	public void updateWipeCostByDetailId(Long wipeCostDetailId);
	
	public Long updateWipeCostData(WipeCost wipeCost);
	
	
	public WipeCost getEidtWipeCost(Long pkuid);
	
	
	public WipeCost getAuditTipWipeCost(Long pkuid);
}
