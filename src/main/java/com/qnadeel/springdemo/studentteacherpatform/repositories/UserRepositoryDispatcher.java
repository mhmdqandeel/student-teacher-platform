package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.Role;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class UserRepositoryDispatcher {

    private final Map<Role, UserRepository<? extends User>> userRepositories = new HashMap<>();

    public UserRepositoryDispatcher( AdminRepository adminRepo
                                    ,TeacherRepository teacherRepo
                                    ,StudentRepository studentRepo) {

        userRepositories.put(Role.ADMIN, adminRepo);
        userRepositories.put(Role.STUDENT, studentRepo);
        userRepositories.put(Role.TEACHER, teacherRepo);
    }

    @SuppressWarnings("unchecked")
    public <T extends User> UserRepository<T> getRepository(Role role) {
        if (!userRepositories.containsKey(role)) {
            throw new UnsupportedOperationException();
        }
        return (UserRepository<T>) userRepositories.get(role);
    }
}
