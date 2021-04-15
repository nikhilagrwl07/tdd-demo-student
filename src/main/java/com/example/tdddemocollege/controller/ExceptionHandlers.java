package com.example.tdddemocollege.controller;

import com.example.tdddemocollege.exception.ErrorResponse;
import com.example.tdddemocollege.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleStudentNotFound(StudentNotFoundException e) {
        return new ErrorResponse("STUDENT_NOT_FOUND", e.getMessage());
    }

}
