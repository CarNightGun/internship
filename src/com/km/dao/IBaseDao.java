package com.km.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Dao封装接口
 * @author tcn 空幕  email:1623092203@qq.com time:2016年4月28日下午1:41:26
 */
public interface IBaseDao<T,ID extends Serializable>
{
	
	/**
	 * 保存实体
	 * @author tcn 空幕  2016年4月28日下午1:44:54
	 * @param entity 要保存的实体对象
	 * @return 保存成功的id
	 */
	public ID save(T entity);
	
	/**
	 * 保存或者更新实体
	 * @author tcn 空幕  2016年4月28日下午1:48:07
	 * @param entity 要保存或更新的实体对象
	 */
	public void saveOrUpdate(T entity);
	
	/**
	 * load实体
	 * @author tcn 空幕  2016年4月28日下午1:49:08
	 * @param id 要加载的实体对象id
	 * @return 实体对象
	 */
	public T load(ID id);
	
	/**
	 * get实体
	 * @author tcn 空幕  2016年4月28日下午1:50:02
	 * @param id 要加载的实体对象id
	 * @return 实体对象
	 */
	public T get(ID id);
	
	/**
	 * 判断是否包含实体
	 * @author tcn 空幕  2016年4月28日下午1:51:16
	 * @param entity 要判断的实体
	 * @return true包含，false不包含
	 */
	public boolean contains(T entity);
	
	/**
	 * 删除某实体
	 * @author tcn 空幕  2016年4月28日下午1:54:14
	 * @param entity 要删除的实体
	 */
	public void delete(T entity);
	
	/**
	 * 根据ID删除实体
	 * @author tcn 空幕  2016年4月28日下午1:55:14
	 * @param id 实体id
	 * @return true删除成功，false没有删除成功
	 */
	public boolean deleteById(ID id);
	
	/**
	 * 删除传入集合中实体
	 * @author tcn 空幕  2016年4月28日下午1:58:51
	 * @param entities 要删除的实体的collection集合
	 */
	public void deleteAll(Collection<T> entities);
	
	/**
	 * 执行hql语句
	 * @author tcn 空幕  2016年4月28日下午2:04:22
	 * @param hqlString 要执行的hql
	 * @param values 不定长参数数组
	 */
	public void queryHql(String hqlString,Object... values);
	
	/**
	 * 执行sql语句
	 * @author tcn 空幕  2016年4月28日下午2:05:56
	 * @param sqlString 要执行的sql
	 * @param values 不定长参数数组
	 */
	public void querySql(String sqlString,Object... values);
	
	/**
	 * 根据hql查询唯一实体
	 * @author tcn 空幕  2016年4月28日下午2:11:21
	 * @param hqlString hql语句
	 * @param values 不定长参数数组
	 * @return 要查询的实体
	 */
	public T getByHql(String hqlString, Object... values);
	
	/**
	 * 根据sql查询唯一实体
	 * @author tcn 空幕  2016年4月28日下午2:11:24
	 * @param sqlString sql语句
	 * @param values 不定长参数数组
	 * @return 要查询的实体
	 */
	public T getBySql(String sqlString, Object... values);
	
	/**
	 * 根据hql查询对于的List实体集合
	 * @author tcn 空幕  2016年4月28日下午2:16:44
	 * @param hqlString 要执行的hql
	 * @param values 不定长参数数组
	 * @return List实体集合
	 */
	public List<T> getListByHql(String hqlString, Object... values);
	
	/**
	 * 根据sql查询对于的List实体集合
	 * @author tcn 空幕  2016年4月28日下午2:16:49
	 * @param sqlString 要执行的sql
	 * @param values 不定长参数数组
	 * @return List实体集合
	 */
	public List<T> getListBySql(String sqlString, Object... values);
	
	/**
	 * refresh
	 * @author tcn 空幕  2016年4月28日下午2:22:02
	 * @param entity
	 */
	public void refresh(T entity);
	
	/**
	 * 更新实体
	 * @author tcn 空幕  2016年4月28日下午2:22:53
	 * @param entity 要更新的实体
	 */
	public void update(T entity);
	
	/**
	 * 根据hql得到记录数
	 * @author tcn 空幕  2016年4月28日下午2:24:06
	 * @param whereHql 要执行的以where开头的hql
	 * @param values 不定长参数数组
	 * @return Long 记录数
	 */
	public Long countByHql(String whereHql,Object... values);
	
	/**
	 * hql分页查询
	 * @author tcn 空幕  2016年4月28日下午2:29:01
	 * @param hqlString hql语句
	 * @param countHql 查询记录条数的hql语句
	 * @param pageNo 要查询第pageNo的记录
	 * @param pageSize 一页总数
	 * @param values 不定长参数数组
	 * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
	 */
	public List<T> findPageByFetchHql(int pageNo, int pageSize,
			String hqlString, Object... values);
	
	
	
	
	
	
	
	
	
}
