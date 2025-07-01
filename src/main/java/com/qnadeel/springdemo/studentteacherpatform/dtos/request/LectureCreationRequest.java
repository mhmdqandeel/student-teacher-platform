package com.qnadeel.springdemo.studentteacherpatform.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureCreationRequest {

    @NotBlank(message = "Lecture name is required")
    private String lectureName;

    @NotBlank(message = "Lecture content is required")
    private String lectureContent;

    @NotNull(message = "Published date is required")
    private LocalDate publishedAt;

    @NotBlank(message = "Lecture duration is required")
    private String lectureDuration;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
