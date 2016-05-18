package com.km.common.dao;

import java.util.List;

import com.km.common.bean.AbstractTreeEntity;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月17日下午3:58:45
 */
public interface ITreeDao<PKUID extends Number, EntityType extends AbstractTreeEntity<PKUID, EntityType>> extends
		IBaseDao<PKUID, EntityType>
{

	/**
	 * 设置级次代码
	 * 
	 * @author tcn 空幕 2016年5月17日下午4:03:46
	 * @param entity
	 */
	public void setLevelCode(EntityType entity);
	
	/**
	 * current 获取当前实体所在的层次最大的levelcode的实体 or parent 获取当前实体父结点所在的层次最大的levelcode的实体
	 * @author tcn 空幕  2016年5月17日下午4:48:34
	 * @param parent_pkuid  父结点pkuid
	 * @param current_pkuid 当前节点pkuid
	 * @return 
	 */
	public EntityType getMaxLevelCodeExceCurrentEntity(PKUID parent_pkuid, PKUID current_pkuid);
	
	
	public void updateLevelCode(EntityType entity);
	
	
	public List<EntityType> listByParentPKUID(PKUID parent_pkuid);
	
	
	public List<EntityType> listByParentPKUIDExceCurrent(PKUID parent_pkuid, PKUID current_pkuid);

}
