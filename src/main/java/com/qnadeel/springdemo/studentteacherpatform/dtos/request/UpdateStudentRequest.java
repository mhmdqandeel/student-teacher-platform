package com.qnadeel.springdemo.studentteacherpatform.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateStudentRequest {

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String userName;

    @Email(message = "Invalid email format")
    private String userEmail;

    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters")
    private String universityName;

    @Size(min = 2, max = 50, message = "Academic title must be between 2 and 50 characters")
    private String studentMajor;
}