package com.qnadeel.springdemo.studentteacherpatform.services.userService.createAccount;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UserCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;

public interface UserCreation<T extends User> {
    T creteUser(UserCreationRequest request);
}