package com.km.util;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月29日下午8:41:46
 */
public class CommonUtil
{
		
	public static Double formatDouble(Double args){
		if(args == null){
			return 0d;
		}
		return Double.parseDouble(String.format("%.2f", args));
	}
}
