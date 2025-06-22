package com.qnadeel.springdemo.studentteacherpatform.entities.user;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "TEACHER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@AttributeOverride(name = "userId",
        column = @Column(name = "teacher_id")
)
public class Teacher extends User {

    @Column(name = "teacher_department")
    private String teacherDepartment;

    @Column(name = "teacher_academic_title")
    private String teacherAcademicTitle;

    @OneToMany(mappedBy = "teacher")
    private List<Course> teacherCourses;
}