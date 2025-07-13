package com.qnadeel.springdemo.studentteacherpatform.validators.validator;

import com.qnadeel.springdemo.studentteacherpatform.repositories.StudentRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldPassIfIsValid() {


    }
}