package com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}
