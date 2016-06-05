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

import com.km.bean.MajorPlan;
import com.km.common.controller.BaseController;
import com.km.service.ICostPlanService;
import com.km.service.IMajorPlanService;
import com.km.util.SpringUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月31日下午5:39:46
 */
@Controller
@RequestMapping(value="/majorplan")
public class MajorPlanCtrl extends BaseController
{
	@Resource(name = "majorPlanServiceImpl")
	 protected IMajorPlanService majorPlanService;
	
	@Resource(name = "costPlanServiceImpl")
	 protected ICostPlanService costPlanService;
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,MajorPlan searchMajorPlan){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

		 model.addAttribute(searchModel, searchMajorPlan);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute(contentModel, majorPlanService.listPage(searchMajorPlan, pageNo, pageSize));

        return "majorplan/list";
    }
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.GET })
	public String add(HttpServletRequest request, Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			MajorPlan majorPlan = SpringUtil.getObject(MajorPlan.class);
			model.addAttribute(contentModel, majorPlan);
		}
		
		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
		model.addAttribute("selectCostPlanDataSource", costPlanService.getSelectSource());
		
		return "majorplan/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.POST })
	public String add(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") MajorPlan editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return add(request, model);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		majorPlanService.save(editModel);
		if (returnUrl == null)
			returnUrl = "majorplan/list";
		return "redirect:" + returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			MajorPlan majorPlan = majorPlanService.get(pkuid);
			model.addAttribute(contentModel, majorPlan);
		}
		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
		model.addAttribute("selectCostPlanDataSource", costPlanService.getSelectSource());
		return "majorplan/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.POST })
	public String edit(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") MajorPlan editModel, BindingResult result,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (result.hasErrors()){
			return edit(request, model,pkuid);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		majorPlanService.updateMajorPlanData(editModel);
		if (returnUrl == null)
			returnUrl = "majorplan/list";
		return "redirect:" + returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(HttpServletRequest request,@PathVariable(value="id")Long majorPlanId){
		
		majorPlanService.deleteById(majorPlanId);
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
}
