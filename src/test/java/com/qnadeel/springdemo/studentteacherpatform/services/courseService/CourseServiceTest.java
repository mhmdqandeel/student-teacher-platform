package com.qnadeel.springdemo.studentteacherpatform.services.courseService;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.exceptions.ResourcesNotFoundException;
import com.qnadeel.springdemo.studentteacherpatform.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldReturnCourseWhenFound() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setCourseTitle("Java");

        when(courseRepository.findByCourseId(course.getCourseId()))
                .thenReturn(Optional.of(course));

        Course returnedCourse = courseService.getCourseById(1L);

        assertThat(returnedCourse).isEqualTo(course);
        verify(courseRepository).findByCourseId(1L);
    }

    @Test
    void isShouldThrowExceptionWhenCourseNotFound() {
        Long courseId = 1L;

        when(courseRepository.findByCourseId(courseId))
                .thenReturn(Optional.empty());

        assertThrows(ResourcesNotFoundException.class, () ->
                courseService.getCourseById(courseId));

        verify(courseRepository).findByCourseId(courseId);

    }
}