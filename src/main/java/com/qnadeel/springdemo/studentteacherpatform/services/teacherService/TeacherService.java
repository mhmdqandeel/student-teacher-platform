package com.qnadeel.springdemo.studentteacherpatform.services.teacherService;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.TeacherUpdateCourse;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository.TeacherRepository;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.EmailValidator;
import com.qnadeel.springdemo.studentteacherpatform.validators.validator.UserNameValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourcesNotFoundException("Teacher not found"));
    }
}