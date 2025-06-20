package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository <T extends User> {
}
