package com.qnadeel.springdemo.studentteacherpatform.services.userService.createAccount;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Role;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("TEACHER")
@AllArgsConstructor
public class TeacherCreation implements UserCreation<Teacher> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Teacher creteUser(UserCreationRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return Teacher.builder()
                .userName(request.getUserName())
                .userEmail(request.getEmail())
                .userRole(Role.TEACHER)
                .userPassword(encodedPassword)
                .build();
    }
}