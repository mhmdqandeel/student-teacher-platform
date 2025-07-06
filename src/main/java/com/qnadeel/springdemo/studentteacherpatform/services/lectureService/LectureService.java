package com.qnadeel.springdemo.studentteacherpatform.services.lectureService;

import com.qnadeel.springdemo.studentteacherpatform.repositories.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

}