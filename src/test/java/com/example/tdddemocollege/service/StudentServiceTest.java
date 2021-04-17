package com.example.tdddemocollege.service;

import com.example.tdddemocollege.domain.Student;
import com.example.tdddemocollege.repository.StudentRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    // save student return student
    @Test
    public void saveStudentReturnStudent(){
        Student sampleStudent = getSampleStudent();
        Mockito.when(studentRepository.save(sampleStudent)).thenReturn(sampleStudent);

        Student savedStudent = studentService.saveStudent(sampleStudent);
        Assertions.assertEquals(sampleStudent, savedStudent);
    }

    // save student throws RuntimeException
    @Test
    public void saveStudentThrowIOException(){
        Student sampleStudent = getSampleStudent();
        Mockito.when(studentRepository.save(sampleStudent))
                .thenThrow(new RuntimeException("Student not saved"));

        Assertions.assertThrows(RuntimeException.class, () -> {
            studentService.saveStudent(sampleStudent);
        });
    }

    // fetch details of existing student by roll number
    @Test
    public void fetchStudentDetailsFound(){
        Student sampleStudent = getSampleStudent();
        Mockito.when(studentRepository.findById(sampleStudent.getRollNumber()))
                .thenReturn(java.util.Optional.of(sampleStudent));

        Assertions.assertEquals(sampleStudent, studentService.fetchStudentDetails(sampleStudent.getRollNumber()));
        Mockito.verify(studentRepository).findById(sampleStudent.getRollNumber());
    }

    // fetch details of new student by roll number throw Exception

    // delete existing student by roll number


    public Student getSampleStudent(){
        return new Student("Nikhil", "Agrawal", "1");
    }
}
