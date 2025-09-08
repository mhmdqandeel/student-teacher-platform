package com.qnadeel.springdemo.studentteacherpatform.services.lectureService;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.Lecture;
import com.qnadeel.springdemo.studentteacherpatform.repositories.LectureRepository;
import com.qnadeel.springdemo.studentteacherpatform.services.courseService.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    private final CourseService courseService;

    public Lecture getAllLecturesByCourseId(Long courseId) {
        Course course = courseService.getCourseById(courseId);

        return lectureRepository.getAllByCourse(course);
    }
}