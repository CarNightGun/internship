package com.km.common.service.impl;

import java.util.List;




import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.km.common.bean.AbstractTreeEntity;
import com.km.common.dao.ITreeDao;
import com.km.common.service.ITreeService;
import com.km.util.LazyUtil;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月18日上午10:44:12
 */
public abstract class TreeServiceImpl<PKUID extends Number, EntityType extends AbstractTreeEntity<PKUID, EntityType>, IDaoType extends ITreeDao<PKUID, EntityType>>
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
		List<EntityType> list = this.baseDao.getCriteria().add(Restrictions.isNull("parent")).list();
		for (EntityType entity : list){
			LazyUtil.initializeEntity(entity);
			for (EntityType entityType : entity.getChildren())
			{
				LazyUtil.initializeEntity(entityType.getChildren());
			}
		}
		return list;
	}
}
