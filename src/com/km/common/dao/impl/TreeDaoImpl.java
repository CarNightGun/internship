package com.km.common.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.km.common.bean.AbstractTreeEntity;
import com.km.common.dao.ITreeDao;
import com.km.util.KmException;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月17日下午4:39:26
 */
public abstract class TreeDaoImpl<PKUID extends Number, EntityType extends AbstractTreeEntity<PKUID, EntityType>>
		extends BaseDaoImpl<PKUID, EntityType> implements ITreeDao<PKUID, EntityType>
{

	public void setLevelCode(EntityType entity)
	{
		PKUID pkuid = (PKUID) super.save(entity);
		if (entity.getParent() == null)
		{
			entity.setLevelCode(pkuid.toString());
			super.update(entity);
		} else
		{
			EntityType parentEntity = super.get(entity.getParent().getPkuid());
			if (parentEntity == null)
				throw new KmException("The parent does not exist! It's null.");
			else
			{
				entity.setLevelCode(parentEntity.getLevelCode() + "," + pkuid.toString());
				super.update(entity);
			}
		}
	}

	@Override
	public EntityType getMaxLevelCodeExceCurrentEntity(PKUID parent_pkuid, PKUID current_pkuid)
	{
		Criteria criteria = super.getCriteria().add(Restrictions.ne("pkuid", current_pkuid));
		if (parent_pkuid == null)
			criteria.add(Restrictions.isNull("parent"));
		else
			criteria.createCriteria("parent").add(Restrictions.eq("pkuid", parent_pkuid));
		criteria.setMaxResults(1);

		@SuppressWarnings("unchecked")
		List<EntityType> result = criteria.list();
		if (result != null && result.size() > 0)
			return result.get(0);
		return null;
	}

	@Override
	public void updateLevelCode(EntityType entity)
	{
		if (entity.getParent() == null)
		{
			entity.setLevelCode(entity.getPkuid().toString());
		} else
		{
			entity.setLevelCode(entity.getParent().getLevelCode() + "," + entity.getPkuid().toString());
		}

		super.update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> listByParentPKUID(PKUID parent_pkuid)
	{
		if (parent_pkuid == null)
		{
			return super.getCriteria().add(Restrictions.isNull("parent")).list();
		} else
		{
			return super.getCriteria().createCriteria("parent").add(Restrictions.eq("pkuid", parent_pkuid)).list();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> listByParentPKUIDExceCurrent(PKUID parent_pkuid, PKUID current_pkuid)
	{
		if (parent_pkuid == null)
		{
			return super.getCriteria().add(Restrictions.ne("pkuid", current_pkuid)).add(Restrictions.isNull("parent"))
					.list();
		} else
		{
			return super.getCriteria().add(Restrictions.ne("pkuid", current_pkuid)).createCriteria("parent")
					.add(Restrictions.eq("pkuid", parent_pkuid)).list();
		}
	}

	@Override
	public PKUID save(EntityType entity)
	{
		super.checkNull(entity);
		if (entity.getParent() != null)
		{
			entity.setParent(super.get(entity.getParent().getPkuid()));
		}
		entity.setLevelCode("");
		PKUID pkuid = super.save(entity);
		this.updateLevelCode(super.get(pkuid));
		return pkuid;
	}

	@Override
	public void update(EntityType entity)
	{
		super.checkNull(entity);
		if (entity.getParent() != null)
		{
			entity.setParent(super.get(entity.getParent().getPkuid()));
		}
		super.update(entity);
		this.updateLevelCode(entity);
	}

	@Override
	public void delete(EntityType entity)
	{
		super.checkNull(entity);
		if (entity.getChildren() != null || !entity.getChildren().isEmpty())
		{
			throw new KmException("cannot delete the entity when  has childrens");
		} else
		{
			super.delete(entity);
		}
	}

}
