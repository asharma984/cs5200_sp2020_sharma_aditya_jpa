package edu.northeastern.cs5200.models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Section {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  private String title;
	  private int seats;
	  @ManyToOne
	  private Course course;
	  @OneToMany(mappedBy="section")
	  private List<Enrollment> enrollments;
	  public Section()
		{
			enrollments=new ArrayList<>();
		}

	  public Section(String title, int seats) {
		super();
		this.title = title;
		this.seats = seats;
			enrollments=new ArrayList<>();
	}
	public void addEnrollment(Enrollment e)
	  {
	 	 enrollments.add(e);
	  }
	  public void removeEnrollment(Enrollment e)
	  {
	 	 enrollments.remove(e);
	  }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
		if(!course.getSections().contains(this))
		course.addSection(this);
	}
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	  

}
