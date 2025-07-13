package com.qnadeel.springdemo.studentteacherpatform.services.teacherService.updateTeacher;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UpdateTeacherRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.repositories.TeacherRepository;
import com.qnadeel.springdemo.studentteacherpatform.services.teacherService.TeacherService;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.EmailValidator;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.UserNameValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateTeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherService teacherService;

    private final EmailValidator emailValidator;
    private final UserNameValidator userNameValidator;

    @Transactional
    public void update(Long teacherId, UpdateTeacherRequest request, String userName) {
        Teacher teacher = teacherService.getTeacherById(teacherId);

        if (!teacher.getUserName().equals(userName)) {
            throw new AccessDeniedException("You are not authorized to update this teacher");
        }

        emailValidator.isValid(request.getUserEmail());
        userNameValidator.isValid(request.getUserName());

        teacher.setUserName(request.getUserName());
        teacher.setUserEmail(request.getUserEmail());
        teacher.setTeacherDepartment(request.getTeacherDepartment());
        teacher.setTeacherAcademicTitle(request.getTeacherAcademicTitle());

        teacherRepository.save(teacher);
    }
}
