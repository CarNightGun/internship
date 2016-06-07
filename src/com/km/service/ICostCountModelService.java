package com.km.service;

import com.km.bean.CostCountModel;
import com.km.common.service.IBaseService;
import com.km.dao.ICostCountModelDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日下午1:38:52
 */
public interface ICostCountModelService extends IBaseService<Long, CostCountModel, ICostCountModelDao>
{

	public IPageList<CostCountModel> listPage(CostCountModel search, int pageNo, int pageSize);

	
	public void countInfoByYear(Long year);
}
