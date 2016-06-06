package com.km.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
 



import com.km.bean.AuditHistory;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IAuditHistoryDao;
import com.km.service.IAuditHistoryService;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月6日下午8:49:46
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class AuditHistoryServiceImpl extends BaseServiceImpl<Long, AuditHistory,  IAuditHistoryDao> implements IAuditHistoryService
{

	@Autowired
	public AuditHistoryServiceImpl(@Qualifier("auditHistoryDaoImpl")IAuditHistoryDao baseDao)
	{
		super(baseDao);
	}

}
