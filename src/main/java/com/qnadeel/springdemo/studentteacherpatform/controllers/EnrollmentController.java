package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.EnrollmentCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.enrollmentService.enrollInCourse.EnrollInCourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enrollments")
@AllArgsConstructor
public class EnrollmentController {

    private final EnrollInCourseService enrollInCourseService;

    @PostMapping("/enroll")
    @Operation(summary = "Enroll in course",
            description = "Allows a student to enroll in a course")
    public ResponseEntity<String> enrollInCourse(@RequestBody @Valid EnrollmentCreationRequest request) {
        enrollInCourseService.enroll(request);
        return ResponseEntity.ok("Enrollment created");
    }
}