package com.km.web.extra;

import com.km.bean.Authority;
import com.km.web.model.AuthorityEditModel;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月26日下午10:34:55
 */
public class AuthorityModelExtra
{
	public static Authority toAuthority(AuthorityEditModel editModel){
		Authority result =new Authority();
		result.setPkuid(editModel.getId());
		result.setName(editModel.getName());
		result.setSorting(editModel.getSorting());
		result.setUrl(editModel.getUrl());
		result.setMatchUrl(editModel.getMatchUrl());
		result.setItemIcon(editModel.getItemIcon());
		
		if(editModel.getParentId()!=null && editModel.getParentId()!=null && editModel.getParentId()>0){
			Authority parent=new Authority();
			parent.setPkuid(editModel.getParentId());
			result.setParent(parent);
		}
		
		return result;
	}
	
	public static AuthorityEditModel toAuthorityEditModel(Authority model){
		AuthorityEditModel result=new AuthorityEditModel();
		result.setId(model.getPkuid());
		result.setName(model.getName());
		result.setSorting(model.getSorting());
		result.setUrl(model.getUrl());
		result.setMatchUrl(model.getMatchUrl());
		result.setItemIcon(model.getItemIcon());
		
		if(model.getParent()!=null)
			result.setParentId(model.getParent().getPkuid());
		
		return result;
	}
}
