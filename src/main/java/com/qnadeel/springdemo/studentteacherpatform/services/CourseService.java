package com.qnadeel.springdemo.studentteacherpatform.services;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.CourseCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final TeacherService teacherService;

    @Transactional
    public void crateCourse(CourseCreationRequest request) {
        Teacher teacher = teacherService.getTeacherById(request.getTeacherId());

        Course course = Course.builder()
                .courseTitle(request.getCourseTitle())
                .courseDescription(request.getCourseDescription())
                .teacher(teacher)
                .createdAt(LocalDate.now())
                .build();

        teacher.getTeacherCourses().add(course);
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findByCourseId(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Course not found"));
    }
}