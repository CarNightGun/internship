package com.km.test.singleT;

import com.km.util.CommonUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月29日下午8:38:34
 */
public class Mytest1
{

	public static void main(String[] args)
	{
		Double d = 2.001;
		String result = String.format("%.2f",d);
		System.out.println(CommonUtil.formatDouble(d));
	}

}
