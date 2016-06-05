package com.km.dao.impl;

import org.springframework.stereotype.Repository;

import com.km.bean.CostCategory;
import com.km.common.dao.impl.TreeDaoImpl;
import com.km.dao.ICostCategoryDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月5日下午2:17:50
 */
@Repository
public class CostCategoryDaoImpl extends TreeDaoImpl<Long, CostCategory> implements ICostCategoryDao
{

}
