package com.qnadeel.springdemo.studentteacherpatform.mappers;

import com.qnadeel.springdemo.studentteacherpatform.dtos.response.CourseResponse;
import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    public CourseResponse CourseToCourseResponse(Course course) {
        return CourseResponse.builder()
                .courseTitle(course.getCourseTitle())
                .courseDescription(course.getCourseDescription())
                .teacherName(course.getTeacher().getUserName())
                .build();
    }

    public List<CourseResponse> CourseListToCourseResponseList(List<Course> courseList) {
        return courseList.stream().map
                (this::CourseToCourseResponse)
                .toList();
    }
}
