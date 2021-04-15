package com.example.tdddemocollege.domain;

public class Student {
    public String firstName;
    public String lastName;
    public String rollNumber;

    public Student() {
    }

    public Student(String firstName, String lastName, String rollNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRollNumber() {
        return rollNumber;
    }
}
