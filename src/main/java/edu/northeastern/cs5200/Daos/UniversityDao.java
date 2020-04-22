package edu.northeastern.cs5200.Daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Enrollment;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;
import edu.northeastern.cs5200.repositories.CourseRepository;
import edu.northeastern.cs5200.repositories.EnrollmentRepository;
import edu.northeastern.cs5200.repositories.FacultyRepository;
import edu.northeastern.cs5200.repositories.PersonRepository;
import edu.northeastern.cs5200.repositories.SectionRepository;
import edu.northeastern.cs5200.repositories.StudentRepository;
@Service
public class UniversityDao {
	    @Autowired
	    PersonRepository personRepository;
	    @Autowired
	    CourseRepository courseRepository;
	    @Autowired
	    FacultyRepository facultyRepository;
	    @Autowired
	    StudentRepository studentRepository;
	    @Autowired
	    SectionRepository sectionRepository;
	    @Autowired
	    EnrollmentRepository enrollmentRepository;


	    public void truncateDatabase()
	    {
	    	enrollmentRepository.deleteAll();
	    	sectionRepository.deleteAll();
	    	courseRepository.deleteAll();
	    	personRepository.deleteAll();
	    	facultyRepository.deleteAll();
	    	studentRepository.deleteAll();
	    }
	    public Faculty createFaculty(Faculty faculty)
	    {
	    	return facultyRepository.save(faculty);
	    }
	    public Student createStudent(Student student)
	    {
	    	return studentRepository.save(student);
	    }
	    public Course createCourse(Course course)
	    {
	    	return courseRepository.save(course);
	    }
	    public Section createSection(Section section)
	    {
	    	return sectionRepository.save(section);
	    }
	    public Course addSectionToCourse(Section section, Course course)
	    {
	    	course.addSection(section);
	    	section.setCourse(course);
	    	sectionRepository.save(section);
	    	return courseRepository.save(course);
	    }
	    public Course setAuthorForCourse(Faculty faculty, Course course)
	    {
	    	try{
				course.setFaculty(faculty);
				return courseRepository.save(course);}
	    	catch(Exception e)
				{
					System.out.println("catch");
				}
	    	return null;
	    }
	    public boolean enrollStudentInSection(Student student, Section section)
	    {
	    	if(section.getSeats()>0)
	    	{   section.setSeats(section.getSeats()-1);;
	    		Enrollment e=new Enrollment(0,"",student,section);
	    		student.addEnrollment(e);
	    		studentRepository.save(student);
	    		section.addEnrollment(e);
	    		sectionRepository.save(section);
	    		enrollmentRepository.save(e);
	    		return true;	
	    	}
	    	return false;
	    	
	    }
	    public List<Person> findAllUsers()
	    {
	    	return (List<Person>)personRepository.findAll();
	    }
	    public List<Faculty> findAllFaculty()
	     {
	    	 return (List<Faculty>)facultyRepository.findAll();
	     }
	    public List<Student> findAllStudents()
	    {
	    	return (List<Student>)studentRepository.findAll();
	    }
	    public List<Course> findAllCourses()
	     {
	    	 return (List<Course>)courseRepository.findAll();
	     }
	    public List<Section> findAllSections()
	     {
	    	 return (List<Section>)sectionRepository.findAll();
	     }
	    public List<Course> findCoursesForAuthor(Faculty faculty)
	     {
	    	 return faculty.getCourses();
	     }
	    public List<Section> findSectionForCourse(Course course){
	    	 return course.getSections();
	     }
	    public List<Student> findStudentsInSection(Section section)
	     {
	    	return enrollmentRepository.findAllStudentsBySection(section);
	    	
	     }
	    public List<Section> findSectionsForStudent(Student student)
	     {
	    	return enrollmentRepository.findAllSectionsByStudent(student);
	     }
	    
}
