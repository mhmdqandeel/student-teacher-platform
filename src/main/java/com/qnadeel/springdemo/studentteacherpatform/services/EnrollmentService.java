package com.qnadeel.springdemo.studentteacherpatform.services;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.EnrollmentCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.Enrollment;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.repositories.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final StudentService studentService;

    private final CourseService courseService;

    public void enroll(EnrollmentCreationRequest request) {
        Student student = studentService.getStudentById(request.getStudentId());

        Course course = courseService.getCourseById(request.getCourseId());

        boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndCourse(student, course);

        if (alreadyEnrolled) {
            throw new RuntimeException("Student is already enrolled in this course");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        enrollmentRepository.save(enrollment);
    }
}
