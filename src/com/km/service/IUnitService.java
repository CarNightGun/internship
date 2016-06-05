package com.km.service;

import com.km.bean.Unit;
import com.km.common.service.IBaseService;
import com.km.dao.IUnitDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午9:58:10
 */
public interface IUnitService extends IBaseService<Long, Unit, IUnitDao>
{

	public IPageList<Unit> listPage(Unit search, int pageNo, int pageSize);

	public Unit getEditUtil(Long pkuid);
}
