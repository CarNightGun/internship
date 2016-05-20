package com.km.util.page;

import java.util.List;


/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月18日上午9:19:09
 */
public interface IPageList<EntityType>
{
	/**
	 * 获取页面大小
	 * @author tcn 空幕  2016年5月18日上午9:24:47
	 * @return int
	 */
	public int getPageSize();
	
	
	/**
	 * 获取总页数
	 * @author tcn 空幕  2016年5月18日上午9:25:04
	 * @return int
	 */
	public int getPageCount();
	
	/**
	 * 获取当前页码
	 * @author tcn 空幕  2016年5月18日上午9:25:26
	 * @return int
	 */
	public int getCurrentPageNo();
	
	
	/**
	 * 获得记录总条数
	 * @author tcn 空幕  2016年5月18日上午9:25:46
	 * @return int
	 */
	public int getItemsCount();
	
	/**
	 * 是否有上一页
	 * @author tcn 空幕  2016年5月18日上午9:27:47
	 * @return
	 */
	public boolean isHasPre();
	
	/**
	 * 是否有下一页
	 * @author tcn 空幕  2016年5月18日上午9:28:04
	 * @return
	 */
	public boolean isHasNext();
	
//	/**
//	 * 获取数据
//	 * @author tcn 空幕  2016年5月18日上午9:42:13
//	 * @return 页面数据集合
//	 */
//	public  List<EntityType> getItems();
}
