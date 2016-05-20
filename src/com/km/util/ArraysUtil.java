package com.km.util;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日下午3:11:11
 */
public class ArraysUtil
{
	/**
	 * 将list转化为数组 根据list中对象的属性名称
	 * 
	 * @author tcn 空幕 2016年5月20日下午6:48:18
	 * @param list
	 * @param propertyName
	 * @return propertyType[]
	 */
	public static Object[] listToArrayByField(List<?> list, String propertyName)
	{
		if (list == null || list.isEmpty())
		{
			return null;
		}

		String firstLetter = propertyName.substring(0, 1).toUpperCase();
		String getterMethodName = "get" + firstLetter + propertyName.substring(1);
		return listToArrayByGetMethod(list, getterMethodName);
	}

	/**
	 * 将list转化为数组 根据list中对象的getter
	 * 
	 * @author tcn 空幕 2016年5月20日下午6:50:12
	 * @param list
	 * @param methodName
	 * @return getterType[]
	 */
	public static Object[] listToArrayByGetMethod(List<?> list, String methodName)
	{
		if (list == null || list.isEmpty())
		{
			return null;
		}

		try
		{
			Object array = null;
			int index = 0;
			for (Object lt : list)
			{
				Method method = lt.getClass().getMethod(methodName, new Class[] {});
				Object value = method.invoke(lt);
				if (array == null)
				{
					Class<?> classType = Class.forName(value.getClass().getTypeName());
					array = Array.newInstance(classType, list.size());
				}
				Array.set(array, index++, value);
			}
			return (Object[]) array;
		} catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 根据column 过滤
	 * 
	 * @author tcn 空幕 2016年5月20日下午7:10:17
	 * @param list
	 * @param columnName
	 * @return
	 */
	public static List<?> filterListByColumn(List<?> list, String columnName)
	{
		if (list == null || list.isEmpty())
		{
			return null;
		}

		String firstLetter = columnName.substring(0, 1).toUpperCase();
		String getterMethodName = "get" + firstLetter + columnName.substring(1);

		return filterListByColumnGetter(list, getterMethodName);
	}

	/**
	 * 根据columnGetter 过滤
	 * 
	 * @author tcn 空幕 2016年5月20日下午7:10:44
	 * @param list
	 * @param columnGetterName
	 * @return
	 */
	public static List<?> filterListByColumnGetter(List<?> list, String columnGetterName)
	{
		if (list == null || list.isEmpty())
		{
			return null;
		}

		try
		{
			Object array = null;
			Method add = null;
			for (Object lt : list)
			{
				Method method = lt.getClass().getMethod(columnGetterName, new Class[] {});
				Object value = method.invoke(lt);
				if (array == null)
				{
					Class<?> classType = list.getClass();
					array = classType.newInstance();
					add = array.getClass().getMethod("add", Object.class);
				}
				add.invoke(array, value);
			}
			return (List<?>) array;
		} catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 去掉Array中的某一项
	 * 
	 * @author tcn 空幕 2016年5月20日下午6:46:31
	 * @param array
	 * @param item
	 * @return
	 */
	public final static Long[] removeArrayItem(Long[] array, Long item)
	{
		List<Long> list = new ArrayList<Long>();
		for (Long arrayItem : array)
		{
			if (!arrayItem.equals(item))
			{
				list.add(arrayItem);
			}
		}
		Long[] ret = new Long[list.size()];
		return list.toArray(ret);
	}

	/**
	 * 已split分隔数组
	 * 
	 * @author tcn 空幕 2016年5月20日下午6:47:33
	 * @param array
	 * @param split
	 * @return
	 */
	public final static String toString(Long[] array, String split)
	{
		StringBuilder sb = new StringBuilder();
		for (Long item : array)
		{
			sb.append(item.toString());
			sb.append(split);
		}
		String str = sb.toString();
		if (str.endsWith(split))
			str = str.substring(0, str.length() - split.length());
		return str;
	}

}
