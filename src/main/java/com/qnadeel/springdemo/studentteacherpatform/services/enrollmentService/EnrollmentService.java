package com.qnadeel.springdemo.studentteacherpatform.services.enrollmentService;

import com.qnadeel.springdemo.studentteacherpatform.repositories.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

}
