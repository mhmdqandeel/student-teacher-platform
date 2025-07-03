package com.qnadeel.springdemo.studentteacherpatform.services.userService.loginService;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserLoginRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.PasswordNotMatch;
import com.qnadeel.springdemo.studentteacherpatform.services.JwtService;
import com.qnadeel.springdemo.studentteacherpatform.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserService userService;

    public String login(UserLoginRequest request){
        User user = userService.getByUserName(request.getUserName());
        isPasswordMatch(request, user.getUserPassword());
        return jwtService.generateToken(user);
    }

    private void isPasswordMatch (UserLoginRequest request, String password){
        if (!(passwordEncoder.matches(request.getPassword(), password))){
            throw new PasswordNotMatch("Password not match");
        }
    }
}
