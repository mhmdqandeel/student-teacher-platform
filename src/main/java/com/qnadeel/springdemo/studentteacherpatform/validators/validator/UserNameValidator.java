package com.qnadeel.springdemo.studentteacherpatform.validators.validator;

import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourceAlreadyExistsException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.StudentRepository;
import com.qnadeel.springdemo.studentteacherpatform.repositories.TeacherRepository;
import com.qnadeel.springdemo.studentteacherpatform.validators.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserNameValidator implements Validator {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void isValid(String userName) {

        boolean studentUserNameIsValid = studentRepository.findByUserName(userName).isPresent();
        boolean teacherUserNameIsValid = teacherRepository.findByUserName(userName).isPresent();

        if (teacherUserNameIsValid || studentUserNameIsValid) {
            throw new ResourceAlreadyExistsException(userName + " already exists");
        }
    }
}
