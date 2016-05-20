package com.km.web.extra;

import java.util.ArrayList;
import java.util.List;

import com.km.common.bean.AbstractTreeEntity;
import com.km.web.model.TreeModel;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午10:07:35
 */
public class TreeModelExtra
{
	public static <PKUID extends Number, EntityType extends AbstractTreeEntity<PKUID, EntityType>> List<TreeModel> ToTreeModels(
			List<EntityType> entities, PKUID selectedId, List<PKUID> checkedIdList,
			List<PKUID> expandedIdList)
	{

		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		for (EntityType entity : entities)
		{
			boolean checked = false;
			boolean selected = false;
			boolean collpase = true;
			List<TreeModel> children = null;

			if (selectedId != null && selectedId.equals(entity.getPkuid()))
			{
				selected = true;
			}
			if (checkedIdList != null && !checkedIdList.isEmpty())
			{
				checked = checkedIdList.contains(entity.getPkuid());
			}
			if (expandedIdList != null && !expandedIdList.isEmpty())
			{
				collpase = !expandedIdList.contains(entity.getPkuid());
			}
			if (entity.getChildren() != null && !entity.getChildren().isEmpty())
			{
				children = ToTreeModels(entity.getChildren(), selectedId, checkedIdList,
						expandedIdList);
			}

			treeModels.add(new TreeModel(entity.getPkuid().toString(),
					entity.getPkuid().toString(), entity.getName().toString(), checked, selected,
					collpase, children));
		}
		return treeModels;
	}

}
