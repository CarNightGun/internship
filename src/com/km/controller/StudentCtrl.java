package com.km.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.km.bean.Student;
import com.km.bean.Unit;
import com.km.common.controller.BaseController;
import com.km.service.IStudentService;
import com.km.util.ExcelUtil;
import com.km.util.page.PageUtil;
import com.km.web.auth.AuthRight;
 

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日下午1:52:32
 */
@Controller
@RequestMapping(value="/student")
public class StudentCtrl extends BaseController
{
	@Resource(name="studentServiceImpl")
	protected IStudentService studentService;
	
	@AuthRight
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model,Student search){ 			
    	model.addAttribute(requestUrl, request.getServletPath());
		model.addAttribute(requestQuery, request.getQueryString());

		 model.addAttribute(searchModel, search);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_NO, PageUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageUtil.NAME_PAGE_SIZE, PageUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("selectMajorDataSource", organizationService.getSelectMajor());
        model.addAttribute("selectStuClassDataSource", organizationService.getSelectStuClass());
        model.addAttribute(contentModel, studentService.listPage(search, pageNo, pageSize));

        return "student/list";
    }
	
	@AuthRight
	@RequestMapping(value="/importstudent",method = {RequestMethod.POST})
	public String importstudent(@RequestParam(value = "upfile", required = true) MultipartFile file,HttpServletRequest request){

			InputStream in;
			try
			{
				in = file.getInputStream();
				studentService.improtStudent(in);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		
		return "redirect:/student/list";
	 
	}
	
	@AuthRight
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(HttpServletRequest request,@PathVariable(value="id")String userids){
		
		studentService.delete(userids);
		
		 String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null){
			returnUrl = "/home/index";
		}
		return "redirect:"+returnUrl;
	}
	
	
}
