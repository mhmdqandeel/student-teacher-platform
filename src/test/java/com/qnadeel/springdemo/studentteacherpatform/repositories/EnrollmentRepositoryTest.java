package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.Enrollment;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void itShouldExistsByStudentAndCourse() {
        //Given
        Student student = new Student();
        student.setUserName("Qandeel");
        student.setUserEmail("student@gmail.com");
        studentRepository.save(student);

        Course course = new Course();
        course.setCourseTitle("Java");
        course.setCourseDescription("Java programming language");
        courseRepository.save(course);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);

        //when
        boolean exists = enrollmentRepository.existsByStudentAndCourse(student, course);

        //Then
        assertThat(exists).isTrue();
    }
}