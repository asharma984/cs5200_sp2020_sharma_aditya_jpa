package edu.northeastern.cs5200.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Enrollment;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment,Integer> {
	@Query("SELECT enroll.section from Enrollment enroll WHERE enroll.student=:student")
    List<Section>findAllSectionsByStudent(@Param("student") Student student);

    @Query("SELECT enroll.student from Enrollment enroll WHERE enroll.section=:section")
    List<Student>findAllStudentsBySection(@Param("section") Section section);
}
