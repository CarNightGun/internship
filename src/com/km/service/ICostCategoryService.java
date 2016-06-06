package com.km.service;

import java.util.Map;

import com.km.bean.CostCategory;
import com.km.common.service.ITreeService;
import com.km.dao.ICostCategoryDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月5日下午2:19:39
 */
public interface ICostCategoryService extends ITreeService<Long, CostCategory, ICostCategoryDao>
{
	
	public Map<Long, String> getSelectCostCategory();
	
	public Map<Long, String> getSelectCostClass();

}
