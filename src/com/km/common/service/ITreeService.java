package com.km.common.service;

import java.util.List;

import com.km.common.bean.AbstractTreeEntity;
import com.km.common.dao.ITreeDao;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月18日上午8:50:25
 */
public interface ITreeService<PKUID extends Number, EntityType extends AbstractTreeEntity<PKUID, EntityType>, DaoType extends ITreeDao<PKUID, EntityType>>
		extends IBaseService<PKUID, EntityType, DaoType>
{
	/**
	 * 获得顶层树的列表
	 * @author tcn 空幕  2016年5月18日上午10:17:15
	 * @return list列表
	 */
	public List<EntityType> listTree();
}
