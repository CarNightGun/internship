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
import com.km.bean.Student;
import com.km.bean.Unit;
import com.km.common.controller.BaseController;
import com.km.service.IStudentService;
import com.km.service.IUnitService;
import com.km.util.SpringUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午10:15:54
 */
@Controller
@RequestMapping(value= "/unit")
public class UtilCtrl extends BaseController
{

	 @Resource(name = "unitServiceImpl")
	 protected IUnitService unitService;
	 
	 @Resource(name="studentServiceImpl")
	 protected IStudentService studentService;
	
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,Unit search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

		 model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute(contentModel, unitService.listPage(search, pageNo, pageSize));

        return "unit/list";
    }
	
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.GET })
	public String add(HttpServletRequest request, Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			Unit unit = SpringUtil.getObject(Unit.class);
			model.addAttribute(contentModel, unit);
			model.addAttribute(requestUrl, request.getServletPath());
			model.addAttribute(requestQuery, request.getQueryString());
		}
		return "unit/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.POST })
	public String add(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") Unit editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return add(request, model);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

		unitService.save(editModel);
		if (returnUrl == null)
			returnUrl = "unit/list";
		return "redirect:" + returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			Unit unit = unitService.getEditUtil(pkuid);
			
			
			model.addAttribute(contentModel, unit);
		}
		model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());
		return "unit/edit";
	}
	
	@AuthRight
	@RequestMapping(value =  "/edit/{id}", method = RequestMethod.POST )
	public String edit(HttpServletRequest request, Model model,
			@Valid @ModelAttribute(contentModel) Unit editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return edit(request, model,editModel.getPkuid());
		}

		unitService.update(editModel);

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null)
			returnUrl = "unit/list";
		return "redirect:" + returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String delete(HttpServletRequest request,@PathVariable(value="id")Long costPlanId){
		
		unitService.deleteById(costPlanId);
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/studentList/{editid}", method = {RequestMethod.GET})
	public String selectstudent(HttpServletRequest request, Model model,Student search,@PathVariable(value="editid")Long editid){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());
		
		search.setUnit(SpringUtil.getObject(Unit.class));
		model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
        model.addAttribute("selectStuClassDataSource", organizationService.getSelectStuClass());
        model.addAttribute("unitStudentContentModel", studentService.listPage(search, pageNo, pageSize));
        
        Unit unit = unitService.getEditUtil(editid);
		model.addAttribute(contentModel, unit);

        return "unit/studentList";
    }
	
	
	@AuthRight
	@RequestMapping(value="/selectstudent/{id}/{editId}",method = {RequestMethod.GET})
	public String selectStudent(HttpServletRequest request,@Valid @ModelAttribute("contentModel") Unit editModel,Model model,@PathVariable(value="id")String studentids,@PathVariable(value="editId")Long pkuid){
		 
		if(pkuid == null){
			return "redirect:/unit/list";
		}
		
		studentService.updateUnits(studentids,pkuid);
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/unit/list";
		}
		return "redirect:"+returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/removestudent/{id}",method = {RequestMethod.GET})
	public String removestudent(HttpServletRequest request,@PathVariable(value="id")String studentids){
		
		
		studentService.updateUnits(studentids,null);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/unit/list";
		}
		return "redirect:"+returnUrl;
	} 
	
}
