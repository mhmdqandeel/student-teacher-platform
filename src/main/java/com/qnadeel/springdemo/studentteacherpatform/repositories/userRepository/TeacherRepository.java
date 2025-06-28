package com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends UserRepository<Teacher> {


    Optional<Teacher> findByUserName(String userName);

    Optional<Teacher> findByUserEmail(String email);
}