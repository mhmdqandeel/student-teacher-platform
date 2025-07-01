package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.Enrollment;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);
}
