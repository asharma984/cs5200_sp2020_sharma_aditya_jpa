package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Student extends Person {
  private int gradYear;
  private long scholarship;

  @OneToMany(mappedBy="student")
  private List<Enrollment> enrollment;
  public Student()
	{

	}

  
 public Student(String username, String password, String firstName, String lastName, int gradYear, long scholarship) {
	super(username, password, firstName, lastName);
	this.gradYear = gradYear;
	this.scholarship = scholarship;
}
public void addEnrollment(Enrollment e)
 {
	 enrollment.add(e);
 }
 public void removeEnrollment(Enrollment e)
 {
	 enrollment.remove(e);
 }
public int getGradYear() {
	return gradYear;
}
public void setGradYear(int gradYear) {
	this.gradYear = gradYear;
}
public long getScholarship() {
	return scholarship;
}
public void setScholarship(long scholarship) {
	this.scholarship = scholarship;
}

public List<Enrollment> getEnrollment() {
	return enrollment;
}
public void setEnrollment(List<Enrollment> enrollment) {
	this.enrollment = enrollment;
}
  
}
