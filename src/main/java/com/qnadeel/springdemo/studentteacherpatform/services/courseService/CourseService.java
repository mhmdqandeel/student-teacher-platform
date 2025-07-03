package com.qnadeel.springdemo.studentteacherpatform.services.courseService;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course getCourseById(Long id) {
        return courseRepository.findByCourseId(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Course not found"));
    }
}