package com.km.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.PractieCostCount;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IPractieCostCountDao;
import com.km.service.IPractieCostCountService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午10:07:18
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class PractieCostCountServiceImpl extends BaseServiceImpl<Long, PractieCostCount, IPractieCostCountDao> implements IPractieCostCountService
{

	@Autowired
	public PractieCostCountServiceImpl(@Qualifier("practieCostCountDaoImpl")IPractieCostCountDao baseDao)
	{
		super(baseDao);
	}

}
