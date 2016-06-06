package com.km.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.km.bean.AuditHistory;
import com.km.bean.Organization;
import com.km.bean.User;
import com.km.bean.WipeCost;
import com.km.bean.WipeCostDetail;
import com.km.common.controller.BaseController;
import com.km.service.IAuditHistoryService;
import com.km.service.IMajorPlanService;
import com.km.service.IWipeCostDetailService;
import com.km.service.IWipeCostService;
import com.km.util.SpringUtil;
import com.km.util.auth.AuthUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;
import com.km.web.auth.UserAuth;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月6日上午12:44:06
 */
@Controller
@RequestMapping(value="/wipecost")
public class WipeCostCtrl extends BaseController
{

	@Resource(name = "wipeCostServiceImpl")
	 protected IWipeCostService wipeCostService;
	
	@Resource(name="majorPlanServiceImpl")
	private IMajorPlanService majorPlanService;
	
	@Resource(name="wipeCostDetailServiceImpl")
	private IWipeCostDetailService wipeCostDetailService;
	
	
	@Resource(name="auditHistoryServiceImpl")
	private IAuditHistoryService auditHistoryService;
	
	
	@ModelAttribute  
    public void populateModel(Model model) {
		 Map<String, String> abc = new HashMap<String, String>();
		 abc.put("草稿", "草稿");
		 abc.put("审核中", "审核中");
		 abc.put("已通过", "已通过");
		 abc.put("未通过", "未通过");
		 abc.put("已确认", "已确认");
		 model.addAttribute("selectAuditStateDataSource", abc);  
       
		 Map<String, String> abcd = new HashMap<String, String>();
		 abcd.put("已通过", "已通过");
		 abcd.put("未通过", "未通过");
		 model.addAttribute("selectAuditResultDataSource", abcd); 
    } 
 
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,WipeCost search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());
		
		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
		model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
		
		 model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute(contentModel, wipeCostService.listPage(search, pageNo, pageSize));

        return "wipecost/list";
    }
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.GET })
	public String add(HttpServletRequest request, Model model)
	{
		if (!model.containsAttribute(contentModel))
		{
			WipeCost wipeCost = SpringUtil.getObject(WipeCost.class);
			model.addAttribute(contentModel, wipeCost);
		}
		
		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
		 model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
 
		
		return "wipecost/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/add", method =
	{ RequestMethod.POST })
	public String add(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") WipeCost editModel, BindingResult result)
	{
		if (result.hasErrors()){
			return add(request, model);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		editModel.setAuditState("草稿");
		UserAuth userAuth = AuthUtil.getSessionUserAuth(request);
		User user = userService.get(userAuth.getPkuid());
		editModel.setApplicant(user);
		Long eid = wipeCostService.save(editModel);
		editModel = wipeCostService.get(eid);
		editModel.setWipeCode(null);
		wipeCostService.update(editModel);
		if (returnUrl == null)
			returnUrl = "wipecost/list";
		return "redirect:" + returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			WipeCost wipecost = wipeCostService.getEidtWipeCost(pkuid);
			model.addAttribute(contentModel, wipecost);
		}
		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
		 model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
//		model.addAttribute("selectCostPlanDataSource", costPlanService.getSelectSource());
		return "wipecost/edit";
	}
	
	@AuthRight
	@RequestMapping(value = "/edit/{id}", method =
	{ RequestMethod.POST })
	public String edit(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") WipeCost editModel, BindingResult result,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (result.hasErrors()){
			return edit(request, model,pkuid);
		}
		model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());
		
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		WipeCost _eCost = wipeCostService.get(editModel.getPkuid());
		editModel.setAuditState(_eCost.getAuditState());
		editModel.setApplicant(_eCost.getApplicant());
//		wipeCostService.update(editModel);
		wipeCostService.updateWipeCostData(editModel);
		
		if (returnUrl == null)
			returnUrl = "wipecost/list";
		return "redirect:" + returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String delete(HttpServletRequest request,@PathVariable(value="id")Long wipeCostId){
		
		wipeCostService.deleteById(wipeCostId);
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/submit/{id}")
	public String submit(HttpServletRequest request,HttpServletResponse  response,@PathVariable(value="id")Long wipeCostId){
		
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		WipeCost wipeCost = wipeCostService.get(wipeCostId) ;
		UserAuth userAuth = AuthUtil.getSessionUserAuth(request);
		User user = userService.get(userAuth.getPkuid());
		if(user.getPkuid().equals(wipeCost.getApplicant().getPkuid())){
			wipeCost.setAuditState("审核中");
			
			Organization major = wipeCost.getMajor();
			
			Long majorId = major.getPkuid();
			
			User headDepartment = null;
			User teachingSecretary = null;
			
			List<User> users = userService.listAllAndInitOrgAndRole();
			for (User u : users)
			{
				if(u.getOrganization() == null || u.getRole() == null){
					continue;
				}
				
				if(u.getOrganization().getPkuid() == majorId && "系主任".equals(u.getRole().getName())){
					headDepartment = u;
				}
				
				if("学院教学秘书".equals(u.getRole().getName())){
					teachingSecretary = u;
				}
				
			}
			
			if(headDepartment== null){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				try
				{
					out = response.getWriter();
					out.print(" <script language='javascript'>alert('没有对应的系主任,请联系管理员！');window.location='/internship/"+ returnUrl.toString() +"';</script>");
					out.flush();
					out.close();
					return null;
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}else if(teachingSecretary == null){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				try
				{
					out = response.getWriter();
					out.print(" <script language='javascript'>alert('没有对应的学院教学秘书,请联系管理员！');window.location='/internship/"+ returnUrl.toString() +"';</script>");
					out.flush();
					out.close();
					return null;
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			wipeCost.setHeadDepartment(headDepartment);
			wipeCost.setTeachingSecretary(teachingSecretary);
			wipeCostService.update(wipeCost);			
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try
			{
				out = response.getWriter();
				out.print(" <script language='javascript'>alert('没有权限,请联系管理员！');window.location='/internship/"+ returnUrl.toString() +"';</script>");
				out.flush();
				out.close();
				return null;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return "redirect:"+returnUrl;
	}
	
	@AuthRight
	@RequestMapping(value="/complete/{id}")
	public String complete(HttpServletRequest request,HttpServletResponse  response,@PathVariable(value="id")Long wipeCostId){
		
		WipeCost wipeCost = wipeCostService.get(wipeCostId) ;
		UserAuth userAuth = AuthUtil.getSessionUserAuth(request);
		User user = userService.get(userAuth.getPkuid());
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		if(user.getPkuid().equals(wipeCost.getApplicant().getPkuid())){
			wipeCost.setAuditState("已确认");
			wipeCostService.update(wipeCost);
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try
			{
				out = response.getWriter();
				out.print(" <script language='javascript'>alert('没有权限,请联系管理员！');window.location='/internship/"+ returnUrl.toString() +"';</script>");
				return "redirect:"+returnUrl;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}	
		return "redirect:"+returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value="/removewipecostdetail/{id}",method = {RequestMethod.GET})
	public String removestudent(HttpServletRequest request,@PathVariable(value="id")String ids){
		
		WipeCostDetail wipeCostDetail = wipeCostDetailService.get(Long.parseLong(ids.split(",")[0]));
		WipeCost wipeCost = wipeCostDetail.getWipeCost();
		wipeCostDetailService.updateWipeCostDetails(ids,null);
		wipeCostService.updateWipeCostData(wipeCost);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/wipecost/list";
		}
		return "redirect:"+returnUrl;
	} 
	
	
	@AuthRight
	@RequestMapping(value="/unauditlist", method = {RequestMethod.GET})
    public String unAuditList(HttpServletRequest request, Model model,WipeCost search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());
		
		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
		model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
		
		search.setAuditState("审核中");
		UserAuth userAuth = AuthUtil.getSessionUserAuth(request);
		User u = userService.get(userAuth.getPkuid());
		if("系主任".equals(u.getRole().getName())){
			search.setHeadDepartment(u);
		}else if("学院教学秘书".equals(u.getRole().getName())){
			search.setTeachingSecretary(u);
		}else{
			search.setAuditState("");
		}
				
		 model.addAttribute(searchModel, search);		 
		 
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute(contentModel, wipeCostService.listPage(search, pageNo, pageSize));

        return "wipecost/unauditlist";
    }
	
	
	@AuthRight
	@RequestMapping(value = "/auditwipecost/{id}", method =
	{ RequestMethod.GET })
	public String auditwipecost(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			WipeCost wipecost = wipeCostService.getAuditTipWipeCost(pkuid);
			model.addAttribute(contentModel, wipecost);
		}
		
//		model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
//		model.addAttribute("selectMajorPlanDataSource", majorPlanService.getSelectMajorPlan());
//		model.addAttribute("selectCostPlanDataSource", costPlanService.getSelectSource());
		return "wipecost/auditwipecost";
	}
	
	@AuthRight
	@RequestMapping(value = "/auditwipecost/{id}", method =
	{ RequestMethod.POST })
	public String auditwipecost(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") WipeCost editModel, BindingResult result,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (result.hasErrors()){
			return edit(request, model,pkuid);
		}
//		model.addAttribute(requestUrl, request.getServletPath());
//		model.addAttribute(requestQuery, request.getQueryString());
		
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		WipeCost _eCost = wipeCostService.get(editModel.getPkuid());
		
		if(_eCost.getHeadDepartment() != null){
			AuditHistory au = SpringUtil.getObject(AuditHistory.class);
			au.setAuditResult(editModel.getTpauditResult());
			au.setExplainRemark(editModel.getTpexplain());
			au.setOperatorName(editModel.getTpoperatorName());
			
			if("已通过".endsWith(editModel.getTpauditResult())){
				_eCost.setHeadDepartment(null);
				_eCost.setAuditState("审核中");
			}else{
				_eCost.setAuditState("未通过");
			}
			wipeCostService.update(_eCost);
			au.setWipeCost(_eCost);
			auditHistoryService.save(au);
		}else if(_eCost.getHeadDepartment() == null && _eCost.getTeachingSecretary() != null){
			AuditHistory au = SpringUtil.getObject(AuditHistory.class);
			au.setAuditResult(editModel.getTpauditResult());
			au.setExplainRemark(editModel.getTpexplain());
			au.setOperatorName(editModel.getTpoperatorName());
			
			if("已通过".endsWith(editModel.getTpauditResult())){
				_eCost.setTeachingSecretary(null);
				_eCost.setAuditState("已通过");
			}else{
				_eCost.setAuditState("未通过");
			}
			wipeCostService.update(_eCost);
			au.setWipeCost(_eCost);
			auditHistoryService.save(au);
		}
 
//		wipeCostService.update(_eCost);
//		wipeCostService.updateWipeCostData(editModel);
		
		if (returnUrl == null)
			returnUrl = "wipecost/unauditlist";
		return "redirect:" + returnUrl;
	}
	
	
	@AuthRight
	@RequestMapping(value = "/viewwipecost/{id}", method = { RequestMethod.GET })
	public String viewwipecost(HttpServletRequest request, Model model,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (!model.containsAttribute(contentModel))
		{
			WipeCost wipecost = wipeCostService.getEidtWipeCost(pkuid);
			model.addAttribute(contentModel, wipecost);
		}
		
		return "wipecost/viewwipecost";
	}
	
	@AuthRight
	@RequestMapping(value = "/viewwipecost/{id}", method = { RequestMethod.POST })
	public String viewwipecost(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("contentModel") WipeCost editModel, BindingResult result,@Valid @PathVariable(value="id")Long pkuid)
	{
		if (result.hasErrors()){
			return edit(request, model,pkuid);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null)
			returnUrl = "wipecost/list";
		return "redirect:" + returnUrl;
	}
		
}
