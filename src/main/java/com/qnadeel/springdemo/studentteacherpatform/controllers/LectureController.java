package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.LectureCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.lectureService.LectureService;
import com.qnadeel.springdemo.studentteacherpatform.services.lectureService.addLecture.AddLectureService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lectures")
@AllArgsConstructor
public class LectureController {

    private final AddLectureService addLectureService;

    @Operation(
            summary = "Add a new lecture",
            description = "Allows a teacher to add a new lecture. The authenticated user's username is used to associate the lecture with the teacher."
    )
    @PostMapping("/")
    public ResponseEntity<String> addLecture(@RequestBody @Valid LectureCreationRequest request,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        String userNameOfTeacher = userDetails.getUsername();
        addLectureService.addLecture(request, userNameOfTeacher);
        return ResponseEntity.ok("Successfully added lecture");
    }
}