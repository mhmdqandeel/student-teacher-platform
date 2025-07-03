package com.qnadeel.springdemo.studentteacherpatform.services.courseService.createCourse;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.CourseCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.repositories.CourseRepository;
import com.qnadeel.springdemo.studentteacherpatform.services.teacherService.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateCourseService {

    private final CourseRepository courseRepository;

    private final TeacherService teacherService;

    @Transactional
    public void crateCourse(CourseCreationRequest request) {
        Teacher teacher = teacherService.getTeacherById(request.getTeacherId());
        Course course = courseCreation(request,teacher);

        List<Course> courses =teacher.getTeacherCourses();

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(course);
        courseRepository.save(course);
    }

    private Course courseCreation(CourseCreationRequest request, Teacher teacher) {
        return Course.builder()
                .courseTitle(request.getCourseTitle())
                .courseDescription(request.getCourseDescription())
                .teacher(teacher)
                .createdAt(LocalDate.now())
                .build();
    }
}