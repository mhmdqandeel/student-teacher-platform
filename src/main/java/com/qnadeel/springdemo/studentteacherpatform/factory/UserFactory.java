package com.qnadeel.springdemo.studentteacherpatform.factory;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public User create(UserCreationRequest userCreationRequest) {
        String encodedPassword = passwordEncoder.encode(userCreationRequest.getPassword());

        return switch (userCreationRequest.getUserRole()) {
            case STUDENT -> Student.builder()
                    .userName(userCreationRequest.getName())
                    .userEmail(userCreationRequest.getEmail())
                    .userRole(userCreationRequest.getUserRole())
                    .userPassword(encodedPassword)
                    .build();

            case TEACHER -> Teacher.builder()
                    .userName(userCreationRequest.getName())
                    .userEmail(userCreationRequest.getEmail())
                    .userRole(userCreationRequest.getUserRole())
                    .userPassword(encodedPassword)
                    .build();

            default -> throw new IllegalArgumentException("Unsupported role: "
                    + userCreationRequest.getUserRole());
        };
    }
}
