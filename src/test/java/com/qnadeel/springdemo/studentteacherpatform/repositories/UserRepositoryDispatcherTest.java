package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.Role;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DataJpaTest
class UserRepositoryDispatcherTest {

    private UserRepositoryDispatcher userRepositoryDispatcher;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        teacherRepository = mock(TeacherRepository.class);
        studentRepository = mock(StudentRepository.class);
        adminRepository = mock(AdminRepository.class);
        userRepositoryDispatcher =
                new UserRepositoryDispatcher(adminRepository, teacherRepository, studentRepository);
    }

    @Test
    void itShouldReturnAdminRepository() {
        UserRepository <? extends User> repo =
                userRepositoryDispatcher.getRepository(Role.ADMIN);
        assertThat(repo).isSameAs(adminRepository);
    }

    @Test
    void itShouldReturnStudentRepository() {
        UserRepository <? extends User> repo =
                userRepositoryDispatcher.getRepository(Role.STUDENT);
        assertThat(repo).isSameAs(studentRepository);
    }

    @Test
    void itShouldReturnTeacherRepository() {
        UserRepository <? extends User> repo =
                userRepositoryDispatcher.getRepository(Role.TEACHER);
        assertThat(repo).isSameAs(teacherRepository);
    }

    @Test
    void itShouldThrowForUnknownRole() {
        assertThrows(UnsupportedOperationException.class, () -> {
            userRepositoryDispatcher.getRepository(null);
        });
    }
}