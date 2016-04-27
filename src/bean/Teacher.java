package bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table
public class Teacher extends User
{
	private String jobNumber;
	
	private String professionalTitle;

	public String getJobNumber()
	{
		return jobNumber;
	}

	public void setJobNumber(String jobNumber)
	{
		this.jobNumber = jobNumber;
	}

	public String getProfessionalTitle()
	{
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle)
	{
		this.professionalTitle = professionalTitle;
	}
}
