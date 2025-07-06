package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void itShouldFindByCourseId() {
        //Given
        Course course = new Course();
        course.setCourseTitle("Java");
        course.setCourseDescription("Java programming language");

        course = courseRepository.save(course);

        //When
        Optional<Course> result = courseRepository.findByCourseId(course.getCourseId());

        //Then
        assertThat(result).isPresent();
        assertThat(result.get().getCourseId()).isEqualTo(course.getCourseId());
    }

    @Test
    void itShouldReturnEmptyIfIdNotFound(){
        Optional<Course> result = courseRepository.findByCourseId(null);
        assertThat(result).isNotPresent();
    }
}