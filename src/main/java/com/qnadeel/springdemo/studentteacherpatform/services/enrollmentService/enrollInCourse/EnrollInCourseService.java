package com.qnadeel.springdemo.studentteacherpatform.services.enrollmentService.enrollInCourse;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.EnrollmentCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.Enrollment;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.repositories.EnrollmentRepository;
import com.qnadeel.springdemo.studentteacherpatform.services.courseService.CourseService;
import com.qnadeel.springdemo.studentteacherpatform.services.studentService.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EnrollInCourseService {

    private final EnrollmentRepository enrollmentRepository;

    private final StudentService studentService;

    private final CourseService courseService;

    @Transactional
    public void enroll(EnrollmentCreationRequest request) {
        Student student = studentService.getStudentById(request.getStudentId());
        Course course = courseService.getCourseById(request.getCourseId());

        isAlreadyEnrolled(student, course);

        Enrollment enrollment = enrollmentCreation(student, course);

        enrollmentRepository.save(enrollment);
    }

    private void isAlreadyEnrolled(Student student, Course course) {
        boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndCourse(student, course);
        if (alreadyEnrolled) {
                throw new RuntimeException("Student is already enrolled in this course");
        }
    }

    private Enrollment enrollmentCreation(Student student, Course course) {
        return  Enrollment.builder()
                .student(student)
                .course(course)
                .build();
    }
}