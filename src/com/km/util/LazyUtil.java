package com.km.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.proxy.HibernateProxy;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月25日下午10:03:04
 */
public class LazyUtil
{

	//加载代理类实体
	public static void initializeEntity(Object entity)
	{
		if (entity instanceof HibernateProxy) {
			if (org.hibernate.Hibernate.isInitialized(entity) == false) {
				Hibernate.initialize(entity);
			}
		} else if (entity instanceof PersistentBag) {
			if (((PersistentBag) entity).wasInitialized() == false) {
				Hibernate.initialize(entity);
			}
		}
	}
	
	
	public static void initEntity(Object entity){
		if(entity == null){
			return;
		}
		
		try
		{
//			Introspector.flushCaches();
//			Introspector.flushFromCaches(entity.getClass());
			BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass(),Object.class);
			PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
			
			for (PropertyDescriptor pd : props)
			{
				if(pd.getReadMethod() == null || "hibernateLazyInitializer".equals(pd.getName())){ //hibernateLazyInitializer Hibernate 属性
					continue;
				}
				initializeEntity(pd.getReadMethod().invoke(entity, new Object[]{}));
			}
			
			
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		
	}

	
	
}
