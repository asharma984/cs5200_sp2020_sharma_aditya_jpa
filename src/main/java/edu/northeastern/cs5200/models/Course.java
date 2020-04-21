package edu.northeastern.cs5200.models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@Entity
public class Course {
	  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;


     
     @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
     private List<Section> sections;
     @ManyToOne(fetch=FetchType.LAZY,optional=true,cascade=CascadeType.ALL)
     private Faculty faculty;
public Course()
{
	sections=new ArrayList<>();
	faculty=new Faculty();
}

	public Course(String label) {
		super();
		this.label = label;
	}
    public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}




    public void addSection(Section section) {
        sections.add(section);
        section.setCourse(this);
    }

    public void removeSection(Section section) {
    	sections.remove(section);
        section.setCourse(this);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}



	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
		if (!faculty.getCourses().contains(this)) {
			faculty.getCourses().add(this);
		}

	}
}
