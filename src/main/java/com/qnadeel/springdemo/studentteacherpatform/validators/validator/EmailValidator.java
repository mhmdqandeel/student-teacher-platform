package com.qnadeel.springdemo.studentteacherpatform.validators.validator;

import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourceAlreadyExistsException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository.StudentRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository.TeacherRepository;
import com.qnadeel.springdemo.studentteacherpatform.validators.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
    }
}