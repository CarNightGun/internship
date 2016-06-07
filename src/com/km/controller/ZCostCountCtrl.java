package com.km.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.km.bean.WipeCost;
import com.km.bean.ZCostCount;
import com.km.common.controller.BaseController;
import com.km.service.ICostCategoryService;
import com.km.service.IZCostCountService;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月7日上午10:22:19
 */
@Controller
@RequestMapping(value="/zcostcount")
public class ZCostCountCtrl extends BaseController
{
	
	@Resource(name="costCategoryServiceImpl")
	private ICostCategoryService costCategoryService;
	
	
//	@Resource(name="zCostCountServiceImpl")
//	private IZCostCountService  zCostCountService;
//	
	
//	@AuthRight
//	@RequestMapping(value="/list", method = {RequestMethod.GET})
//    public String list(HttpServletRequest request, Model model,ZCostCount search){ 			
//    	model.addAttribute(requestUrl, request.getServletPath());
//		model.addAttribute(requestQuery, request.getQueryString());
//		
//		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
//		model.addAttribute("selectCostCategoryDataSource", costCategoryService.getSelectCostCategory());
//		
//		 model.addAttribute(searchModel, search);
//        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
//        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
//        model.addAttribute(contentModel, zCostCountService.listPage(search, pageNo, pageSize));
//
//        return "zcostcount/list";
//    }

}
