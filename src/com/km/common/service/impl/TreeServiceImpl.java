package com.km.common.service.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.km.common.bean.AbstractTreeEntity;
import com.km.common.dao.ITreeDao;
import com.km.common.service.ITreeService;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月18日上午10:44:12
 */
public class TreeServiceImpl<PKUID extends Number, EntityType extends AbstractTreeEntity<PKUID, EntityType>, IDaoType extends ITreeDao<PKUID, EntityType>>
		extends BaseServiceImpl<PKUID, EntityType, IDaoType> implements
		ITreeService<PKUID, EntityType, IDaoType>
{

	public TreeServiceImpl(IDaoType baseDao)
	{
		super(baseDao);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<EntityType> listTree()
	{
		return this.baseDao.getCriteria().add(Restrictions.isNull("parent")).list();
	}
}
