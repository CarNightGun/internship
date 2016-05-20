package com.km.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日上午9:17:25
 */
public class StringUtil
{

	public static List<Long> toIntegerList(String expanded, String split)
	{
		List<Long> ret = new ArrayList<Long>();
		if (expanded != null && !expanded.isEmpty())
		{
			String[] strArray = expanded.split(split);

			for (String item : strArray)
			{
				if (!item.isEmpty())
				{
					ret.add(Long.parseLong(item));
				}
			}
		}
		return ret;
	}

}
