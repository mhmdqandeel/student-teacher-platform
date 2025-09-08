package com.qnadeel.springdemo.studentteacherpatform.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    private String courseTitle;

    private String courseDescription;

    private String teacherName;
}
