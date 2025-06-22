package com.qnadeel.springdemo.studentteacherpatform.services;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.factory.UserFactory;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepositoryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository<? extends User> userRepository;

    private final UserFactory userFactory;

    private final UserRepositoryDispatcher userRepositoryDispatcher;

    public User save(UserCreationRequest userCreationRequest) {
        UserRepository<User> userRepository =
                userRepositoryDispatcher.getRepository(userCreationRequest.getUserRole());

        return userRepository.save(userFactory.create(userCreationRequest));
    }

    public <T extends User> User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));
    }

    public <T extends User> User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));
    }

    public <T extends User> Optional<?> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}