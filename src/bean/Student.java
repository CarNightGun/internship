package bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Student extends User
{
	
	// 学号
	private String studentID;

	// 行政班级ID
	private String classID;

	// 专业编号
	private String majorID;

	// 专业名称
	private String majorName;

	// 年级
	private String grade;

	// 学制
	private int lengthSchooling;

	public String getStudentID()
	{
		return studentID;
	}

	public void setStudentID(String studentID)
	{
		this.studentID = studentID;
	}

	public String getClassID()
	{
		return classID;
	}

	public void setClassID(String classID)
	{
		this.classID = classID;
	}

	public String getMajorID()
	{
		return majorID;
	}

	public void setMajorID(String majorID)
	{
		this.majorID = majorID;
	}

	public String getMajorName()
	{
		return majorName;
	}

	public void setMajorName(String majorName)
	{
		this.majorName = majorName;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public int getLengthSchooling()
	{
		return lengthSchooling;
	}

	public void setLengthSchooling(int lengthSchooling)
	{
		this.lengthSchooling = lengthSchooling;
	}

}
