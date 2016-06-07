package com.km.service;

import com.km.bean.ZCostCount;
import com.km.common.service.IBaseService;
import com.km.dao.IZCostCountDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午10:12:57
 */
public interface IZCostCountService extends IBaseService<Long, ZCostCount, IZCostCountDao>
{

	public IPageList<ZCostCount> listPage(ZCostCount search, int pageNo, int pageSize);
	
	
	public ZCostCount Maincout(Integer year);

}
