package com.qnadeel.springdemo.studentteacherpatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LECTURE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    @GeneratedValue
    private int lectureId;

    private String lectureName;

    private String lectureContent;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}