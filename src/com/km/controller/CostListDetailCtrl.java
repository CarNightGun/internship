package com.km.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.km.bean.CostListDetail;
import com.km.common.controller.BaseController;
import com.km.service.ICostCategoryService;
import com.km.service.ICostListDetailService;
import com.km.service.IMajorPlanService;
import com.km.util.SpringUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月5日下午2:04:25
 */
@Controller
@RequestMapping(value = "/costlistdetail")
public class CostListDetailCtrl extends BaseController
{
	@Resource(name="costListDetailServiceImpl")
	private ICostListDetailService costListDetailService;
	
	@Resource(name="majorPlanServiceImpl")
	private IMajorPlanService majorPlanService;
	
	@Resource(name="costCategoryServiceImpl")
	private ICostCategoryService costCategoryService;

	
	 @ModelAttribute  
     public void populateModel(Model model) {
		 Map<Integer, String> abc = new HashMap<Integer, String>();
		 abc.put(0, "专业实习");
		 abc.put(1, "毕业实习");
        model.addAttribute("selectInternClassDataSource", abc);  
     } 
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,CostListDetail search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

		 model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        
        model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
        model.addAttribute("selectCostCategoryDataSource", costCategoryService.getSelectCostCategory());
        
        model.addAttribute(contentModel, costListDetailService.listPage(search, pageNo, pageSize));

        return "costlistdetail/list";
    }
	
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.GET })
	public String add(HttpServletRequest request, Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			CostListDetail costListDetail = SpringUtil.getObject(CostListDetail.class);
			model.addAttribute(contentModel, costListDetail);
			model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
		    model.addAttribute("selectCostCategoryDataSource", costCategoryService.getSelectCostCategory());
		}
		return "costlistdetail/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.POST })
	public String add(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") CostListDetail editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return add(request, model);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		
		costListDetailService.upateData(costListDetailService.save(editModel));
		if (returnUrl == null)
			returnUrl = "costlistdetail/list";
		return "redirect:" + returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			CostListDetail costListDetail = costListDetailService.get(pkuid);
			model.addAttribute(contentModel, costListDetail);
			model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
		    model.addAttribute("selectCostCategoryDataSource", costCategoryService.getSelectCostCategory());
		}
		return "costlistdetail/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.POST })
	public String edit(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") CostListDetail editModel, BindingResult result,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (result.hasErrors()){
			return edit(request, model,pkuid);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		costListDetailService.update(editModel);
		costListDetailService.upateData(editModel.getPkuid());
		if (returnUrl == null)
			returnUrl = "costlistdetail/list";
		return "redirect:" + returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String delete(HttpServletRequest request,@PathVariable(value="id")String costListDetailIds){
		
		Set<Long> ids = costListDetailService.deleteAndReturnMajorPlanIds(costListDetailIds);
		if(ids != null){
			for (Long idLong : ids)
			{	
				if(idLong != null){
					majorPlanService.updateMajorPlanData(idLong);
				}
			}
		}
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value = "/timing",method = { RequestMethod.POST } )
	@ResponseBody
	public  Map<String, Object> timingTip(HttpServletRequest request,
			@RequestParam("inputValue")Double inputValue,
			@RequestParam("majorPlanId")Long majorPlanId,
			@RequestParam("costCategoryId")Long costCategoryId )
	{
		Map<String, Object> mapmap = (HashMap<String, Object>) costListDetailService.getCostPlanUsedInfo(inputValue, majorPlanId, costCategoryId);
		
//		Iterator<Map.Entry<String, Object>> iterator = mapmap.entrySet().iterator();
//		while (iterator.hasNext())
//		{
//			Map.Entry<String, Object> ert = iterator.next();
//			map.put(ert.getKey(), ert.getValue());			
//		}		
		return mapmap;
	}
	
}
