package com.km.util.page;

import java.util.List;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月18日上午9:32:48
 */
public  class  PageList<EntityType> implements IPageList<EntityType>
{
	private int pageSize;

	private int pageCount;

	private int currentPageNo;

	private int itemsCount;

	private boolean isHasPre;

	private boolean isHasNext;

	private List<EntityType> items;

	public PageList(int pageSize, int currentPageNo, int itemsCount, List<EntityType> items)
	{
		this.pageSize = pageSize;
		this.pageCount = (itemsCount % pageSize == 0) ? itemsCount / pageSize : itemsCount
				/ pageSize + 1;
		this.currentPageNo = currentPageNo > pageCount ? pageCount : currentPageNo;
		this.itemsCount = itemsCount;
		this.isHasPre = currentPageNo > 1;
		this.isHasNext = currentPageNo < this.pageCount;
		this.items = items;
	}

	@Override
	public int getPageSize()
	{
		return this.pageSize;
	}

	@Override
	public int getPageCount()
	{
		return this.pageCount;
	}

	@Override
	public int getCurrentPageNo()
	{
		return this.currentPageNo;
	}

	@Override
	public int getItemsCount()
	{
		return this.itemsCount;
	}

	@Override
	public boolean isHasPre()
	{
		return this.isHasPre;
	}

	@Override
	public boolean isHasNext()
	{
		return this.isHasNext;
	}
	

	public  List<EntityType> getItems()
	{
		return this.items;
	}

	@Override
	public String toString()
	{
		return "PageList [pageSize=" + pageSize + ", pageCount=" + pageCount + ", currentPageNo="
				+ currentPageNo + ", itemsCount=" + itemsCount + ", isHasPre=" + isHasPre
				+ ", isHasNext=" + isHasNext + ", items=" + items + "]";
	}
	

	

	
}
