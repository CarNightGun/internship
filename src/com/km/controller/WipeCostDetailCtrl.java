package com.km.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.km.bean.CostListDetail;
import com.km.bean.Unit;
import com.km.bean.WipeCostDetail;
import com.km.common.controller.BaseController;
import com.km.service.ICostCategoryService;
import com.km.service.ICostPlanService;
import com.km.service.IWipeCostDetailService;
import com.km.service.IWipeCostService;
import com.km.util.SpringUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月6日上午10:40:18
 */
@Controller
@RequestMapping(value="/wipecostdetail")
public class WipeCostDetailCtrl extends BaseController
{
	 @Resource(name = "wipeCostDetailServiceImpl")
	 protected IWipeCostDetailService wipeCostDetailService;
	 
	 @Resource(name="costCategoryServiceImpl")
	private ICostCategoryService costCategoryService;

	 @Resource(name = "wipeCostServiceImpl")
	 protected IWipeCostService wipeCostService;
	 
	 
	 @ModelAttribute  
     public void populateModel(Model model) {
		 Map<Integer, String> abc = new HashMap<Integer, String>();
		 abc.put(0, "专业实习");
		 abc.put(1, "毕业实习");
        model.addAttribute("selectInternClassDataSource", abc);  
        
        Map<String, String> abcd = new HashMap<String, String>();
        abcd.put("草稿", "草稿");
        abcd.put("审核中", "审核中");
        abcd.put("已通过", "已通过");
        abcd.put("未通过", "未通过");
        abcd.put("已确认", "已确认");
		 model.addAttribute("selectAuditStateDataSource", abcd);  
        
     } 
	 
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,WipeCostDetail search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

		 model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        
        model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
        model.addAttribute("selectWipeCostDataSource", wipeCostService.getSelectWipeCost());
        model.addAttribute("selectCostCategoryDataSource", costCategoryService.getSelectCostCategory());
        
        model.addAttribute(contentModel, wipeCostDetailService.listPage(search, pageNo, pageSize));

        return "wipecostdetail/list";
    }
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.GET })
	public String add(HttpServletRequest request, Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			WipeCostDetail wipeCostDetail = SpringUtil.getObject(WipeCostDetail.class);
			model.addAttribute(contentModel, wipeCostDetail);
		}
		model.addAttribute("selectWipeCostDataSource", wipeCostService.getSelectUnAuditWipeCost());
		model.addAttribute("selectCostClassDataSource", costCategoryService.getSelectCostClass());
		return "wipecostdetail/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.POST })
	public String add(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") WipeCostDetail editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return add(request, model);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		Long pkuid = wipeCostDetailService.saveAndUpdateData(editModel);
		wipeCostService.updateWipeCostByDetailId(pkuid);
		if (returnUrl == null)
			returnUrl = "wipecostdetail/list";
		return "redirect:" + returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			WipeCostDetail wipeCostDetail = wipeCostDetailService.get(pkuid);
			
			
			model.addAttribute(contentModel, wipeCostDetail);
		}
		model.addAttribute("selectWipeCostDataSource", wipeCostService.getSelectUnAuditWipeCost());
		model.addAttribute("selectCostClassDataSource", costCategoryService.getSelectCostClass());
		 
		return "wipecostdetail/edit";
	}
	
	@AuthRight
	@RequestMapping(value =  "/edit/{id}", method = RequestMethod.POST )
	public String edit(HttpServletRequest request, Model model,
			@Valid @ModelAttribute(contentModel) WipeCostDetail editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return edit(request, model,editModel.getPkuid());
		}

//		wipeCostDetailService.update(editModel);
		Long pkuid = wipeCostDetailService.saveAndUpdateData(editModel);
		wipeCostService.updateWipeCostByDetailId(pkuid);

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null)
			returnUrl = "wipecostdetail/list";
		return "redirect:" + returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String delete(HttpServletRequest request,@PathVariable(value="id")Long costPlanId){
		
		wipeCostDetailService.deleteById(costPlanId);
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	 

}
