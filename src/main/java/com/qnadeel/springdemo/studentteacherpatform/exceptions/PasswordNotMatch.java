package com.qnadeel.springdemo.studentteacherpatform.exceptions;

public class PasswordNotMatch extends RuntimeException {
    public PasswordNotMatch(String message) {
        super(message);
    }
}
