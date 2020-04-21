package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Faculty extends Person {
   private String office;
   private boolean tenured;
   @OneToMany(mappedBy="faculty")
   private List<Course> courses;
   public Faculty()
   {
    courses=new ArrayList<>();
   }

   public Faculty(String username, String password, String firstName, String lastName, String office, boolean tenured) {
	super(username, password, firstName, lastName);
	this.office = office;
	this.tenured = tenured;
}

public void addCourse(Course course) {
       courses.add(course);
       course.setFaculty(this);
   }

   public void removeCourse(Course course) {
       courses.remove(course);
       course.setFaculty(this);
   }
public String getOffice() {
	return office;
}
public void setOffice(String office) {
	this.office = office;
}
public boolean isTenured() {
	return tenured;
}
public void setTenured(boolean tenured) {
	this.tenured = tenured;
}
public List<Course> getCourses() {
	return courses;
}
public void setCourses(List<Course> courses) {
	this.courses = courses;
}
   
}
