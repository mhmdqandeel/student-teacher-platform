package com.qnadeel.springdemo.studentteacherpatform.repositories;

import com.qnadeel.springdemo.studentteacherpatform.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}