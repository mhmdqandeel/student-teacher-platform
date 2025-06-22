package com.qnadeel.springdemo.studentteacherpatform.services;

import com.qnadeel.springdemo.studentteacherpatform.dtos.CustomUserDetails;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .map(user -> new CustomUserDetails((User) user))
                .orElseThrow(() -> new ResourcesNotFoundException("User " + username + " not found"));
    }
}
