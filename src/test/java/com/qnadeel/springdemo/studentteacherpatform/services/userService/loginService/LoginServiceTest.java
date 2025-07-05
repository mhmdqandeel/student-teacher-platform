package com.qnadeel.springdemo.studentteacherpatform.services.userService.loginService;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserLoginRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.PasswordNotMatch;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.services.JwtService;
import com.qnadeel.springdemo.studentteacherpatform.services.userService.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginShouldReturnTokenWhenPasswordMatches() {
        // Given
        String userName = "Qandeel";
        String rawPassword = "123456";
        String encodedPassword = "encoded";
        String token = "mock-jwt-token";

        UserLoginRequest request = new UserLoginRequest(userName, rawPassword);

        User mockUser = mock(User.class);

        when(mockUser.getUserName()).thenReturn(userName);
        when(mockUser.getUserPassword()).thenReturn(encodedPassword);

        when(userService.getByUserName(userName)).thenReturn(mockUser);
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(jwtService.generateToken(mockUser)).thenReturn(token);

        // When
        String result = loginService.login(request);

        // Then
        assertEquals(token, result);
        verify(userService).getByUserName(userName);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verify(jwtService).generateToken(mockUser);
    }

    @Test
    void loginShouldThrowExceptionWhenPasswordDoesNotMatch() {
        String userName = "Qandeel";
        String rawPassword = "wrong-password";
        String encodedPassword = "encoded";

        UserLoginRequest request = new UserLoginRequest(userName, rawPassword);

        User mockUser = mock(User.class);
        when(mockUser.getUserName()).thenReturn(userName);
        when(mockUser.getUserPassword()).thenReturn(encodedPassword);

        when(userService.getByUserName(userName)).thenReturn(mockUser);
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        assertThrows(PasswordNotMatch.class, () -> {
            loginService.login(request);
        });

        verify(userService).getByUserName(userName);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verify(jwtService, never()).generateToken(any());
    }

    @Test
    void loginShouldThrowExceptionWhenUsernameDoesNotMatch(){
        String userName = "wrong-username";
        String rawPassword = "1223456";

        UserLoginRequest request = new UserLoginRequest(userName, rawPassword);

        when(userService.getByUserName(userName))
                .thenThrow(new ResourcesNotFoundException("Username not found"));

        assertThrows(ResourcesNotFoundException.class, () -> {
            loginService.login(request);
        });
    }
}