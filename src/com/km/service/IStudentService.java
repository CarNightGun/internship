package com.km.service;

import java.io.InputStream;
import java.util.List;

import com.km.bean.Student;
import com.km.common.service.IBaseService;
import com.km.dao.IStudentDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年6月4日上午10:05:27
 */
public interface IStudentService extends IBaseService<Long, Student, IStudentDao>
{

	/**
	 * 分页显示
	 * @author tcn 空幕  2016年6月4日下午7:20:01
	 * @param search
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public IPageList<Student> listPage(Student search, int pageNo, int pageSize);
	
	/**
	 * 导入excel数据
	 * @author tcn 空幕  2016年6月4日下午7:20:12
	 * @param inStream
	 */
	public void improtStudent(InputStream inStream);


	public void delete(String pkuids);

	public void updateUnits(String pkuids,Long unitPkuid);
	
	/**
	 * 根据学号获得学生
	 * @author tcn 空幕  2016年6月5日下午3:49:42
	 * @param stuNo
	 * @return
	 */
	public Student getStudentByStuNo(String stuNo);

	/**
	 * 根据专业和学年获取学生列表
	 * @author tcn 空幕  2016年6月5日下午6:15:10
	 * @param majorId
	 * @param stuYear
	 * @return
	 */
	public List<Student> getStudentsByMajorAndStuYear(Long majorId,Long stuYear);
	
}
