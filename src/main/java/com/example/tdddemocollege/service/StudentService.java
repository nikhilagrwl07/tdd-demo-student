package com.example.tdddemocollege.service;

import com.example.tdddemocollege.domain.Student;
import com.example.tdddemocollege.exception.StudentNotFoundException;
import com.example.tdddemocollege.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student fetchStudentDetails(String rollNumber) {
        Optional<Student> student = studentRepository.findById(rollNumber);
        if (student.isPresent())
            return student.get();

        throw new StudentNotFoundException("Requested student not found!");
    }

    public Student deleteStudent(String rollNumber) {
        studentRepository.deleteById(rollNumber);
        return new Student("Nikhil", "Agrawal", rollNumber);
    }
}

