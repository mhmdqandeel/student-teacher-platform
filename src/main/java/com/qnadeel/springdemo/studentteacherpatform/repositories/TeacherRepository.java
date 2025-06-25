package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface TeacherRepository extends UserRepository<Teacher> {


    Optional<Teacher> findByUserName(String userName);

    Optional<Teacher> findByUserEmail(String email);
}