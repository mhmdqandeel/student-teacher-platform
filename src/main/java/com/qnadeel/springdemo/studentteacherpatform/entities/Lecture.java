package com.qnadeel.springdemo.studentteacherpatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "LECTURE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    @GeneratedValue
    @Column(name = "lecture_id")
    private int lectureId;

    @Column(name = "lecture_name")
    private String lectureName;

    @Column(name = "lecture_content")
    private String lectureContent;

    private LocalDate publishedAt;

    @Column(name = "lecture_duration")
    private String lectureDuration;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}