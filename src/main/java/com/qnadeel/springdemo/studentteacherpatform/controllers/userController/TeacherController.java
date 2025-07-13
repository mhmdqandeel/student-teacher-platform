package com.qnadeel.springdemo.studentteacherpatform.controllers.userController;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.UpdateTeacherRequest;
import com.qnadeel.springdemo.studentteacherpatform.services.teacherService.updateTeacher.UpdateTeacherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeacherController {

    private final UpdateTeacherService updateTeacherService;

    @Operation(
            summary = "Update teacher's course info",
            description = "Allows an authenticated teacher to update their course information using their teacher ID."
    )
    @PutMapping("/{teacherId}/update")
    public ResponseEntity<String> updateTeacher(@PathVariable Long teacherId,
                                                @RequestBody UpdateTeacherRequest request,
                                                @AuthenticationPrincipal UserDetails userDetails) {

        String userName = userDetails.getUsername();
        updateTeacherService.update(teacherId, request, userName);
        return ResponseEntity.ok("Student updated successfully");
    }
}
