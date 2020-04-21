package edu.northeastern.cs5200;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;


import edu.northeastern.cs5200.Daos.UniversityDao;
import edu.northeastern.cs5200.models.Course;
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

@RunWith(SpringRunner.class)
@SpringBootTest
class Cs5200Sp2020SharmaAdityaJpaApplicationTests {
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
	   @Autowired
    UniversityDao uniDao;
	void contextLoads(){

	}
	@Test
	void test() {

		uniDao.truncateDatabase();
		Faculty f1=new Faculty("","","Alan","Turin","123A",true);
		Faculty f2=new Faculty("","","Ada","Lovelace","123B",true);
		uniDao.createFaculty(f1);
		uniDao.createFaculty(f2);
		Student s1=new Student("","","Alice","Wonderland", 2020, 12000);
		Student s2=new Student("","","Bob","Hope", 2021, 23000);
		Student s3=new Student("","","Charlie","Brown", 2019, 21000);
		Student s4=new Student("","","Dan","Craig", 2019, 0);
		Student s5=new Student("","","Edward","Scissorhands", 2022, 11000);
		Student s6=new Student("","","Frank","Herbert", 2018, 0);
		Student s7=new Student("","","Gregory","Peck", 2023, 10000);
		uniDao.createStudent(s1);
		uniDao.createStudent(s2);
		uniDao.createStudent(s3);
		uniDao.createStudent(s4);
		uniDao.createStudent(s5);
		uniDao.createStudent(s6);
		uniDao.createStudent(s7);
		Course c1=new Course("CS1234");
		Course c2=new Course("CS2345");
		Course c3=new Course("CS3456");
		Course c4=new Course("CS4567");
		uniDao.createCourse(c1);
		uniDao.createCourse(c2);
		uniDao.createCourse(c3);
		uniDao.createCourse(c4);
		uniDao.setAuthorForCourse(f1, c1);
		uniDao.setAuthorForCourse(f1, c2);
		uniDao.setAuthorForCourse(f2, c3);
		uniDao.setAuthorForCourse(f2, c4);
		Section sec1=new Section("SEC4321",50);
		Section sec2=new Section("SEC5432",50);
		Section sec3=new Section("SEC6543",50);
		Section sec4=new Section("SEC7654",50);
		uniDao.createSection(sec1);
		uniDao.createSection(sec2);
		uniDao.createSection(sec3);
		uniDao.createSection(sec4);
		uniDao.addSectionToCourse(sec1, c1);
		uniDao.addSectionToCourse(sec2, c1);
		uniDao.addSectionToCourse(sec3, c2);
		uniDao.addSectionToCourse(sec4, c3);
		uniDao.enrollStudentInSection(s1, sec1);
		uniDao.enrollStudentInSection(s1, sec2);
		uniDao.enrollStudentInSection(s2, sec2);
		uniDao.enrollStudentInSection(s3, sec3);
		
		//1
		Assertions.assertEquals(9,uniDao.findAllUsers().size());
		//2
		Assertions.assertEquals(2,uniDao.findAllFaculty().size());
		//3
		Assertions.assertEquals(7,uniDao.findAllStudents().size());
		//4
		Assertions.assertEquals(4,uniDao.findAllCourses().size());
		//5
		Assertions.assertEquals(4,uniDao.findAllSections().size());
		//6
		Assertions.assertEquals(2,uniDao.findCoursesForAuthor(f1).size());
		
		Assertions.assertEquals(2,uniDao.findCoursesForAuthor(f2).size());
		//7
		Assertions.assertEquals(2,uniDao.findSectionForCourse(c1).size());

		Assertions.assertEquals(1,uniDao.findSectionForCourse(c2).size());

		Assertions.assertEquals(1,uniDao.findSectionForCourse(c3).size());

		Assertions.assertEquals(0,uniDao.findSectionForCourse(c4).size());
   	//8
		Assertions.assertEquals(1,uniDao.findStudentsInSection(sec1).size());

		Assertions.assertEquals(2,uniDao.findStudentsInSection(sec2).size());

		Assertions.assertEquals(1,uniDao.findStudentsInSection(sec3).size());

		Assertions.assertEquals(0,uniDao.findStudentsInSection(sec4).size());
		
		//9
		
		Assertions.assertEquals(2,uniDao.findSectionsForStudent(s1).size());
		Assertions.assertEquals(1,uniDao.findSectionsForStudent(s2).size());
		Assertions.assertEquals(1,uniDao.findSectionsForStudent(s3).size());
		Assertions.assertEquals(0,uniDao.findSectionsForStudent(s4).size());
		Assertions.assertEquals(0,uniDao.findSectionsForStudent(s5).size());
		Assertions.assertEquals(0,uniDao.findSectionsForStudent(s6).size());
		Assertions.assertEquals(0,uniDao.findSectionsForStudent(s7).size());
		
		//10
		Assertions.assertEquals(49,sec1.getSeats());
		Assertions.assertEquals(48,sec2.getSeats());
		Assertions.assertEquals(49,sec3.getSeats());
		Assertions.assertEquals(50,sec4.getSeats());
		
		
		
	}

}
