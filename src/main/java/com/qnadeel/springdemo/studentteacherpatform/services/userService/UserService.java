package com.qnadeel.springdemo.studentteacherpatform.services.userService;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.StudentRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.TeacherRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository<? extends User> userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    public <T extends User> User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));
    }

    public Optional<User> findByUserName(String userName) {
        return studentRepository.findByUserName(userName)
                .map(student -> (User) student)
                .or(() -> teacherRepository.findByUserName(userName)
                        .map(teacher -> (User) teacher));
    }

    public User getByUserName(String userName) {
        return findByUserName(userName)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));
    }
}