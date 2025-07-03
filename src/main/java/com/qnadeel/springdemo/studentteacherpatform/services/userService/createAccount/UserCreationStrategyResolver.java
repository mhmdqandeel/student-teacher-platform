package com.qnadeel.springdemo.studentteacherpatform.services.userService.createAccount;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserCreationStrategyResolver {

    private final Map<String, UserCreation<? extends User>> strategyRegistry;

    public UserCreationStrategyResolver(Map<String, UserCreation<? extends User>> strategyRegistry) {
        this.strategyRegistry = strategyRegistry;
    }

    public UserCreation<? extends User> getStrategy(String userRole) {
        UserCreation<? extends User> strategy =
                strategyRegistry.get(userRole.toUpperCase());
        if (strategy == null) {
            throw new ResourcesNotFoundException("No user creation strategy found for role: " + userRole);
        }
        return strategy;
    }
}