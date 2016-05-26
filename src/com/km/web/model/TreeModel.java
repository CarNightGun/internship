package com.km.web.model;

import java.util.List;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日上午9:02:30
 */
public class TreeModel
{
	private String id;
	private String value;
	private String text;
	private boolean checked;
	private boolean selected;
	private boolean collpase;
	private List<TreeModel> children;

	public TreeModel()
	{

	}

	public TreeModel(String id, String value, String text, boolean checked, boolean selected,
			boolean collpase, List<TreeModel> children)
	{
		this.id = id;
		this.value = value;
		this.text = text;
		this.checked = checked;
		this.selected = selected;
		this.collpase = collpase;
		this.children = children;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	public boolean isCollpase()
	{
		return collpase;
	}

	public void setCollpase(boolean collpase)
	{
		this.collpase = collpase;
	}

	public List<TreeModel> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeModel> children)
	{
		this.children = children;
	}

	@Override
	public String toString()
	{
		return "TreeModel [id=" + id + ", value=" + value + ", text=" + text + ", checked="
				+ checked + ", selected=" + selected + ", collpase=" + collpase + ", children="
				+ children + "]";
	}

	
}
