package com.qnadeel.springdemo.studentteacherpatform.validators.validator;

import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourceAlreadyExistsException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.StudentRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.TeacherRepository;
import com.qnadeel.springdemo.studentteacherpatform.validators.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailValidator implements Validator {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void isValid(String email) {

        boolean studentEmailIsValid = studentRepository.findByUserEmail(email).isPresent();
        boolean teacherEmailIsValid = teacherRepository.findByUserEmail(email).isPresent();

        if (studentEmailIsValid || teacherEmailIsValid) {
            throw new ResourceAlreadyExistsException(email + " already exists");
        }
    }
}