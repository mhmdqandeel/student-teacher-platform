package com.qnadeel.springdemo.studentteacherpatform.services.userService.createAccount;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Role;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("STUDENT")
@AllArgsConstructor
public class StudentCreation implements UserCreation<Student> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Student creteUser(UserCreationRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return Student.builder()
                .userName(request.getUserName())
                .userEmail(request.getEmail())
                .userRole(Role.STUDENT)
                .userPassword(encodedPassword)
                .build();
    }
}
