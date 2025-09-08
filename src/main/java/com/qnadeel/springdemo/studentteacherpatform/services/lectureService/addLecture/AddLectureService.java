package com.qnadeel.springdemo.studentteacherpatform.services.lectureService.addLecture;

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
public class AddLectureService {

    private final LectureRepository lectureRepository;

    private final CourseService courseService;

    @Transactional
    public void addLecture(LectureCreationRequest request, String userNameOfTeacher) {
        Course course = courseService.getCourseById(request.getCourseId());

        isAlreadyAdded(course.getTeacher().getUserName(), userNameOfTeacher);

        Lecture lecture = lectureCreation(request, course);

        course.getLectures().add(lecture);

        lectureRepository.save(lecture);
    }

    private void isAlreadyAdded(String userNameOfTeacherFromCourse, String userNameOfTeacher) {
        if(!userNameOfTeacherFromCourse.equals(userNameOfTeacher)) {
            throw new AccessDeniedException("You are not allowed to add this lecture");
        }
    }

    private Lecture lectureCreation(LectureCreationRequest request, Course course) {
        return Lecture.builder()
                .lectureName(request.getLectureName())
                .lectureContent(request.getLectureContent())
                .publishedAt(LocalDate.now())
                .lectureDuration(request.getLectureDuration())
                .course(course)
                .build();
    }
}
