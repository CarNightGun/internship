package com.km.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





import com.km.bean.Organization;
import com.km.common.controller.BaseController;
import com.km.util.SpringUtil;
import com.km.util.StringUtil;
import com.km.web.auth.AuthRight;
import com.km.web.extra.OrganizationModelExtra;
import com.km.web.extra.TreeModelExtra;
import com.km.web.model.OrganizationEditModel;
import com.km.web.model.TreeModel;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:49:40
 */
@Controller
@RequestMapping(value="/organization")
public class OrganizationCtrl extends BaseController
{
	@AuthRight
	@RequestMapping(value="/chain" , method = RequestMethod.GET)
	public String listChain(HttpServletRequest request , Model model){
		
		if(!model.containsAttribute(contentModel)){		
			String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
			List<TreeModel> children=TreeModelExtra.ToTreeModels(organizationService.listTree(), null, null, StringUtil.toIntegerList( expanded, ","));		
			List<TreeModel> treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","根节点",false,false,false,children)));	
			
			String jsonString  = JSONArray .fromObject(treeModels, new JsonConfig()).toString();
			model.addAttribute(contentModel, jsonString);
		}
		
		model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());
				
		return "orgnaization/chain";
	}
	
//	@AuthRight
	@RequestMapping(value="/delete" , method = RequestMethod.GET)
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="id") Long id){
		boolean del = organizationService.deleteById(id);
		if(!del){
			return "redirect:/organization/chain";
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="/authority/chain";
        return "redirect:"+returnUrl;
	}
	
//	@AuthRight
	@RequestMapping(value = "/add/{id}", method = {RequestMethod.GET})
	public String add(HttpServletRequest request, Model model, @PathVariable(value="id") Long id){	
		if(!model.containsAttribute(contentModel)){
			OrganizationEditModel oem =new OrganizationEditModel();
			oem.setParentId(id);
			Organization organization = SpringUtil.getObject(Organization.class);
			organization.setParent(null);
			model.addAttribute(contentModel, oem);
		}
		
		List<TreeModel> treeModels;
		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		if(id!=null && id>0){
			List<TreeModel> children=TreeModelExtra.ToTreeModels(organizationService.listTree(), id, null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","根节点",false,false,false,children)));
		}
		else{
			List<TreeModel> children=TreeModelExtra.ToTreeModels(organizationService.listTree(), null, null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","根节点",false,true,false,children)));
		}
		model.addAttribute(treeDataSource, JSONArray .fromObject(treeModels, new JsonConfig()).toString());		
        return "organization/edit";	
	}
	
	
//	@AuthRight
	@RequestMapping(value = "/add/{id}", method = {RequestMethod.POST})
    public String add(HttpServletRequest request, Model model, @Valid @ModelAttribute(contentModel) OrganizationEditModel editModel, @PathVariable(value="id") Long id, BindingResult result) {
        if(result.hasErrors()){
            return add(request, model, id);
        }
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        Organization organization=OrganizationModelExtra.toOrganization(editModel);
        organizationService.save(organization);
        if(returnUrl==null)
        	returnUrl="/organization/chain";
    	return "redirect:"+returnUrl;     	
    }
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
	public String edit(HttpServletRequest request, Model model, @PathVariable(value="id") Long id){	
		if(!model.containsAttribute(contentModel)){
			OrganizationEditModel organizationEditModel=OrganizationModelExtra.toOrganizationEditModel(organizationService.get(id));
			model.addAttribute(contentModel, organizationEditModel);
		}

		List<TreeModel> treeModels;
		OrganizationEditModel editModel=(OrganizationEditModel)model.asMap().get(contentModel);
		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		if(editModel.getParentId()!=null && editModel.getParentId()>0){
			List<TreeModel> children=TreeModelExtra.ToTreeModels(organizationService.listTree(), editModel.getParentId(), null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","根节点",false,false,false,children)));
		}
		else{
			List<TreeModel> children=TreeModelExtra.ToTreeModels(organizationService.listTree(), null, null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","根节点",false,true,false,children)));
		}
		model.addAttribute(treeDataSource, JSONArray .fromObject(treeModels, new JsonConfig()).toString());
		
        return "organization/edit";	
	}
	
//	@AuthRight
	@RequestMapping(value = "/edit/{id}", method = {RequestMethod.POST})
    public String edit(HttpServletRequest request, Model model, @Valid @ModelAttribute(contentModel) OrganizationEditModel editModel, @PathVariable(value="id") Long id, BindingResult result){
        if(result.hasErrors())
            return edit(request, model, id);
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        
        Organization organization=OrganizationModelExtra.toOrganization(editModel);
        organization.setPkuid(id);
        organizationService.update(organization);
        if(returnUrl==null)
        	returnUrl="/organization/chain";
    	return "redirect:"+returnUrl;      	
    }
	

}
