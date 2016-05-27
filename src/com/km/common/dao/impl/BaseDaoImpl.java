package com.km.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.km.common.bean.AbstractBaseEntity;
import com.km.common.dao.IBaseDao;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年4月28日下午3:17:44
 */
@Repository
public abstract class BaseDaoImpl<PKUID extends Number, EntityType extends AbstractBaseEntity<PKUID>> implements
		IBaseDao<PKUID, EntityType>
{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	protected final Class<EntityType> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl()
	{
		Type type = getClass().getGenericSuperclass();
		if(!(type instanceof ParameterizedType)){
			type = getClass().getSuperclass().getGenericSuperclass();
		}
			this.entityClass = (Class<EntityType>)((ParameterizedType)type).getActualTypeArguments()[1];			
	}

	/**
	 * 获得session
	 * 
	 * @author tcn 空幕 2016年4月28日下午3:36:28
	 * @return org.hibernate.Session
	 */
	public Session currentSession()
	{
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	protected Class<EntityType> getEntityClass()
	{
		if (this.entityClass == null)
		{
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Type[] type = pt.getActualTypeArguments();
			return (Class<EntityType>) type[0];
//			return (Class<EntityType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];			
		}
		return this.entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PKUID save(EntityType entity)
	{
		this.checkNull(entity);
		return (PKUID) this.currentSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityType load(PKUID pkuid)
	{
		return (EntityType) this.currentSession().load(this.entityClass, pkuid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityType get(PKUID pkuid)
	{
		return (EntityType) this.currentSession().get(this.entityClass, pkuid);
	}

	@Override
	public void saveOrUpdate(EntityType entity)
	{
		this.checkNull(entity);
		this.currentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(EntityType entity)
	{
		this.checkNull(entity);
		this.currentSession().delete(entity);
	}

	@Override
	public boolean deleteById(PKUID pkuid)
	{
		EntityType entity = this.get(pkuid);
		if (entity == null)
		{
			return false;
		}
		this.delete(entity);
		if (this.get(pkuid) == null)
		{
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll(Collection<EntityType> entities)
	{
		for (EntityType entity : entities)
		{
			this.delete(entity);
		}
	}

	@Override
	public void update(EntityType entity)
	{
		this.checkNull(entity);
		this.currentSession().update(entity);
	}

	@Override
	public void refresh(EntityType entity)
	{
		this.checkNull(entity);
		this.currentSession().refresh(entity);
	}

	@Override
	public boolean contains(EntityType entity)
	{
		this.checkNull(entity);
		return this.currentSession().contains(entity);
	}

	@Override
	public Long countByHql(String whereHql, Object... values)
	{
		String hql = "select count(*) from " + this.entityClass.getSimpleName() + whereHql;
		Query query = this.currentSession().createQuery(hql);
		this.setParameters(query, values);
		return (Long) query.uniqueResult();
	}

	@Override
	public void queryHql(String hqlString, Object... values)
	{
		Query query = this.currentSession().createQuery(hqlString);
		this.setParameters(query, values);
		query.executeUpdate();

	}

	@Override
	public void querySql(String sqlString, Object... values)
	{
		Query query = this.currentSession().createSQLQuery(sqlString);
		this.setParameters(query, values);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityType getByHql(String hqlString, Object... values)
	{
		Query query = this.currentSession().createQuery(hqlString);
		this.setParameters(query, values);
		return (EntityType) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityType getBySql(String sqlString, Object... values)
	{
		Query query = this.currentSession().createSQLQuery(sqlString);
		this.setParameters(query, values);
		return (EntityType) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> getListByHql(String hqlString, Object... values)
	{
		Query query = this.currentSession().createQuery(hqlString);
		this.setParameters(query, values);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> getListBySql(String sqlString, Object... values)
	{
		Query query = this.currentSession().createSQLQuery(sqlString);
		this.setParameters(query, values);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> findPageByFetchHql(int pageNo, int pageSize, String hqlString, Object... values)
	{
		Query query = this.currentSession().createQuery(hqlString);
		this.setParameters(query, values);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	/**
	 * 设置query参数
	 * 
	 * @author tcn 空幕 2016年4月28日下午5:20:13
	 * @param query
	 *            org.hibernate.Query
	 * @param values
	 *            不定长参数数组
	 */
	private void setParameters(Query query, Object... values)
	{
		if (values != null)
		{
			for (int i = 0, len = values.length; i < len; i++)
			{
				query.setParameter(i, values[i]);
			}
		}
	}

	@Override
	public boolean exists(PKUID pkuid)
	{
		return this.currentSession().get(this.entityClass, pkuid) != null;
	}

	@Override
	public int countAll()
	{
		Criteria criteria = getCriteria();
		criteria.setProjection(Projections.rowCount());
		return Integer.parseInt(criteria.uniqueResult().toString());
	}

	@Override
	public void merge(EntityType entity)
	{
		this.checkNull(entity);
		this.currentSession().merge(entity);

	}

	@Override
	public Criteria getCriteria()
	{
		return this.currentSession().createCriteria(this.entityClass);
	}

	@Override
	public List<EntityType> listAll()
	{
		return listAll(null, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> listAll(String orderName, boolean orderASC)
	{
		Criteria criteria = this.getCriteria();
		if (orderName != null)
		{
			if (orderASC)
				criteria.addOrder(Order.asc(orderName));
			else
				criteria.addOrder(Order.desc(orderName));
		}
		return criteria.list();
	}

	@Override
	public List<EntityType> listPage(int start, int limit)
	{
		return this.listPage(start, limit, null, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> listPage(int start, int limit, String orderName, boolean orderASC)
	{
		Criteria criteria = this.getCriteria();
		if (orderName != null)
		{
			if (orderASC)
				criteria.addOrder(Order.asc(orderName));
			else
				criteria.addOrder(Order.desc(orderName));
		}
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}

	@Override
	public void evict(EntityType entity)
	{
		this.checkNull(entity);
		this.currentSession().evict(entity);
	}

	@Override
	public void flush()
	{
		this.currentSession().flush();
	}

	@Override
	public void clear()
	{
		this.currentSession().clear();
	}

	protected void checkNull(EntityType entity)
	{
		if (entity == null)
			throw new NullPointerException("basedao operator: entity is null!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> listAudit()
	{
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("audit", true));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> listUnAudit()
	{
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("audit", false));
		return criteria.list();
	}

	@Override
	public void audit(EntityType entity)
	{
		this.checkNull(entity);
		entity.setAudit(true);
		this.update(entity);
	}

	@Override
	public void unAudit(EntityType entity)
	{
		this.checkNull(entity);
		entity.setAudit(false);
		this.update(entity);
	}
}
