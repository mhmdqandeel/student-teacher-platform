package com.qnadeel.springdemo.studentteacherpatform.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreationRequest {

    @NotBlank(message = "Course title is required")
    private String courseTitle;

    @NotBlank(message = "Course description is required")
    private String courseDescription;

    @NotNull(message = "Teacher ID is required")
    private Long teacherId;
}