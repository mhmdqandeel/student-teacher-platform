package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.CourseCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.dtos.response.CourseResponse;
import com.qnadeel.springdemo.studentteacherpatform.mappers.CourseMapper;
import com.qnadeel.springdemo.studentteacherpatform.services.courseService.CourseService;
import com.qnadeel.springdemo.studentteacherpatform.services.courseService.createCourse.CreateCourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final CreateCourseService createCourseService;

    private final CourseMapper courseMapper;

    @Operation(
            summary = "Create a new course",
            description = "Allows a teacher to create a new course by providing course details in the request body."
    )
    @PostMapping("/create-course")
    public ResponseEntity<String> createCourse(@RequestBody @Valid CourseCreationRequest request) {
        createCourseService.crateCourse(request);
        return ResponseEntity.ok("Course created successfully");
    }

    @Operation(
            summary = "Get all courses",
            description = "Retrieves a list of all available courses " +
                    "from the system and returns them as CourseResponse DTOs.")
    @GetMapping("/")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(
                courseMapper.CourseListToCourseResponseList(
                        courseService.getAllCourses()
                ));
    }
}