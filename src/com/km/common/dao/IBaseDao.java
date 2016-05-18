package com.km.common.dao;


import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

import com.km.common.bean.AbstractBaseEntity;

/**
 * Dao封装接口
 * @author tcn 空幕  email:1623092203@qq.com time:2016年4月28日下午1:41:26
 */
public interface IBaseDao<PKUID extends Number,EntityType extends AbstractBaseEntity<PKUID>>
{
	
	/**
	 * 保存实体
	 * @author tcn 空幕  2016年4月28日下午1:44:54
	 * @param entity 要保存的实体对象
	 * @return 保存成功的id
	 */
	public PKUID save(EntityType entity);
	
	
	/**
	 * load实体
	 * @author tcn 空幕  2016年4月28日下午1:49:08
	 * @param pkuid 要加载的实体对象id
	 * @return 实体对象
	 */
	public EntityType load(PKUID pkuid);
	
	/**
	 * get实体
	 * @author tcn 空幕  2016年4月28日下午1:50:02
	 * @param pkuid 要加载的实体对象id
	 * @return 实体对象
	 */
	public EntityType get(PKUID pkuid);
	
	/**
	 * 判断是否包含实体
	 * @author tcn 空幕  2016年4月28日下午1:51:16
	 * @param entity 要判断的实体
	 * @return true包含，false不包含
	 */
	public boolean contains(EntityType entity);
	
	/**
	 * 删除某实体
	 * @author tcn 空幕  2016年4月28日下午1:54:14
	 * @param entity 要删除的实体
	 */
	public void delete(EntityType entity);
	
	/**
	 * 根据ID删除实体
	 * @author tcn 空幕  2016年4月28日下午1:55:14
	 * @param pkuid 实体id
	 * @return true删除成功，false没有删除成功
	 */
	public boolean deleteById(PKUID pkuid);
	
	/**
	 * 删除传入集合中实体
	 * @author tcn 空幕  2016年4月28日下午1:58:51
	 * @param entities 要删除的实体的collection集合
	 */
	public void deleteAll(Collection<EntityType> entities);
	
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
	public EntityType getByHql(String hqlString, Object... values);
	
	/**
	 * 根据sql查询唯一实体
	 * @author tcn 空幕  2016年4月28日下午2:11:24
	 * @param sqlString sql语句
	 * @param values 不定长参数数组
	 * @return 要查询的实体
	 */
	public EntityType getBySql(String sqlString, Object... values);
	
	/**
	 * 根据hql查询对于的List实体集合
	 * @author tcn 空幕  2016年4月28日下午2:16:44
	 * @param hqlString 要执行的hql
	 * @param values 不定长参数数组
	 * @return List实体集合
	 */
	public List<EntityType> getListByHql(String hqlString, Object... values);
	
	/**
	 * 根据sql查询对于的List实体集合
	 * @author tcn 空幕  2016年4月28日下午2:16:49
	 * @param sqlString 要执行的sql
	 * @param values 不定长参数数组
	 * @return List实体集合
	 */
	public List<EntityType> getListBySql(String sqlString, Object... values);
	
	/**
	 * refresh
	 * @author tcn 空幕  2016年4月28日下午2:22:02
	 * @param entity
	 */
	public void refresh(EntityType entity);
	
	/**
	 * 更新实体
	 * @author tcn 空幕  2016年4月28日下午2:22:53
	 * @param entity 要更新的实体
	 */
	public void update(EntityType entity);
	
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
	public List<EntityType> findPageByFetchHql(int pageNo, int pageSize,
			String hqlString, Object... values);
	
	
	/**
	 * 检查对应的id的对象是否存在
	 * @author tcn 空幕  2016年5月17日下午2:47:23
	 * @param pkuid
	 * @return boolean true存在 ,false 不存在
	 */
	public boolean exists(PKUID pkuid);
    
	/**
	 * 得到所有记录的个数
	 * @author tcn 空幕  2016年5月17日下午2:43:00
	 * @return int 记录数
	 */
    public int countAll();
	
    
    /**
     * 保存或更新对象
     * @author tcn 空幕  2016年5月17日下午2:48:13
     * @param entity
     */
    public void saveOrUpdate(EntityType entity);
    
    /**
     * 先查询是否有对应记录，再根据查询结果保存或更新对象
     * @author tcn 空幕  2016年5月17日下午2:48:29
     * @param entity
     */
    public void merge(EntityType entity);
    
    /**
     * 获取Criteria
     * @author tcn 空幕  2016年5月17日下午2:48:38
     * @return Criteria
     */
    public Criteria getCriteria();
    
    /**
     * 查询所有对象
     * @author tcn 空幕  2016年5月17日下午2:48:56
     * @return 对象list
     */
    public List<EntityType> listAll();

    /**
     * 查询所有对象并排序
     * @author tcn 空幕  2016年5月17日下午2:50:32
     * @param orderName 排序字段名
     * @param orderASC 排序方式   true->asc,false->desc
     * @return 排序后的对象list
     */
    public List<EntityType> listAll(String orderName, boolean orderASC);
    
   /**
    * 分页查询对象
    * @author tcn 空幕  2016年5月17日下午2:51:29
    * @param start 开始的下标
    * @param limit 此次对象list length的最大值
    * @return 分页后的对象list
    */
    public List<EntityType> listPage(int start, int limit);
    
    /**
     * 分页查询对象并排序
     * @author tcn 空幕  2016年5月17日下午2:53:35
     * @param start 开始的下标
     * @param limit 此次对象list length的最大值
     * @param orderName  排序字段名
     * @param orderASC 排序方式  true->asc,false->desc
     * @return 分页后的对象list
     */
    public List<EntityType> listPage(int start, int limit, String orderName, boolean orderASC);
    
    /**
     * 清除缓存中对应的对象，使对象变为托管态
     * @author tcn 空幕  2016年5月17日下午2:42:35
     * @param entity
     */
    public void evict(EntityType entity);
    
    /**
     * 强制缓存中所有对象与数据库数据同步
     * @author tcn 空幕  2016年5月17日下午2:42:24
     */
    public void flush();
    
   /**
    * 强制刷新一级缓存中所有的对象
    * @author tcn 空幕  2016年5月17日下午2:42:12
    */
    public void clear();
	
	/**
	 * 查找已审核数据
	 * @author tcn 空幕  2016年5月17日下午3:45:11
	 * @return
	 */
    public List<EntityType> listAudit();
    
    /**
     * 查找未审核数据
     * @author tcn 空幕  2016年5月17日下午3:45:44
     * @return
     */	
    public List<EntityType> listUnAudit();
    
    /**
     * 审核数据
     * @author tcn 空幕  2016年5月17日下午3:47:33
     * @param entity 要操作的实体
     */
    public void aduit(EntityType entity) ;
    
    /**
     * 取消审核数据
     * @author tcn 空幕  2016年5月17日下午3:47:54
     * @param entity 要操作的实体
     */
	public void unAudit(EntityType entity);
	
}
