package com.qnadeel.springdemo.studentteacherpatform.services;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserLoginRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.PasswordNotMatch;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.factory.UserFactory;
import com.qnadeel.springdemo.studentteacherpatform.repositories.StudentRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.TeacherRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepositoryDispatcher;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.EmailValidator;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.UserNameValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository<? extends User> userRepository;

    private final UserFactory userFactory;

    private final UserRepositoryDispatcher userRepositoryDispatcher;

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final EmailValidator emailValidator;
    private final UserNameValidator userNameValidator;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public void save(UserCreationRequest userCreationRequest) {
        UserRepository<User> repo =
                userRepositoryDispatcher.getRepository(userCreationRequest.getUserRole());

        emailValidator.isValid(userCreationRequest.getEmail());
        userNameValidator.isValid(userCreationRequest.getUserName());

        repo.save(userFactory.create(userCreationRequest));
    }

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

    public String login(UserLoginRequest request){
        User user = findByUserName(request.getUserName())
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));

        if (!(passwordEncoder.matches(request.getPassword(), user.getUserPassword()))){
            throw new PasswordNotMatch("Password not match");
        }

        return jwtService.generateToken(user.getUserName(), user.getUserEmail());
    }
}