package com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository <T extends User> extends JpaRepository<T, Long> {
//    T save(T user);

//    Optional<T> findByUserName(String userName);
}