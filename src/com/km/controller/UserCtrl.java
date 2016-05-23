package com.km.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.km.bean.Authority;
import com.km.bean.User;
import com.km.common.controller.BaseController;
import com.km.util.auth.AuthUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;
import com.km.web.auth.AuthorityMenu;
import com.km.web.auth.PermissionMenu;
import com.km.web.auth.UserAuth;
import com.km.web.auth.UserRole;
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
		} else if (!user.isAduit())
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
	
	
}
