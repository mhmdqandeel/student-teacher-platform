package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.EnrollmentCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.EnrollmentService;
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

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollInCourse(@RequestBody @Valid EnrollmentCreationRequest request) {
        enrollmentService.enroll(request);
        return ResponseEntity.ok("Enrollment created");
    }
}