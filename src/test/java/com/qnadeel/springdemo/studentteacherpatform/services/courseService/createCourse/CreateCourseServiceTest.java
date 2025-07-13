package com.qnadeel.springdemo.studentteacherpatform.services.courseService.createCourse;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.CourseCreationRequest;
import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.repositories.CourseRepository;
import com.qnadeel.springdemo.studentteacherpatform.services.teacherService.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateCourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private CreateCourseService createCourseService;

    @Captor
    private ArgumentCaptor<Course> courseCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldCrateCourseSuccessfully() {
        Teacher teacher = new Teacher();
        teacher.setUserId(1L);
        teacher.setUserName("qandeel");
        teacher.setUserEmail("qandeel@gmail.com");

        CourseCreationRequest request = new CourseCreationRequest();
        request.setCourseTitle("Java");
        request.setCourseDescription("Java");
        request.setTeacherId(teacher.getUserId());

        when(teacherService.getTeacherById(teacher.getUserId())).thenReturn(teacher);

        createCourseService.crateCourse(request);

        verify(teacherService).getTeacherById(teacher.getUserId());
        verify(courseRepository).save(courseCaptor.capture());

        Course savedCourse = courseCaptor.getValue();
        assertThat(savedCourse.getCourseTitle()).isEqualTo("Java");
        assertThat(savedCourse.getCourseDescription()).isEqualTo("Java");
        assertThat(savedCourse.getTeacher().getUserId()).isEqualTo(teacher.getUserId());
        assertThat(savedCourse.getCreatedAt()).isEqualTo(LocalDate.now());
    }
}