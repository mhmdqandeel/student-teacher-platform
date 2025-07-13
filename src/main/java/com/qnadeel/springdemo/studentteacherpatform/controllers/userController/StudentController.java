package com.qnadeel.springdemo.studentteacherpatform.controllers.userController;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UpdateStudentRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.studentService.updateStudent.UpdateStudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final UpdateStudentService updateStudentService;

    @Operation(
            summary = "Update student's course info",
            description = "Allows an authenticated student to update their course information using their teacher ID."
    )
    @PutMapping("/{studentId}/update")
    public ResponseEntity<String> updateStudent(@PathVariable Long studentId,
                                                @RequestBody UpdateStudentRequest request,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        String userName = userDetails.getUsername();
        updateStudentService.update(studentId, request, userName);
        return ResponseEntity.ok("Student updated successfully");
    }
}