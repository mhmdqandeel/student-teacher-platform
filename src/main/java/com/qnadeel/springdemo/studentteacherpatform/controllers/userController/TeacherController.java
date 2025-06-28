package com.qnadeel.springdemo.studentteacherpatform.controllers.userController;

import com.qnadeel.springdemo.studentteacherpatform.dtos.request.TeacherUpdateCourse;
import com.qnadeel.springdemo.studentteacherpatform.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeacherController {


    private final TeacherService teacherService;

    @PutMapping("/{teacherId}/update")
    public ResponseEntity<String> updateStudent(@PathVariable Long teacherId,
                                                @RequestBody TeacherUpdateCourse request,
                                                @AuthenticationPrincipal UserDetails userDetails) {

        String userName = userDetails.getUsername();
        teacherService.update(teacherId, request, userName);
        return ResponseEntity.ok("Student updated successfully");
    }
}
