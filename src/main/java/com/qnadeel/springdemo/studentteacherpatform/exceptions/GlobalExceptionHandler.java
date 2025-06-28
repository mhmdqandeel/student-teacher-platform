package com.qnadeel.springdemo.studentteacherpatform.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
//        error.put("timestamp", LocalDateTime.now());
        error.put("status", ex.getStatusCode().value());
//        error.put("error", ex.getStatusCode().getClass());
        error.put("message", ex.getReason());
//        error.put("path", request.getRequestURI());
        return new ResponseEntity<>(error, ex.getStatusCode());
    }
}

