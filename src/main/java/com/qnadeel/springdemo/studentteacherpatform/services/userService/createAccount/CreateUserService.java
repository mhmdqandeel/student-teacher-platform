package com.qnadeel.springdemo.studentteacherpatform.services.userService.createAccount;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepositoryDispatcher;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepository;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.EmailValidator;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.UserNameValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserService {

    private final UserRepositoryDispatcher userRepositoryDispatcher;
    private final EmailValidator emailValidator;
    private final UserNameValidator userNameValidator;
    private final UserCreationStrategyResolver userCreationStrategy;

    public void createUserAccount(UserCreationRequest userCreationRequest) {
        UserRepository<User> repo = userRepositoryDispatcher
                .getRepository(userCreationRequest.getUserRole());

        emailValidator.isValid(userCreationRequest.getEmail());
        userNameValidator.isValid(userCreationRequest.getUserName());

        UserCreation<? extends User> userCreation =
                resolveUserCreationStrategy(userCreationRequest);

        repo.save(userCreation.creteUser(userCreationRequest));
    }

    private UserCreation<? extends User> resolveUserCreationStrategy(UserCreationRequest request) {
        return userCreationStrategy.getStrategy(String.valueOf(request.getUserRole()));
    }
}