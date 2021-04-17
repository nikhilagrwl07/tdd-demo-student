package com.example.tdddemocollege.repository;


import com.example.tdddemocollege.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

//    public Student save(Student student) {
//        return student;
//    }
//
//    public Student get(String rollNumber) {
//        return null;
//    }
}
