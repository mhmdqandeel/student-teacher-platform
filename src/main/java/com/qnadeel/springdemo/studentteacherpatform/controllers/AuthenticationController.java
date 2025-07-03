package com.qnadeel.springdemo.studentteacherpatform.controllers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserLoginRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.userService.loginService.LoginService;
import com.qnadeel.springdemo.studentteacherpatform.services.userService.createAccount.CreateUserService;
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

    private final CreateUserService createUserService;
    private final LoginService loginService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody UserCreationRequest request) {
        createUserService.createUserAccount(request);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }
}