package com.km.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.km.bean.CostCountModel;
import com.km.bean.CostListDetail;
import com.km.common.controller.BaseController;
import com.km.service.ICostCategoryService;
import com.km.service.ICostCountModelService;
import com.km.service.IMajorPlanService;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日下午1:43:43
 */
@Controller
@RequestMapping(value="/costcount")
public class CostCountModelCtrl extends BaseController
{
	
//	@Resource(name="majorPlanServiceImpl")
//	private IMajorPlanService majorPlanService;
	
	@Resource(name="costCategoryServiceImpl")
	private ICostCategoryService costCategoryService;
	
	@Resource(name="costCountModelServiceImpl")
	private ICostCountModelService costCountModelService;
	
	
	@ModelAttribute  
    public void populateModel(Model model) {
		 Map<Integer, String> abc = new HashMap<Integer, String>();
		 abc.put(0, "专业实习");
		 abc.put(1, "毕业实习");
       model.addAttribute("selectInternClassDataSource", abc);  
    } 
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,CostCountModel search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

		if(search.getCountYear() == null){
			search.setCountYear(2015L);
		}
		 model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        
        model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
        model.addAttribute("selectCostCategoryDataSource", costCategoryService.getSelectCostCategory());
        
        costCountModelService.countInfoByYear(search.getCountYear());	
        model.addAttribute(contentModel, costCountModelService.listPage(search, pageNo, pageSize));

        return "costcount/list";
    }

}
