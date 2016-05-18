package com.km.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;


import com.km.common.bean.AbstractBaseEntity;
import com.km.common.dao.IBaseDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月18日上午8:49:55
 */
public interface IBaseService<PKUID extends Number, EntityType extends AbstractBaseEntity<PKUID>, DaoType extends IBaseDao<PKUID, EntityType>>
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
	 * 更新实体
	 * @author tcn 空幕  2016年4月28日下午2:22:53
	 * @param entity 要更新的实体
	 */
	public void update(EntityType entity);
	

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
     * 查询所有对象
     * @author tcn 空幕  2016年5月17日下午2:48:56
     * @return 对象list
     */
    public List<EntityType> listAll();

    
    /**
     * 分页查询对象
     * @author tcn 空幕  2016年5月18日上午9:16:40
     * @param pageNo 起始页
     * @return 分页
     */
     public IPageList<EntityType>  listPage(int pageNo);
    
   /**
    * 分页查询对象
    * @author tcn 空幕  2016年5月18日上午9:16:40
    * @param pageNo 起始页
    * @param pageSize 页面大小
    * @return 分页
    */
    public IPageList<EntityType>  listPage(int pageNo, int pageSize);
    
	
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
	
	
	/**
	 * 获得已审核的数据 ID与名称
	 * @author tcn 空幕  2016年5月18日上午10:13:18
	 * @return
	 */
	public Map<PKUID, String> getAuditSelectSource();
	
	/**
	 * 获得数据 ID与名称
	 * @author tcn 空幕  2016年5月18日上午10:15:31
	 * @return
	 */
	public Map<PKUID, String>  getSelectSource();
	
}
