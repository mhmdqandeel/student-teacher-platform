package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("should find student by name")
    void itShouldFindByUserName() {
        //given
        Student student = new Student();
        student.setUserName("Firas");
        student.setUserEmail("firas@gmail.com");
        student.setUserPassword("123456");

        studentRepository.save(student);

        //when
        Optional<Student> result = studentRepository.findByUserName("Firas");

        //then
        assertThat(result).isPresent();
        assertThat(result.get().getUserName()).isEqualTo("Firas");
    }

    @Test
    void itShouldFindByUserEmail() {
        //given
        Student student = new Student();
        student.setUserName("Firas");
        student.setUserEmail("firas@gmail.com");
        student.setUserPassword("123456");

        studentRepository.save(student);

        //when
        Optional<Student> result = studentRepository.findByUserEmail("firas@gmail.com");

        //then
        assertThat(result).isPresent();
        assertThat(result.get().getUserName()).isEqualTo("Firas");
    }

    @Test
    @DisplayName("Should return empty if username not found")
    void itShouldReturnEmptyIfUserNameNotFound() {
        Optional<Student> result = studentRepository.findByUserName("notexist");
        Assertions.assertThat(result).isNotPresent();
    }
}