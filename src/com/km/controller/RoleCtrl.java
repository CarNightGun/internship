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

import com.km.bean.Role;
import com.km.common.controller.BaseController;
import com.km.util.ArraysUtil;
import com.km.util.SpringUtil;
import com.km.util.StringUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;
import com.km.web.extra.TreeModelExtra;
import com.km.web.model.RoleBindModel;
import com.km.web.model.TreeModel;

/**
 * @author tcn 空幕 email:1623092203@qq.com time:2016年5月20日上午11:30:46
 */
@Controller
@RequestMapping(value = "/role")
public class RoleCtrl extends BaseController
{
	@AuthRight
	@RequestMapping(value = "/list", method =
	{ RequestMethod.GET })
	public String list(HttpServletRequest request, Model model, @ModelAttribute Role roleSearh)
	{
		model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

        model.addAttribute(searchModel, roleSearh);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute(contentModel, roleService.listPage(roleSearh, pageNo, pageSize));

        return "role/list";
	}

	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.GET })
	public String add(HttpServletRequest request, Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			Role role = SpringUtil.getObject(Role.class);
			model.addAttribute(contentModel, role);
		}
		return "role/edit";
	}

	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.POST })
	public String add(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") Role editModel, BindingResult result)
	{
		if (result.hasErrors())
			return add(request, model);

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		Role role = editModel;
		roleService.save(role);
		if (returnUrl == null)
			returnUrl = "role/list";
		return "redirect:" + returnUrl;
	}

	@SuppressWarnings("unchecked")
	@AuthRight
	@RequestMapping(value = "/bind/{id}", method =
	{ RequestMethod.GET })
	public String bind(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id)
	{
		Role role = roleService.get(id);

		if (!model.containsAttribute(contentModel))
		{
			RoleBindModel roleBindModel = new RoleBindModel();
			roleBindModel.setName(role.getName());
			roleBindModel.setAuthorityIds((Long[]) ArraysUtil.listToArrayByField(
					role.getAuthorities(), "pkuid"));
			model.addAttribute(contentModel, roleBindModel);
		}

		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		List<Long> checkedIdList = (List<Long>) ArraysUtil.filterListByColumnGetter(
				role.getAuthorities(), "getPkuid");
		List<TreeModel> children = TreeModelExtra.ToTreeModels(authorityService.listTree(), null,
				checkedIdList, StringUtil.toIntegerList(expanded, ","));
		List<TreeModel> treeModels = new ArrayList<TreeModel>(Arrays.asList(new TreeModel(null,
				null, "计算机学院实习经费管理及审计系统", false, false, false, children)));
		model.addAttribute(treeDataSource, JSONArray.fromObject(treeModels, new JsonConfig())
				.toString());

		return "role/bind";
	}

	@AuthRight
	@RequestMapping(value = "/bind/{id}", method =
	{ RequestMethod.POST })
	public String bind(HttpServletRequest request, Model model,
			@Valid @ModelAttribute(contentModel) RoleBindModel roleBindModel,
			@PathVariable(value = "id") Long id, BindingResult result)
	{
		if (result.hasErrors()){
			return bind(request, model, id);
		}
		roleService.saveAuthorize(id,
				ArraysUtil.removeArrayItem(roleBindModel.getAuthorityIds(), new Long(0)));
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null)
			returnUrl = "role/list";
		return "redirect:" + returnUrl;
	}
}
