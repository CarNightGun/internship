package com.km.util.page;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月18日上午9:55:45
 */
public class PageUtil
{

	/**
	 * 默认起始页
	 */
	public static final int DEFAULT_PAGE_NO = 1;

	/**
	 * 默认页面大小
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final String NAME_PAGE_NO = "pageNo";

	public static final String NAME_PAGE_SIZE = "pageSize";

	/**
	 * 根据页码和页面大小得到开始下标,不考虑总数
	 * 
	 * @author tcn 空幕 2016年5月18日上午10:00:10
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static int getPageListStart(int pageNo, int pageSize)
	{
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 根据页码和页面大小得到开始下标，考虑总数
	 * 
	 * @author tcn 空幕 2016年5月18日上午10:01:53
	 * @param totalCount
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static int getPageListStart(int totalCount, int pageNo, int pageSize)
	{
		int start = (pageNo - 1) * pageSize;
		if (start >= totalCount)
			start = 0;
		return start;
	}

	public static String resolveUrl(String url, String queryString, Integer pageNo, Integer pageSize)
	{
		if (queryString == null)
		{
			queryString = "";
		} else
		{
			queryString = queryString.replaceAll("&pageNo=\\d*", "").replaceAll("pageNo=\\d*", "")
					.replaceAll("&pageSize=\\d*", "").replaceAll("pageSize=\\d*", "");
		}

		if (pageNo != null)
		{
			queryString = queryString.isEmpty() ? "pageNo=" + pageNo.toString() : queryString
					+ "&pageNo=" + pageNo.toString();
		}
		if (pageSize != null)
		{
			queryString = queryString.isEmpty() ? "pageSize=" + pageSize.toString() : queryString
					+ "&pageSize=" + pageSize.toString();
		}

		return queryString.isEmpty() ? url : url + "?" + queryString;
	}
}
