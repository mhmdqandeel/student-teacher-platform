package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.CourseCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.courseService.CourseService;
import com.qnadeel.springdemo.studentteacherpatform.services.courseService.createCourse.CreateCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final CreateCourseService createCourseService;

    @PostMapping("/create-course")
    public ResponseEntity<String> createCourse(@RequestBody CourseCreationRequest request) {
        createCourseService.crateCourse(request);
        return ResponseEntity.ok("Course created successfully");
    }
}