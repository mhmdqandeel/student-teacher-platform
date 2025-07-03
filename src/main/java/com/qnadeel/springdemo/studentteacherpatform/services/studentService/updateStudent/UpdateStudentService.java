package com.qnadeel.springdemo.studentteacherpatform.services.studentService.updateStudent;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.StudentUpdateRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository.StudentRepository;
import com.qnadeel.springdemo.studentteacherpatform.services.studentService.StudentService;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.EmailValidator;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.UserNameValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateStudentService {

    private final StudentRepository studentRepository;

    private final StudentService studentService;

    private final EmailValidator emailValidator;
    private final UserNameValidator userNameValidator;

    @Transactional
    public void update(Long studentId, StudentUpdateRequest request, String userName){
        Student student = studentService.getStudentById(studentId);

        if (!student.getUserName().equals(userName)) {
            throw new AccessDeniedException("You are not authorized to update this student");
        }

        emailValidator.isValid(request.getUserEmail());
        userNameValidator.isValid(request.getUserName());

        student.setUserName(request.getUserName());
        student.setUserEmail(request.getUserEmail());
        student.setStudentMajor(request.getStudentMajor());
        student.setUniversityName(request.getUniversityName());

        studentRepository.save(student);
    }
}
