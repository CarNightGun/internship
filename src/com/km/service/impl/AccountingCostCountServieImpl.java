package com.km.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.AccountingCostCount;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IAccountingCostCountDao;
import com.km.service.IAccountingCostCountService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午10:00:15
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class AccountingCostCountServieImpl extends BaseServiceImpl<Long, AccountingCostCount, IAccountingCostCountDao> implements IAccountingCostCountService
{

	@Autowired
	public AccountingCostCountServieImpl(@Qualifier("accountingCostCountDaoImpl")IAccountingCostCountDao baseDao)
	{
		super(baseDao);
	}

}
