package com.qnadeel.springdemo.studentteacherpatform.entities;

import com.qnadeel.springdemo.studentteacherpatform.entities.user.Teacher;
import com.qnadeel.springdemo.studentteacherpatform.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "COURSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    public Long courseId;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "course_description")
    private String courseDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Teacher teacher;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "course")
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;
}