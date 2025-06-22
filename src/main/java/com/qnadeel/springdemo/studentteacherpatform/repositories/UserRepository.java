package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository <T extends User> extends JpaRepository<T, Long> {
    T save(T user);

    Optional<T> findByUsername(String username);
}