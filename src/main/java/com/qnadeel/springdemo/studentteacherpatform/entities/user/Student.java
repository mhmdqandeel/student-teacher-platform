package com.qnadeel.springdemo.studentteacherpatform.entities.user;

import com.qnadeel.springdemo.studentteacherpatform.entities.Enrollment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends User {

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "student_major")
    private String studentMajor;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}