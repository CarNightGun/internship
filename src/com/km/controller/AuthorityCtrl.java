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

import com.km.bean.Authority;
import com.km.common.controller.BaseController;
import com.km.util.SpringUtil;
import com.km.util.StringUtil;
import com.km.web.auth.AuthRight;
import com.km.web.extra.AuthorityModelExtra;
import com.km.web.extra.AuthorityModelExtra;
import com.km.web.extra.TreeModelExtra;
import com.km.web.model.AuthorityEditModel;
import com.km.web.model.AuthorityEditModel;
import com.km.web.model.TreeModel;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月26日下午10:22:39
 */
@Controller
@RequestMapping(value="/authority")
public class AuthorityCtrl extends BaseController
{

	@AuthRight
	@RequestMapping(value="/chain" , method = RequestMethod.GET)
	public String listChain(HttpServletRequest request , Model model){
		
		if(!model.containsAttribute(contentModel)){		
			String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
			List<TreeModel> children=TreeModelExtra.ToTreeModels(authorityService.listTree(), null, null, StringUtil.toIntegerList( expanded, ","));		
			List<TreeModel> treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","计算机学院实习经费管理及审计系统",false,false,false,children)));	
			
			String jsonString  = JSONArray .fromObject(treeModels, new JsonConfig()).toString();
			model.addAttribute(contentModel, jsonString);
		}
		
		model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());
				
		return "authority/chain";
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}" , method = RequestMethod.GET)
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="id") Long id){
		boolean del = authorityService.deleteById(id);
		if(!del){
			return "redirect:/authority/chain";
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="/authority/chain";
        return "redirect:"+returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value = "/add/{id}", method = {RequestMethod.GET})
	public String add(HttpServletRequest request, Model model, @PathVariable(value="id") Long id){	
		if(!model.containsAttribute(contentModel)){
			AuthorityEditModel oem =new AuthorityEditModel();
			oem.setParentId(id);
			Authority authority = SpringUtil.getObject(Authority.class);
			authority.setParent(null);
			model.addAttribute(contentModel, oem);
		}
		
		List<TreeModel> treeModels;
		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		if(id!=null && id>0){
			List<TreeModel> children=TreeModelExtra.ToTreeModels(authorityService.listTree(), id, null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","计算机学院实习经费管理及审计系统",false,false,false,children)));
		}
		else{
			List<TreeModel> children=TreeModelExtra.ToTreeModels(authorityService.listTree(), null, null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","计算机学院实习经费管理及审计系统",false,true,false,children)));
		}
		model.addAttribute(treeDataSource, JSONArray .fromObject(treeModels, new JsonConfig()).toString());		
        return "authority/edit";	
	}
	
	
	@AuthRight
	@RequestMapping(value = "/add/{id}", method = {RequestMethod.POST})
    public String add(HttpServletRequest request, Model model, @Valid @ModelAttribute(contentModel) AuthorityEditModel editModel, @PathVariable(value="id") Long id, BindingResult result) {
        if(result.hasErrors()){
            return add(request, model, id);
        }
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        Authority authority=AuthorityModelExtra.toAuthority(editModel);
        authorityService.save(authority);
        if(returnUrl==null)
        	returnUrl="/authority/chain";
    	return "redirect:"+returnUrl;     	
    }
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
	public String edit(HttpServletRequest request, Model model, @PathVariable(value="id") Long id){	
		if(!model.containsAttribute(contentModel)){
			AuthorityEditModel authorityEditModel=AuthorityModelExtra.toAuthorityEditModel(authorityService.get(id));
			model.addAttribute(contentModel, authorityEditModel);
		}

		List<TreeModel> treeModels;
		AuthorityEditModel editModel=(AuthorityEditModel)model.asMap().get(contentModel);
		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		if(editModel.getParentId()!=null && editModel.getParentId()>0){
			List<TreeModel> children=TreeModelExtra.ToTreeModels(authorityService.listTree(), editModel.getParentId(), null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","计算机学院实习经费管理及审计系统",false,false,false,children)));
		}
		else{
			List<TreeModel> children=TreeModelExtra.ToTreeModels(authorityService.listTree(), null, null, StringUtil.toIntegerList( expanded, ","));
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","计算机学院实习经费管理及审计系统",false,true,false,children)));
		}
		model.addAttribute(treeDataSource, JSONArray .fromObject(treeModels, new JsonConfig()).toString());
		
        return "authority/edit";	
	}
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method = {RequestMethod.POST})
    public String edit(HttpServletRequest request, Model model, @Valid @ModelAttribute(contentModel) AuthorityEditModel editModel, @PathVariable(value="id") Long id, BindingResult result){
        if(result.hasErrors()){
            return edit(request, model, id);
        }
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        
        Authority authority=AuthorityModelExtra.toAuthority(editModel);
        authority.setPkuid(id);
        authorityService.merge(authority);
        if(returnUrl==null)
        	returnUrl="/authority/chain";
    	return "redirect:"+returnUrl;      	
    }
	

}
