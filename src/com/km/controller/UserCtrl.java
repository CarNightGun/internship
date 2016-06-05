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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.km.bean.Authority;
import com.km.bean.CostPlan;
import com.km.bean.User;
import com.km.common.controller.BaseController;
import com.km.util.StringUtil;
import com.km.util.auth.AuthUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;
import com.km.web.auth.AuthorityMenu;
import com.km.web.auth.PermissionMenu;
import com.km.web.auth.UserAuth;
import com.km.web.auth.UserRole;
import com.km.web.extra.TreeModelExtra;
import com.km.web.extra.UserAuthorizeModelExtra;
import com.km.web.model.TreeModel;
import com.km.web.model.UserAuthorizeModel;
import com.km.web.model.UserLoginModel;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月11日下午10:43:52
 */
@Controller
@RequestMapping("/user")
public class UserCtrl extends BaseController
{

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			model.addAttribute(contentModel, new UserLoginModel());
		}

		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model,
			@Valid @ModelAttribute(contentModel) UserLoginModel userLoginModel, BindingResult result)
	{
		// 已经有错误就返回
		if (result.hasErrors())
		{
			return login(model);
		}
		User user = userService.login(userLoginModel.getUsername().trim(), userLoginModel
				.getPassword().trim());

		if (user == null)
		{
			result.addError(new FieldError(contentModel, "errorLoginInfo", "用户名或密码错误。"));
		} else if (!user.isAudit())
		{
			result.addError(new FieldError(contentModel, "errorLoginInfo", "此用户未启用"));
		} else if (user.getRole() == null)
		{
			result.addError(new FieldError(contentModel, "errorLoginInfo", "此用户没有权限登录"));
		}
		if (result.hasErrors())
		{
			return login(model);
		}

		UserAuth userAuth = new UserAuth(user.getPkuid(), user.getName(), user.getAccountName());
		UserRole userRole = new UserRole(user.getRole().getPkuid(), user.getRole().getName());

		List<Authority> roleAuthorities = user.getRole().getAuthorities();
		List<AuthorityMenu> authorityMenus = new ArrayList<AuthorityMenu>();
		for (Authority authority : roleAuthorities)
		{
			if (authority.getParent() == null)
			{
				AuthorityMenu authorityMenu = new AuthorityMenu(authority.getPkuid(),
						authority.getName(), authority.getItemIcon(), authority.getUrl());

				List<AuthorityMenu> childrenAuthorityMenus = new ArrayList<AuthorityMenu>();
				for (Authority subAuthority : roleAuthorities)
				{
					if (subAuthority.getParent() != null
							&& subAuthority.getParent().getPkuid().equals(authority.getPkuid()))
					{
						childrenAuthorityMenus.add(new AuthorityMenu(subAuthority.getPkuid(),
								subAuthority.getName(), subAuthority.getItemIcon(), subAuthority
										.getUrl()));
					}
				}
				authorityMenu.setChildrens(childrenAuthorityMenus);
				authorityMenus.add(authorityMenu);
			}
		}

		List<PermissionMenu> permissionMenus = new ArrayList<PermissionMenu>();
		for (Authority authority : roleAuthorities)
		{
			List<Authority> parentAuthorities = new ArrayList<Authority>();
			Authority tempAuthority = authority;
			while (tempAuthority.getParent() != null)
			{
				parentAuthorities.add(tempAuthority.getParent());
				tempAuthority = tempAuthority.getParent();
			}
			if (parentAuthorities.size() >= 2)
			{
				permissionMenus.add(new PermissionMenu(parentAuthorities.get(
						parentAuthorities.size() - 1).getPkuid(), parentAuthorities.get(
						parentAuthorities.size() - 1).getName(), parentAuthorities.get(
						parentAuthorities.size() - 2).getPkuid(), parentAuthorities.get(
						parentAuthorities.size() - 2).getName(), authority.getName(), authority
						.getMatchUrl()));
			} else if (parentAuthorities.size() == 1)
			{
				permissionMenus.add(new PermissionMenu(parentAuthorities.get(0).getPkuid(),
						parentAuthorities.get(0).getName(), authority.getPkuid(), authority
								.getName(), authority.getName(), authority.getMatchUrl()));
			} else
			{
				permissionMenus.add(new PermissionMenu(authority.getPkuid(), authority.getName(),
						null, null, authority.getName(), authority.getMatchUrl()));
			}
		}
		userRole.setAuthorityMenus(authorityMenus);
		userRole.setPermissionMenus(permissionMenus);
		userAuth.setUserRole(userRole);
		AuthUtil.setSessionUserAuth(request, userAuth);

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null)
			returnUrl = "/home/index";
		return "redirect:" + returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, User userSearch){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

        model.addAttribute(searchModel, userSearch);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute(contentModel, userService.listPage(userSearch, pageNo, pageSize));

        return "user/list";
    }
	
	@AuthRight
	@RequestMapping(value="/changeaudit/{id}",method = RequestMethod.GET)
	public String changeAuditState(HttpServletRequest request,@PathVariable(value="id") String pkuid){

		userService.changeAuditState(pkuid);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(HttpServletRequest request,@PathVariable(value="id")String userids){
		
		userService.delete(userids);
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	
	
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public String loginout(HttpServletRequest request){
		AuthUtil.setSessionUserAuth(request, null);
		return "redirect:/user/login";
	}
	
	@AuthRight
	@RequestMapping(value="/authorize/{id}", method = {RequestMethod.GET})
    public String authorize(HttpServletRequest request, Model model, @PathVariable(value="id") Long id){	
		if(!model.containsAttribute(contentModel)){
			UserAuthorizeModel userBindModel=UserAuthorizeModelExtra.toUserBindModel(userService.get(id));
            model.addAttribute(contentModel, userBindModel);
        }	

		List<TreeModel> treeModels;
		UserAuthorizeModel authorizeModel=(UserAuthorizeModel)model.asMap().get(contentModel);
		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		if(authorizeModel.getOrganizationId()!=null && authorizeModel.getOrganizationId()>0){
			List<TreeModel> children=TreeModelExtra.ToTreeModels(organizationService.listTree(), authorizeModel.getOrganizationId(), null, StringUtil.toIntegerList( expanded, ","));		
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","计算机学院实习经费管理及审计系统",false,false,false,children)));
		}
		else{
			List<TreeModel> children=TreeModelExtra.ToTreeModels(organizationService.listTree(), null, null, StringUtil.toIntegerList( expanded, ","));		
			treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0","0","计算机学院实习经费管理及审计系统",false,true,false,children)));
		}
		model.addAttribute(treeDataSource, JSONArray .fromObject(treeModels, new JsonConfig()).toString());
		model.addAttribute(selectDataSource, roleService.getSelectSource());
		
        return "user/authorize";
    }

	@AuthRight
	@RequestMapping(value="/authorize/{id}", method = {RequestMethod.POST})
	public String authorize(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") UserAuthorizeModel userAuthorizeModel, @PathVariable(value="id") Long id, BindingResult result){
		if(result.hasErrors()){
            return authorize(request, model, id);
		}

		userService.updateRoleOrg(id, userAuthorizeModel.getRoleId(), userAuthorizeModel.getOrganizationId());       
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="user/list";
    	return "redirect:"+returnUrl; 	
	}
	
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			User user = userService.get(pkuid);
			model.addAttribute(contentModel, user);
		}
		return "user/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.POST })
	public String edit(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") User editModel, BindingResult result,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (result.hasErrors()){
			return edit(request, model, pkuid);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		userService.update(editModel);
		if (returnUrl == null)
			returnUrl = "costplan/list";
		return "redirect:" + returnUrl;
	}
	
	
}
