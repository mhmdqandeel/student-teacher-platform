package com.qnadeel.springdemo.studentteacherpatform.services;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.LectureCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.Lecture;
import com.qnadeel.springdemo.studentteacherpatform.repositories.LectureRepository;
import com.qnadeel.springdemo.studentteacherpatform.services.courseService.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    private final CourseService courseService;

    @Transactional
    public void addLecture(LectureCreationRequest request, String userNameOfTeacher) {
        Course course = courseService.getCourseById(request.getCourseId());

        if (!course.getTeacher().getUserName().equals(userNameOfTeacher)) {
            throw new AccessDeniedException("You are not allowed to add this lecture");
        }

        Lecture lecture = Lecture.builder()
                .lectureName(request.getLectureName())
                .lectureContent(request.getLectureContent())
                .publishedAt(LocalDate.now())
                .lectureDuration(request.getLectureDuration())
                .course(course)
                .build();

        course.getLectures().add(lecture);

        lectureRepository.save(lecture);
    }
}