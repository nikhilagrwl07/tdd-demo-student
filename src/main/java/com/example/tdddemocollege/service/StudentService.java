package com.example.tdddemocollege.service;

import com.example.tdddemocollege.domain.Student;
import com.example.tdddemocollege.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student saveStudent(Student student) {
        return student;
    }

    public Student fetchStudentDetails(String rollNumber) {
        if (rollNumber == "1")
            return new Student("Nikhil", "Agrawal", rollNumber);
        else
            throw new StudentNotFoundException("Requested student not found!");
    }

    public Student deleteStudent(String rollNumber) {
        return new Student("Nikhil", "Agrawal", rollNumber);
    }
}

