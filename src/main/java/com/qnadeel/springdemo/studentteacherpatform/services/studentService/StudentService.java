package com.qnadeel.springdemo.studentteacherpatform.services.studentService;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.Student;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.userRepository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourcesNotFoundException("Student not found"));
    }
}