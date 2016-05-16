package dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import dao.IBaseDao;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年4月28日下午3:17:44
 */
@Repository
public class BaseDaoImpl<T, ID extends Serializable> implements IBaseDao<T, ID>
{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;

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
	protected Class<T> getEntityClass()
	{
		if (entityClass == null)
		{
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Type[] type = pt.getActualTypeArguments();
			entityClass = (Class<T>) type[0];
		}
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ID save(T entity)
	{
		return (ID) this.currentSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(ID id)
	{
		return (T) this.currentSession().load(getEntityClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(ID id)
	{
		return (T) this.currentSession().get(getEntityClass(), id);
	}

	@Override
	public void saveOrUpdate(T entity)
	{
		this.currentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity)
	{
		this.currentSession().delete(entity);
	}

	@Override
	public boolean deleteById(ID id)
	{
		T entity = this.get(id);
		if (entity == null)
		{
			return false;
		}
		this.delete(entity);
		if (this.get(id) == null)
		{
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll(Collection<T> entities)
	{
		for (T entity : entities)
		{
			this.currentSession().delete(entity);
		}
	}

	@Override
	public void update(T entity)
	{
		this.currentSession().update(entity);
	}

	@Override
	public void refresh(T entity)
	{
		this.currentSession().refresh(entity);
	}

	@Override
	public boolean contains(T entity)
	{
		return this.currentSession().contains(entity);
	}

	@Override
	public Long countByHql(String whereHql, Object... values)
	{
		String hql = "select count(*) from " + getEntityClass().getSimpleName()
				+ whereHql;
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
	public T getByHql(String hqlString, Object... values)
	{
		Query query = this.currentSession().createQuery(hqlString);
		this.setParameters(query, values);
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getBySql(String sqlString, Object... values)
	{
		Query query = this.currentSession().createSQLQuery(sqlString);
		this.setParameters(query, values);
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByHql(String hqlString, Object... values)
	{
		Query query = this.currentSession().createQuery(hqlString);
		this.setParameters(query, values);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListBySql(String sqlString, Object... values)
	{
		Query query = this.currentSession().createSQLQuery(sqlString);
		this.setParameters(query, values);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findPageByFetchHql(int pageNo, int pageSize,
			String hqlString, Object... values)
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
}
