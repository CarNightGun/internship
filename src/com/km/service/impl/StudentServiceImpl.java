package com.km.service.impl;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.km.bean.Student;
import com.km.common.service.impl.BaseServiceImpl;
import com.km.dao.IStudentDao;
import com.km.service.IOrganizationService;
import com.km.service.IStudentService;
import com.km.service.IUnitService;
import com.km.util.ExcelException;
import com.km.util.ExcelUtil;
import com.km.util.page.IPageList;
import com.km.util.page.PageUtil;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午10:06:21
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class StudentServiceImpl extends BaseServiceImpl<Long, Student, IStudentDao> implements IStudentService
{
	@Resource(name = "organizationServiceImpl")
	protected IOrganizationService organizationService;
	
	@Resource(name = "unitServiceImpl")
	protected IUnitService unitService;

	@Autowired
	public StudentServiceImpl(@Qualifier("studentDaoImpl")IStudentDao baseDao)
	{
		super(baseDao);
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageList<Student> listPage(Student search, int pageNo, int pageSize)
	{
		Criteria countCriteria = baseDao.getCriteria();
		Criteria listCriteria = baseDao.getCriteria();

		if (search != null)
		{
			if (search.getMajor() !=null && search.getMajor().getPkuid() != null  )
			{
				countCriteria.createCriteria("major").add(Restrictions.eq("pkuid", search.getMajor().getPkuid()));
				listCriteria.createCriteria("major").add(Restrictions.eq("pkuid", search.getMajor().getPkuid()));
			}
			
			if ( search.getStuClass() !=null && search.getStuClass().getPkuid() != null  )
			{
				countCriteria.createCriteria("stuClass").add(Restrictions.eq("pkuid", search.getStuClass().getPkuid()));
				listCriteria.createCriteria("stuClass").add(Restrictions.eq("pkuid", search.getStuClass().getPkuid()));
			}
			
			if (search.getStuYear() != null && !search.getStuYear().isEmpty())
			{
				countCriteria.add(Restrictions.eq("stuYear", search.getStuYear()));
				listCriteria.add(Restrictions.eq("stuYear", search.getStuYear()));
			}
			
			if (search.getUnit() != null)
			{
				if(search.getUnit().getPkuid() != null){
					countCriteria.createCriteria("unit").add(Restrictions.eq("pkuid", search.getUnit().getPkuid()));
					listCriteria.createCriteria("unit").add(Restrictions.eq("pkuid", search.getUnit().getPkuid()));
				}else{
					countCriteria.add(Restrictions.isNull("unit"));
					listCriteria.add(Restrictions.isNull("unit"));
				}
			}
		}

		listCriteria.setFirstResult((pageNo - 1) * pageSize);
		listCriteria.setMaxResults(pageSize);
		List<Student> items = listCriteria.list();
		countCriteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(countCriteria.uniqueResult().toString());

		return PageUtil.getPageList(pageSize, pageNo, count, items);
	}

	@Override
	public void improtStudent(InputStream inStream)
	{
		String sheetName = "student"; 
		Class<Student> entityClass = Student.class; 
		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
		fieldMap.put("学号", "stuNo");
		fieldMap.put("真实姓名", "realName");
		fieldMap.put("电话", "telphone");
		fieldMap.put("电子邮件", "email");
		fieldMap.put("专业", "major.name");
		fieldMap.put("班级", "stuClass.name");
		fieldMap.put("学年", "stuYear");
		String[] uniqueFields = {"学号"}; 
		
		Map<String, Map<Long,String>> fieldValueMap = new LinkedHashMap<String, Map<Long,String>>();
		fieldValueMap.put("major.name", organizationService.getSelectMajor());
		fieldValueMap.put("stuClass.name", organizationService.getSelectStuClass());
		 
		List<Student> listStudents;
		try
		{
			listStudents = ExcelUtil.excelToList(inStream, sheetName, entityClass, fieldMap, uniqueFields,fieldValueMap);
			Student _stu = null;
			for (Student student : listStudents)
			{	
				_stu = this.getStudentByStuNo(student.getStuNo());
				if(_stu == null){
					this.save(student);
				}else{
					student.setPkuid(_stu.getPkuid());
					this.merge(student);
				}
				_stu = null;
				
			}
		} catch (ExcelException e)
		{
			e.printStackTrace();
		}	
	}
	@Override
	public void delete(String pkuids)
	{
		if (pkuids == null || pkuids.isEmpty())
		{
			return;
		}

		Pattern p = Pattern.compile("^([0-9]+,?)*[0-9]+");
		Matcher m = p.matcher(pkuids);
		if (!m.find())
		{
			return;
		}
		String[] pkuidArr = pkuids.split(",");
		for (String pkuid : pkuidArr)
		{
			baseDao.deleteById(Long.parseLong(pkuid));
		}
	}

	
	@Override
	public void updateUnits(String pkuids,Long unitPkuid)
	{
		if (pkuids == null || pkuids.isEmpty())
		{
			return;
		}

		Pattern p = Pattern.compile("^([0-9]+,?)*[0-9]+");
		Matcher m = p.matcher(pkuids);
		if (!m.find())
		{
			return;
		}
		String[] pkuidArr = pkuids.split(",");
		for (String pkuid : pkuidArr)
		{
			Student stu =baseDao.get(Long.parseLong(pkuid));
			if(unitPkuid != null){
				stu.setUnit(unitService.get(unitPkuid));
			}else{
				stu.setUnit(null);
			}
			baseDao.update(stu);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getStudentByStuNo(String stuNo)
	{
		Criteria listCriteria = baseDao.getCriteria();			 
		listCriteria.add(Restrictions.eq("stuNo", stuNo));
		List<Student> items = listCriteria.list();
		if(items.size() > 0){
			return items.get(0);
		}else{
			return null;	
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsByMajorAndStuYear(Long majorId, Long stuYear)
	{
		Criteria listCriteria = baseDao.getCriteria();
		listCriteria.add(Restrictions.eq("stuYear", stuYear.toString()));
		listCriteria.createCriteria("major").add(Restrictions.eq("pkuid", majorId));
		List<Student> items = listCriteria.list();
		return items;
	}
}
