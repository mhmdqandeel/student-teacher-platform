package com.qnadeel.springdemo.studentteacherpatform.services.userService.createAccount;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Role;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.UserRepositoryDispatcher;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.EmailValidator;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.UserNameValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
class CreateUserServiceTest {

    @Mock
    private UserRepositoryDispatcher userRepositoryDispatcher;

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private UserNameValidator userNameValidator;

    @Mock
    private UserCreationStrategyResolver userCreationStrategy;

    @Mock
    @SuppressWarnings("rawtypes")
    private UserCreation userCreation;

    @Mock
    private UserRepository<User> userRepository;

    @InjectMocks
    private CreateUserService createUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTeacherSuccessfully() {
        //Given
        UserCreationRequest request = new UserCreationRequest();
        request.setUserName("Qandeel");
        request.setEmail("qandeel@gmail.com");
        request.setUserRole(Role.TEACHER);

        Teacher teacher = new Teacher();

        when(userRepositoryDispatcher.getRepository(Role.TEACHER)).thenReturn(userRepository);
        when(userCreationStrategy.getStrategy("TEACHER")).thenReturn(userCreation);
        when(userCreation.creteUser(request)).thenReturn(teacher);

        //When
        createUserService.createUserAccount(request);

        //Then
        verify(emailValidator).isValid("qandeel@gmail.com");
        verify(userNameValidator).isValid("Qandeel");
        verify(userRepository).save(teacher);
    }

    @Test
    void shouldCreateStudentSuccessfully() {
        //Given
        UserCreationRequest request = new UserCreationRequest();
        request.setUserName("Qandeel");
        request.setEmail("qandeel@gmail.com");
        request.setUserRole(Role.STUDENT);

        Student student = new Student();

        when(userRepositoryDispatcher.getRepository(Role.STUDENT)).thenReturn(userRepository);
        when(userCreationStrategy.getStrategy("STUDENT")).thenReturn(userCreation);
        when(userCreation.creteUser(request)).thenReturn(student);

        //When
        createUserService.createUserAccount(request);

        //Then
        verify(emailValidator).isValid("qandeel@gmail.com");
        verify(userNameValidator).isValid("Qandeel");
        verify(userRepository).save(student);
    }
}