package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserLoginRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody UserCreationRequest request) {
        userService.save(request);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}