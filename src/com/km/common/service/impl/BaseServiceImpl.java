package com.km.common.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.km.common.bean.AbstractBaseEntity;
import com.km.common.dao.IBaseDao;
import com.km.common.service.IBaseService;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月18日上午10:19:16
 */
public abstract class BaseServiceImpl<PKUID extends Number,EntityType extends AbstractBaseEntity<PKUID>,DaoType extends IBaseDao<PKUID, EntityType>> implements IBaseService<PKUID, EntityType , DaoType>
{
	protected DaoType baseDao;
//	protected final Class<EntityType> entityClass;
	
	public BaseServiceImpl(DaoType baseDao)
	{
//		Type type = getClass().getGenericSuperclass();
//		if(!(type instanceof ParameterizedType)){
//			type = getClass().getSuperclass().getGenericSuperclass();
//		}
//		this.entityClass = (Class<EntityType>)((ParameterizedType)type).getActualTypeArguments()[1];
		this.baseDao = baseDao;
	}
	
	@Override
	public PKUID save(EntityType entity)
	{
		return this.baseDao.save(entity);
	}

	@Override
	public EntityType load(PKUID pkuid)
	{
		return this.baseDao.load(pkuid);
	}

	@Override
	public EntityType get(PKUID pkuid)
	{
		return this.baseDao.get(pkuid);
	}

	@Override
	public boolean contains(EntityType entity)
	{
		return this.baseDao.contains(entity);
	}

	@Override
	public void delete(EntityType entity)
	{
		this.baseDao.delete(entity);
	}

	@Override
	public boolean deleteById(PKUID pkuid)
	{
		return this.baseDao.deleteById(pkuid);
	}

	@Override
	public void deleteAll(Collection<EntityType> entities)
	{
		this.baseDao.deleteAll(entities);	
	}

	@Override
	public void update(EntityType entity)
	{
		this.baseDao.update(entity);
	}

	@Override
	public boolean exists(PKUID pkuid)
	{
		return this.baseDao.exists(pkuid);
	}

	@Override
	public int countAll()
	{
		return this.baseDao.countAll();
	}

	@Override
	public void saveOrUpdate(EntityType entity)
	{
		this.baseDao.saveOrUpdate(entity);
		
	}

	@Override
	public void merge(EntityType entity)
	{
		this.baseDao.merge(entity);
	}

	@Override
	public List<EntityType> listAll()
	{
		return this.baseDao.listAll();
	}

	@Override
	public IPageList<EntityType> listPage(int pageNo)
	{
		return this.listPage(pageNo, PageUtil.DEFAULT_PAGE_SIZE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<EntityType> listPage(int pageNo, int pageSize)
	{
		Integer itemsCount = countAll();
	    List<EntityType> items = this.baseDao.listPage((pageNo-1)*pageSize, pageSize);
	    return PageUtil.getPageList(pageSize, pageNo, itemsCount, items);
	}

	@Override
	public List<EntityType> listAudit()
	{
		return this.baseDao.listAudit();
	}

	@Override
	public List<EntityType> listUnAudit()
	{
		return this.baseDao.listUnAudit();
	}

	@Override
	public void audit(EntityType entity)
	{
		this.baseDao.audit(entity);
	}

	@Override
	public void unAudit(EntityType entity)
	{
		this.baseDao.unAudit(entity);
		
	}

	@Override
	public Map<PKUID, String> getAuditSelectSource()
	{
		Map<PKUID, String> map=new HashMap<PKUID, String>();
		List<EntityType> entities=this.listAudit();
		for(EntityType entity : entities){
			map.put(entity.getPkuid(), entity.getName());
		}
		return map;
	}

	@Override
	public Map<PKUID, String> getSelectSource()
	{
		Map<PKUID, String> map=new HashMap<PKUID, String>();
		List<EntityType> entities=this.listAll();
		for(EntityType entity : entities){
			map.put(entity.getPkuid(), entity.getName());
		}
		return map;
	}

}
