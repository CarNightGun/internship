package com.km.web.extra;

import com.km.bean.Organization;
import com.km.web.model.OrganizationEditModel;


/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午10:54:38
 */
public class OrganizationModelExtra
{

	public static Organization toOrganization(OrganizationEditModel editModel)
	{
		Organization result =new Organization();
		result.setPkuid(editModel.getId());
		result.setName(editModel.getName());
 
		result.setSorting(editModel.getSorting());
		result.setOrgCode(editModel.getOrgCode());
		result.setAudit(true);
		
		if(editModel.getParentId()!=null && editModel.getParentId()>0){
			Organization parent=new Organization();
			parent.setPkuid(editModel.getParentId());
			result.setParent(parent);
		}
		
		return result;
	}

	public static OrganizationEditModel toOrganizationEditModel(Organization organization)
	{
		OrganizationEditModel result=new OrganizationEditModel();
		result.setId(organization.getPkuid());
		result.setName(organization.getName());
		result.setSorting(organization.getSorting());
		result.setOrgCode(organization.getOrgCode());
		
		if(organization.getParent()!=null)
			result.setParentId(organization.getParent().getPkuid());
		
		return result;
	}

}
