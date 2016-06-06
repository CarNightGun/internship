package com.km.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.km.bean.CostPlan;
import com.km.common.controller.BaseController;
import com.km.service.ICostPlanService;
import com.km.util.SpringUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月29日下午8:57:25
 */
@Controller
@RequestMapping(value="/costplan")
public class CostPlanCtrl extends BaseController
{

	 @Resource(name = "costPlanServiceImpl")
	 protected ICostPlanService costPlanService;
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,CostPlan search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

		 model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute(contentModel, costPlanService.listPage(search, pageNo, pageSize));

        return "costplan/list";
    }
	
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.GET })
	public String add(HttpServletRequest request, Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			CostPlan costPlan = SpringUtil.getObject(CostPlan.class);
			model.addAttribute(contentModel, costPlan);
		}
		return "costplan/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.POST })
	public String add(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") CostPlan editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return add(request, model);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		costPlanService.save(editModel);
		if (returnUrl == null)
			returnUrl = "costplan/list";
		return "redirect:" + returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			CostPlan costPlan = costPlanService.get(pkuid);
			model.addAttribute(contentModel, costPlan);
		}
		return "costplan/edit";
	}
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.POST })
	public String edit(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") CostPlan editModel, BindingResult result,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (result.hasErrors()){
			return edit(request, model,pkuid);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		costPlanService.update(editModel);
		if (returnUrl == null)
			returnUrl = "costplan/list";
		return "redirect:" + returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String delete(HttpServletRequest request,@PathVariable(value="id")Long costPlanId){
		
		costPlanService.deleteById(costPlanId);
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	
	
}
