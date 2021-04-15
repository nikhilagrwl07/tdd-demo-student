package com.example.tdddemocollege.controller;

import com.example.tdddemocollege.domain.Student;
import com.example.tdddemocollege.exception.StudentNotFoundException;
import com.example.tdddemocollege.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Student> fetchStudentDetails(@RequestParam(value = "rollNumber") String rollNumber) {
        Student student = studentService.fetchStudentDetails(rollNumber);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteStudent(@RequestParam(value = "rollNumber") String rollNumber){

        Student student = studentService.deleteStudent(rollNumber);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

//        if (rollNumber == "1")
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//
//        throw new StudentNotFoundException("Requested student not found!");
    }
}
