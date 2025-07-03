package com.qnadeel.springdemo.studentteacherpatform.services.userService;

import com.qnadeel.springdemo.studentteacherpatform.dtos.CustomUserDetails;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userService.findByUserName(userName)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new ResourcesNotFoundException("User " + userName + " not found"));
    }
}
