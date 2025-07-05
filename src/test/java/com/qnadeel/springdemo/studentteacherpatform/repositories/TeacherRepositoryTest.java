package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Should find teacher by username")
    void itShouldFindByUserName() {
        // Given
        Teacher teacher = new Teacher();
        teacher.setUserName("qnadeel");
        teacher.setUserEmail("nadeel@example.com");
        teacher.setUserPassword("password"); // set other required fields

        teacherRepository.save(teacher);

        // When
        Optional<Teacher> result = teacherRepository.findByUserName("qnadeel");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getUserName()).isEqualTo("qnadeel");
    }

    @Test
    @DisplayName("Should find teacher by email")
    void itShouldFindByUserEmail() {
        // Given
        Teacher teacher = new Teacher();
        teacher.setUserName("john");
        teacher.setUserEmail("john.doe@example.com");
        teacher.setUserPassword("secure123");

        teacherRepository.save(teacher);

        // When
        Optional<Teacher> result = teacherRepository.findByUserEmail("john.doe@example.com");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getUserEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    @DisplayName("Should return empty if username not found")
    void itShouldReturnEmptyIfUserNameNotFound() {
        Optional<Teacher> result = teacherRepository.findByUserName("notexist");
        assertThat(result).isNotPresent();
    }
}