package com.qnadeel.springdemo.studentteacherpatform.entities.user;

import com.qnadeel.springdemo.studentteacherpatform.entities.Enrollment;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@AttributeOverride(name = "userId",
        column = @Column(name = "student_id")
)
public class Student extends User {

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "student_major")
    private String studentMajor;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}