package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.LectureCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.lectureService.LectureService;
import com.qnadeel.springdemo.studentteacherpatform.services.lectureService.addLecture.AddLectureService;
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

    @PostMapping("/")
    public ResponseEntity<String> addLecture(@RequestBody LectureCreationRequest request,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        String userNameOfTeacher = userDetails.getUsername();
        addLectureService.addLecture(request, userNameOfTeacher);
        return ResponseEntity.ok("Successfully added lecture");
    }
}