package com.example.tdddemocollege.controller;

import com.example.tdddemocollege.domain.Student;
import com.example.tdddemocollege.exception.StudentNotFoundException;
import com.example.tdddemocollege.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
@RunWith(SpringRunner.class)
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    @DisplayName("Adding student return with 200 OK status")
    public void addStudentTest200Ok() throws Exception {

        Student student = getSampleStudent();
        when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/student/v1/")
                        .content(writeJsonAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(studentService).saveStudent(Mockito.any(Student.class));
    }

    @Test
    @DisplayName("Return 200 to fetch student details for existing student by roll number")
    public void getExistingStudentDetailsByRollNumberReturn200() throws Exception {

        Student sampleStudent = getSampleStudent();
        when(studentService.fetchStudentDetails(Mockito.any(String.class)))
                .thenReturn(sampleStudent);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/student/v1")
                        .queryParam("rollNumber", sampleStudent.getRollNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(sampleStudent.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(sampleStudent.getLastName())));

        verify(studentService).fetchStudentDetails(Mockito.any(String.class));
    }

    @Test
    @DisplayName("Return 404 to fetch student details for non existing student by roll number")
    public void getNewStudentDetailsByRollNumberThrowException() throws Exception {
        when(studentService.fetchStudentDetails(Mockito.any(String.class)))
                .thenThrow(new StudentNotFoundException("Requested student not found!"));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/student/v1")
                        .queryParam("rollNumber", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("STUDENT_NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("Requested student not found!")));

        verify(studentService).fetchStudentDetails(Mockito.any(String.class));
    }

    @Test
    @DisplayName("Delete existing student by roll number return 202")
    public void deleteExistingStudentById() throws Exception {

        Student sampleStudent = getSampleStudent();
        when(studentService.deleteStudent(Mockito.any(String.class)))
                .thenReturn(sampleStudent);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/student/v1")
                        .queryParam("rollNumber", sampleStudent.getRollNumber()))
                .andExpect(status().isAccepted());

        verify(studentService).deleteStudent(Mockito.any(String.class));
    }

    @Test
    @DisplayName("Delete non existing student by roll number return 404")
    public void deleteNonExistingStudentById() throws Exception {

        Student sampleStudent = getSampleStudent();
        when(studentService.deleteStudent(Mockito.any(String.class)))
                .thenThrow(new StudentNotFoundException("Requested student not found!"));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/student/v1")
                        .queryParam("rollNumber", "10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("STUDENT_NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("Requested student not found!")));

        verify(studentService).deleteStudent(Mockito.any(String.class));
    }

    public static String writeJsonAsString(final Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getSampleStudent() {
        return new Student("Nikhil", "Agrawal", "1");
    }
}
